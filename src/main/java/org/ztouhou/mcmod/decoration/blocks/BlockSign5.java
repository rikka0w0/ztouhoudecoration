package org.ztouhou.mcmod.decoration.blocks;

import org.ztouhou.mcmod.decoration.Decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rikka.librikka.DirHorizontal8;
import rikka.librikka.block.BlockBase;

public class BlockSign5 extends BlockBase {
	public BlockSign5() {
		super("sign_wetfloor", Block.Properties.create(Material.GLASS).hardnessAndResistance(1.0F, 5.0F).doesNotBlockMovement(), Decoration.itemGroup);
	}
	
    ///////////////////////////////
    ///BlockStates
    ///////////////////////////////
    @Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    	builder.add(DirHorizontal8.prop);
	}
    
    //////////////////////////////////////
    /////Item drops and Block activities
    //////////////////////////////////////
	
	
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
    	PlayerEntity placer = context.getPlayer();
        return this.getDefaultState().with(DirHorizontal8.prop, DirHorizontal8.fromSight(placer));
    }
    
    ///////////////////
    /// BoundingBox
    ///////////////////
    private final static VoxelShape shape = VoxelShapes.create(0.25F, 0, 0.25F, 0.75F, 1, 0.75F);
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
    	return shape;
    }

    @Override
    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }
}
