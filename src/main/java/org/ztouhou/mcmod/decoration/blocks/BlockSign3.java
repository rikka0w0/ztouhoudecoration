package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import rikka.librikka.Utils;

public class BlockSign3 extends BlockSimpleSign {
	public static String[] subNames = new String[] {"broadcast", "wirebox", "hydrant", "radiation"};
	
	public final int meta;
	private BlockSign3(int meta) {
		super("sign_" + subNames[meta]);
		this.meta = meta;
	}

	public static BlockSign3[] create() {
		BlockSign3[] ret = new BlockSign3[subNames.length];
		for (int i=0; i<subNames.length; i++) {
			ret[i] = new BlockSign3(i);
		}
		return ret;
	}
	
	@Override
	public int getTextureOffset() {
		return 0;
	}
	
	@Override
	public boolean isLargeSign() {
		return true;
	}
	
	@Override
	public int getType() {
		return meta;
	}
	
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
    	int rotation = getRotationInt(state);
    	return VoxelShapes.create(boundingBoxesLarge[rotation]);
    }
    
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rtResult) {
        ItemStack held = player.getHeldItem(hand);
        
        if (meta == 2) {
            if(held != null && !held.isEmpty() && held.isItemEqual(new ItemStack(Items.BUCKET))) {
            	player.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
            } else {
            	if (world.isRemote)
            		Utils.chatWithLocalization(player, "msg.ztouhoudecoration.sign_hydrant.needbucket");
            }
            
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.FAIL;
    }
}
