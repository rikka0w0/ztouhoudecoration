package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import rikka.librikka.properties.Properties;

public class BlockSign6 extends BlockSignBase {
	private final String[] subNames = new String[] {"hv", "authorizedonly", "keepout", "onswitchon"};

	public BlockSign6() {
		super("sign6", Material.ROCK);

        setHardness(1.0F);
        setResistance(5.0F);
	}

	@Override
	public String[] getSubBlockUnlocalizedNames() {
		return subNames;
	}

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	int rotation = state.getValue(Properties.facing2bit);
    	return boundingBoxesWarningSign[rotation];
    }
}
