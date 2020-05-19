package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockSign6 extends BlockSignBase {
	private final static String[] subNames = new String[] {"hv", "authorizedonly", "keepout", "onswitchon"};

	public final int meta;
	private BlockSign6(int meta) {
		super("sign_" + subNames[meta], 
				Block.Properties.create(Material.ROCK).hardnessAndResistance(1.0F, 5.0F));
		this.meta = meta;
	}

	@Override
	public int getType() {
		return meta;
	}

	public static BlockSign6[] create() {
		BlockSign6[] ret = new BlockSign6[subNames.length];
		for (int i=0; i<subNames.length; i++) {
			ret[i] = new BlockSign6(i);
		}
		return ret;
	}

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
    	int rotation = getRotationInt(state);
    	return VoxelShapes.create(boundingBoxesWarningSign[rotation]);
    }
}
