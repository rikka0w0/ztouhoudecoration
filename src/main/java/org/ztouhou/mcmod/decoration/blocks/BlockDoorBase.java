package org.ztouhou.mcmod.decoration.blocks;

import java.util.Random;

import org.ztouhou.mcmod.decoration.Decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockDoorBase extends DoorBlock {
	public static class Door1 extends BlockDoorBase {
		public Door1() {
			super("door1", Block.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD));
		}
	}
	
	public static class Door2 extends BlockDoorBase {
		public Door2() {
			super("door2", Block.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD));
		}
	}
	
	public static class Door3 extends BlockDoorBase {
		public Door3() {
			super("door3", Block.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD));
		}
	}
	
	public static class Door4 extends BlockDoorBase {
		public Door4() {
			super("door4", Block.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD));
		}
	}
	
	public static class ItemBlockDoor extends BlockItem {		
		public ItemBlockDoor(BlockDoorBase blockDoor) {
			super(blockDoor, (new Item.Properties()).group(Decoration.itemGroup));
		}
		
//	    /**
//	     * Called when a Block is right-clicked with this Item
//	     */
//		@Override
//	    public ActionResultType onItemUse(ItemUseContext context) {
//			Direction facing = context.getFace();
//			World world = context.getWorld();
//			BlockPos pos = context.getPos();
//			PlayerEntity player = context.getPlayer();
//			Hand hand = context.getHand();
//			
//	        if (facing != Direction.UP)
//	        {
//	            return ActionResultType.FAIL;
//	        }
//	        else
//	        {
//	            BlockState iblockstate = world.getBlockState(pos);
//	            Block block = iblockstate.getBlock();
//
//	            if (!iblockstate.isReplaceable(new BlockItemUseContext(context)))
//	            {
//	                pos = pos.offset(facing);
//	            }
//
//	            ItemStack itemstack = player.getHeldItem(hand);
//
//	            if (player.canPlayerEdit(pos, facing, itemstack) && this.block.canPlaceBlockAt(worldIn, pos))
//	            {
//	                Direction Direction = Direction.fromAngle((double)player.rotationYaw);
//	                int i = Direction.getFrontOffsetX();
//	                int j = Direction.getFrontOffsetZ();
//	                boolean flag = i < 0 && hitZ < 0.5F || i > 0 && hitZ > 0.5F || j < 0 && hitX > 0.5F || j > 0 && hitX < 0.5F;
//	                ItemDoor.placeDoor(worldIn, pos, Direction, this.block, flag);
//	                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
//	                worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
//	                itemstack.shrink(1);
//	                return EnumActionResult.SUCCESS;
//	            }
//	            else
//	            {
//	                return EnumActionResult.FAIL;
//	            }
//	        }
//	    }
	}
	
	public final ItemBlockDoor itemBlock;
	
	public BlockDoorBase(String unlocalizedName, Block.Properties props) {
		super(props);
		this.itemBlock = new ItemBlockDoor(this);
		
        setRegistryName(unlocalizedName);                //Key!

        this.itemBlock.setRegistryName(this.getRegistryName());
	}
}
