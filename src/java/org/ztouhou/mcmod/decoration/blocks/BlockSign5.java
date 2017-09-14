package org.ztouhou.mcmod.decoration.blocks;

import java.util.List;

import javax.annotation.Nullable;

import org.ztouhou.mcmod.decoration.CreativeTab;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import rikka.librikka.block.BlockBase;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBlockBase;
import rikka.librikka.properties.Properties;

public class BlockSign5 extends BlockBase implements ISimpleTexture {
	public BlockSign5() {
		super("sign5", Material.GLASS, ItemBlockBase.class);
		
		setCreativeTab(CreativeTab.instance);
        setHardness(1.0F);
        setResistance(5.0F);
	}

	@Override
	public String getIconName(int damage) {
		return "sign5_wetfloor";
	}
	
    ///////////////////////////////
    ///BlockStates
    ///////////////////////////////
    @Override
    protected final BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,
                new IProperty[]{Properties.type1bit, Properties.facing3bit});
    }

    @Override
    public final IBlockState getStateFromMeta(int meta) {
        meta &= 15;
        int rotation = meta&7;
        int type = (meta>>3)&1;
        return getDefaultState().withProperty(Properties.facing3bit, rotation);
    }

    @Override
    public final int getMetaFromState(IBlockState state) {
    	int rotation = state.getValue(Properties.facing3bit);
    	int type = state.getValue(Properties.type1bit);
    	int meta = (type<<3)&8 | rotation;
        return meta & 15;
    }
    
    //////////////////////////////////////
    /////Item drops and Block activities
    //////////////////////////////////////
    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(Properties.type1bit);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int damage, EntityLivingBase placer) {
        IBlockState state = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, damage, placer);
        int rotation = 8 - MathHelper.floor(placer.rotationYaw * 8.0F / 360.0F + 0.5D) & 7;
        int type = damage;
        return state.withProperty(Properties.type1bit, type)
                .withProperty(Properties.facing3bit, rotation);
    }
    
    ///////////////////
    /// BoundingBox
    ///////////////////
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	return new AxisAlignedBB(0.25F, 0, 0.25F, 0.75F, 1, 0.75F);
    }
    
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean magicBool){
    	
    }
    
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }
}
