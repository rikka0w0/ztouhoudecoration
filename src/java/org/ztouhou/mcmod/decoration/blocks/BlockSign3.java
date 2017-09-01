package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import rikka.librikka.Properties;

public class BlockSign3 extends BlockSimpleSign {
	public static String[] subNames = new String[] {"broadcast", "wirebox", "hydrant", "radiation"};
	
	public BlockSign3() {
		super("sign3");
	}
	
	@Override
	public void beforeRegister() {
		setCreativeTab(CreativeTabs.REDSTONE);
	}

	@Override
	public String[] getSubBlockUnlocalizedNames() {
		return subNames;
	}
	
	@Override
	public int getTextureOffset() {
		return 0;
	}
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	int rotation = state.getValue(Properties.facing2bit);
        return boundingBoxesLarge[rotation];
    }
}
