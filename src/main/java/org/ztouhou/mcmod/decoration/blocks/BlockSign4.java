package org.ztouhou.mcmod.decoration.blocks;

import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEnityUrinals;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityExitSignWithSensor;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityFireExtinguisherBox;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import rikka.librikka.RayTraceHelper;
import rikka.librikka.block.ICustomBoundingBox;

public class BlockSign4 extends BlockSignBase implements ICustomBoundingBox {
	public static String[] subNames = new String[] {"led", "urinals", "fireextinguisher_box", "sign_exit_smart"};
	
	public final int type;
	private BlockSign4(int meta) {
		super(subNames[meta], Block.Properties.create(Material.GLASS).hardnessAndResistance(5.0F, 10.0F));
		this.type = meta;
//        setDefaultState(this.getDefaultState().withProperty(Properties.type2bit, 3)); // EAST
	}
	
	public static BlockSign4[] create() {
		BlockSign4[] ret = new BlockSign4[subNames.length];
		for (int i=0; i<subNames.length; i++) {
			ret[i] = new BlockSign4(i);
		}
		return ret;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {return true;}
		
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		switch (type) {
		case 0:
			return new TileEntityLED12864();
		case 1:
			return new TileEnityUrinals();
		case 2:
			return new TileEntityFireExtinguisherBox();
		case 3:
			return new TileEntityExitSignWithSensor();
		default:
			return null;
		}
	}
	
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rtResult) {
        if (player.isCrouching())
            return ActionResultType.FAIL;
        
		if (type == 2) {
			TileEntity te = world.getTileEntity(pos);
	        player.openContainer((INamedContainerProvider) te);
	        
	        return ActionResultType.SUCCESS;
		} else if (type == 3) {
			if (!world.isRemote) {
				TileEntity te = world.getTileEntity(pos);
				if (te instanceof TileEntityExitSignWithSensor) 
					((TileEntityExitSignWithSensor) te).toggleForcedOn();
			}

			return ActionResultType.SUCCESS;
		}
		
		return ActionResultType.FAIL;
    }
    
    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
        TileEntity te = world.getTileEntity(pos);
        
        if (te instanceof TileEntityFireExtinguisherBox) {
        	InventoryHelper.dropInventoryItems(world, pos, ((TileEntityFireExtinguisherBox) te).inventory);
        } else if (te instanceof TileEntityExitSignWithSensor){
        	if (((TileEntityExitSignWithSensor) te).isPowered())
				updateRedstoneSignal(world, pos, state);
        }
        
        return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
    }
    
    ///////////////////
    /// RedStone
    ///////////////////
	public void updateRedstoneSignal(World world, BlockPos pos, BlockState state) {
		Direction facing = getRotation(state);
		world.notifyNeighborsOfStateChange(pos, this);
		world.notifyNeighborsOfStateChange(pos.offset(facing), this);
	}

    @Override
    public boolean shouldCheckWeakPower(BlockState state, IWorldReader world, BlockPos pos, Direction side) {
    	return false;
    }
    
    @Override
    public boolean canProvidePower(BlockState state) {
    	return type == 3;
    }
    
    @Override
    public int getWeakPower(BlockState blockState, IBlockReader world, BlockPos pos, Direction side) {
    	TileEntity te = world.getTileEntity(pos);
    	
    	if (te instanceof TileEntityExitSignWithSensor) {
    		boolean isPowered = ((TileEntityExitSignWithSensor) te).isPowered();

    		return isPowered ? 15:0;
    	}
    	
    	return 0;
    }
    
    @Override
    public int getStrongPower(BlockState blockState, IBlockReader world, BlockPos pos, Direction side) {
    	TileEntity te = world.getTileEntity(pos);
    	
    	if (te instanceof TileEntityExitSignWithSensor) {
    		boolean isPowered = ((TileEntityExitSignWithSensor) te).isPowered();
    		Direction facing = getRotation(blockState);
    		
    		return isPowered ? (facing==side.getOpposite()?15:0):0;
    	}
    	
    	return 0;
    }
	
    ///////////////////
    /// BoundingBox
    ///////////////////
	protected final static AxisAlignedBB[] 
			boundingBoxes0 = createAABB(0.2F, 0, 0, 0.8F, 0.1F, 1), 
			boundingBoxes1 = createAABB(0, 0, 0.05F, 1, 0.65F, 0.95F), 
			boundingBoxes2 = createAABB(0, 0, 0.05F, 0.85F, 0.55F, 0.95F);
	protected final static VoxelShape[] hitboxes = new VoxelShape[4];
	
    static {
    	for (int i=0; i<4; i++) {
    		Direction side = Direction.byHorizontalIndex((4-i)&3);
    		hitboxes[i] = VoxelShapes.or(
	    		VoxelShapes.create(RayTraceHelper.createAABB(side, 0, 0, 0.055F, 1, 0.1F, 0.945F)),
	    		VoxelShapes.create(RayTraceHelper.createAABB(side, 0.8F, 0, 0.055F, 1, 0.25F, 0.945F)),
	    		VoxelShapes.create(RayTraceHelper.createAABB(side, 0, 0, 0.055F, 1, 0.25F, 0.125F)),
	    		VoxelShapes.create(RayTraceHelper.createAABB(side, 0, 0, 0.055F, 0.33F, 0.65F, 0.125F)),
	    		VoxelShapes.create(RayTraceHelper.createAABB(side, 0, 0, 0.875F, 1, 0.25F, 0.945F)),
	    		VoxelShapes.create(RayTraceHelper.createAABB(side, 0, 0, 0.875F, 0.33F, 0.65F, 0.945F)),
	    		VoxelShapes.create(RayTraceHelper.createAABB(side, 0F, 0.55F, 0.055F, 0.33F, 0.65F, 0.945F)),
	    		VoxelShapes.create(RayTraceHelper.createAABB(side, 0F, 0, 0.055F, 0.25F, 0.65F, 0.945F))
    		);
    	}
    }
	
    @Override
    public VoxelShape getBoundingShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
    	if (type == 1)
    		return VoxelShapes.create(boundingBoxes1[getRotationInt(state)]);
    	else
    	   	return this.getShape(state, world, pos, context);
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
    	int rotation = getRotationInt(state);
    	if (type == 0)
    		return VoxelShapes.create(boundingBoxes0[rotation]);
    	else if (type == 1) 
        	return hitboxes[getRotationInt(state)];
    	else if (type == 2)
    		return VoxelShapes.create(boundingBoxes2[getRotationInt(state)]);

    	return super.getShape(state, world, pos, context);
    }
}
