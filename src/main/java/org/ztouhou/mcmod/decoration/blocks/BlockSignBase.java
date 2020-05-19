package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;

import org.ztouhou.mcmod.decoration.Decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rikka.librikka.RayTraceHelper;
import rikka.librikka.Utils;
import rikka.librikka.block.BlockBase;

public abstract class BlockSignBase extends BlockBase {	
	public BlockSignBase(String unlocalizedName, Block.Properties props) {
		super(unlocalizedName, props, Decoration.itemGroup);
	}
	
	public abstract int getType();
    
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING);
	}
	
    //////////////////////////////////////
    /////Item drops and Block activities
    //////////////////////////////////////
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
    	BlockState state = super.getStateForPlacement(context);
    	PlayerEntity placer = context.getPlayer();
    	Direction facing = Utils.getPlayerSightHorizontal(placer);
		if (state.has(BlockStateProperties.HORIZONTAL_FACING))
			return state.with(BlockStateProperties.HORIZONTAL_FACING, facing);
		else
			return state;
    }
    
    ////////////////////////////////////
    /// Rendering
    ////////////////////////////////////
    @Override
    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }


    ///////////////////
    /// BoundingBox
    ///////////////////
    protected final static AxisAlignedBB[] 
    		boundingBoxes = createAABB(0.2814F, 0, 0, 0.7189F, 0.1F, 1), 
    		boundingBoxesLarge = createAABB(0, 0, 0, 1, 0.1F, 1), 
    		boundingBoxesWarningSign = createAABB(0.125F, 0, 0, 0.875F, 1F/16F, 1);
    
    protected static AxisAlignedBB[] createAABB(float xStart, float yStart, float zStart, float xEnd, float yEnd, float zEnd) {
    	AxisAlignedBB[] boundingBoxes = new AxisAlignedBB[4];
    	boundingBoxes[0] = RayTraceHelper.createAABB(Direction.SOUTH, xStart, yStart, zStart, xEnd, yEnd, zEnd);
    	boundingBoxes[1] = RayTraceHelper.createAABB(Direction.EAST, xStart, yStart, zStart, xEnd, yEnd, zEnd);
    	boundingBoxes[2] = RayTraceHelper.createAABB(Direction.NORTH, xStart, yStart, zStart, xEnd, yEnd, zEnd);
    	boundingBoxes[3] = RayTraceHelper.createAABB(Direction.WEST, xStart, yStart, zStart, xEnd, yEnd, zEnd);
    	return boundingBoxes;
    }
    
    public final static Direction getRotation(BlockState state) {
    	return state.get(BlockStateProperties.HORIZONTAL_FACING);
    }
    
    public final static int getRotationInt(BlockState state) {
    	return (4-getRotation(state).getHorizontalIndex()) & 3;
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
    	int rotation = getRotationInt(state);
    	return VoxelShapes.create(boundingBoxes[rotation]);
    }
}
