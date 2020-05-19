package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.block.material.Material;

import org.ztouhou.mcmod.decoration.Decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rikka.librikka.block.BlockBase;

public class BlockMisc extends BlockBase {
	private final static String[] subNames = new String[] {"fencelight", "fencelight_small", "cellinglight"};
	
	public final int meta;
	private BlockMisc(int meta) {
		super(subNames[meta], Block.Properties.create(Material.GLASS).hardnessAndResistance(1.0F, 5.0F), Decoration.itemGroup);
		this.meta = meta;
	}
	
	public static BlockMisc[] create() {
		BlockMisc[] ret = new BlockMisc[subNames.length];
		for (int i=0; i<subNames.length; i++) {
			ret[i] = new BlockMisc(i);
		}
		return ret;
	}

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return boundingBoxes[meta];
    }
    
    @Override
    public int getLightValue(BlockState state) {
    	return meta < 4 ? 15 : 0;
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
    private final static VoxelShape[] boundingBoxes;
    
    static {
    	boundingBoxes = new VoxelShape[subNames.length];
    	boundingBoxes[0] = VoxelShapes.create(0.1875, 0, 0.1875, 0.8125, 0.9375, 0.8125);
    	boundingBoxes[1] = VoxelShapes.create(0.375, 0, 0.375, 0.625, 0.25, 0.625);
    	boundingBoxes[2] = VoxelShapes.create(0, 0.875, 0, 1, 1, 1);
    }
}
