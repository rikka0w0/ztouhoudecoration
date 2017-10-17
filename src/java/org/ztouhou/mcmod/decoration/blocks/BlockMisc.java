package org.ztouhou.mcmod.decoration.blocks;

import org.ztouhou.mcmod.decoration.CreativeTab;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.block.ISubBlock;
import rikka.librikka.block.MetaBlock;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBlockBase;

public class BlockMisc extends MetaBlock implements ISimpleTexture {
	private final static String[] subNames = new String[] {"fencelight", "fencelight_small", "cellinglight"};
	
	public BlockMisc() {
		super("misc", subNames, Material.GLASS, ItemBlockBase.class);
		
		setCreativeTab(CreativeTab.instance);
        setHardness(1.0F);
        setResistance(5.0F);
	}

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	int type = getMetaFromState(state);
        return boundingBoxes[type];
    }
    
    @Override
    public int getLightValue(IBlockState state) {
    	int type = getMetaFromState(state);
    	return type < 4 ? 15 : 0;
    }
    ////////////////////////////////////
    /// Rendering
    ////////////////////////////////////
	@Override
    @SideOnly(Side.CLIENT)
    public String getIconName(int damage) {
    	return "misc_" + ((ISubBlock)this).getSubBlockUnlocalizedNames()[damage];
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
    private final static AxisAlignedBB[] boundingBoxes;
    
    static {
    	boundingBoxes = new AxisAlignedBB[subNames.length];
    	boundingBoxes[0] = new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.9375, 0.8125);
    	boundingBoxes[1] = new AxisAlignedBB(0.375, 0, 0.375, 0.625, 0.25, 0.625);
    	boundingBoxes[2] = new AxisAlignedBB(0, 0.875, 0, 1, 1, 1);
    }
}
