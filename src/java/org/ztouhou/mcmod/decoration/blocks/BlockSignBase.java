package org.ztouhou.mcmod.decoration.blocks;

import java.lang.ref.WeakReference;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.RayTraceHelper;
import rikka.librikka.block.ISubBlock;
import rikka.librikka.block.BlockBase;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBlockBase;
import rikka.librikka.properties.Properties;
import rikka.librikka.properties.UnlistedPropertyRef;

public abstract class BlockSignBase extends BlockBase implements ISimpleTexture, ISubBlock {
	public final String texturePrefix;
	
	public BlockSignBase(String unlocalizedName, Material material) {
		super(unlocalizedName, material, ItemBlockBase.class);
		
		this.texturePrefix = unlocalizedName;
		setCreativeTab(CreativeTabs.REDSTONE);
	}

    @SideOnly(Side.CLIENT)
    public String getIconName(int damage) {
    	return texturePrefix + "_" + ((ISubBlock)this).getSubBlockUnlocalizedNames()[damage];
    }	
	
    protected boolean hasTileExState() {return false;}
    
    ///////////////////////////////
    ///BlockStates
    ///////////////////////////////
    @Override
    protected final BlockStateContainer createBlockState() {
    	IProperty[] properties = new IProperty[]{Properties.type2bit, Properties.facing2bit};
    	if (hasTileExState()) {
    		return new ExtendedBlockState(this, properties, new IUnlistedProperty[] {UnlistedPropertyRef.propertyTile});
    	}else {
    		return new BlockStateContainer(this, properties);
    	}

    }

    @Override
    public final IBlockState getStateFromMeta(int meta) {
        meta &= 15;
        int rotation = meta&3;
        int type = (meta>>2)&3; 
        return getDefaultState()
        		.withProperty(Properties.type2bit, type)
        		.withProperty(Properties.facing2bit, rotation);
    }

    @Override
    public final int getMetaFromState(IBlockState state) {
    	int rotation = state.getValue(Properties.facing2bit);
    	int type = state.getValue(Properties.type2bit);
    	int meta = (type<<2) | rotation;
        return meta & 15;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state;
    }
    
    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state instanceof IExtendedBlockState) {
            IExtendedBlockState retval = (IExtendedBlockState) state;

            TileEntity te = world.getTileEntity(pos);
            
            if (te != null)
            	retval = retval.withProperty(UnlistedPropertyRef.propertyTile, new WeakReference<>(te));
            
            return retval;
        }
        return state;
    }
    //////////////////////////////////////
    /////Item drops and Block activities
    //////////////////////////////////////
    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(Properties.type2bit);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int damage, EntityLivingBase placer) {
        IBlockState state = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, damage, placer);
        int rotation = 4 - MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int type = damage;
        return state.withProperty(Properties.type2bit, type)
                .withProperty(Properties.facing2bit, rotation);
    }
    
    ////////////////////////////////////
    /// Rendering
    ////////////////////////////////////
    //This will tell minecraft not to render any side of our cube.
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    //And this tell it that you can see through this block, and neighbor blocks should be rendered.
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }
    
    ///////////////////
    /// BoundingBox
    ///////////////////
    protected final static AxisAlignedBB[] boundingBoxes, boundingBoxesLarge;
    
    static {
    	boundingBoxes = new AxisAlignedBB[4];
    	boundingBoxes[0] = RayTraceHelper.createAABB(EnumFacing.SOUTH, 0.2814F, 0, 0, 0.7189F, 0.1F, 1);
    	boundingBoxes[1] = RayTraceHelper.createAABB(EnumFacing.EAST, 0.2814F, 0, 0, 0.7189F, 0.1F, 1);
    	boundingBoxes[2] = RayTraceHelper.createAABB(EnumFacing.NORTH, 0.2814F, 0, 0, 0.7189F, 0.1F, 1);
    	boundingBoxes[3] = RayTraceHelper.createAABB(EnumFacing.WEST, 0.2814F, 0, 0, 0.7189F, 0.1F, 1);
    	
    	boundingBoxesLarge = new AxisAlignedBB[4];
    	boundingBoxesLarge[0] = RayTraceHelper.createAABB(EnumFacing.SOUTH, 0, 0, 0, 1, 0.1F, 1);
    	boundingBoxesLarge[1] = RayTraceHelper.createAABB(EnumFacing.EAST, 0, 0, 0, 1, 0.1F, 1);
    	boundingBoxesLarge[2] = RayTraceHelper.createAABB(EnumFacing.NORTH, 0, 0, 0, 1, 0.1F, 1);
    	boundingBoxesLarge[3] = RayTraceHelper.createAABB(EnumFacing.WEST, 0, 0, 0, 1, 0.1F, 1);
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	int rotation = state.getValue(Properties.facing2bit);
    	return boundingBoxes[rotation];
    }
}
