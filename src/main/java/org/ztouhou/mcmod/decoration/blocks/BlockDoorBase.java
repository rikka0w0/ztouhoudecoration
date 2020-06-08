package org.ztouhou.mcmod.decoration.blocks;

import org.ztouhou.mcmod.decoration.Decoration;

import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

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
	}
	
	public final ItemBlockDoor itemBlock;
	
	public BlockDoorBase(String unlocalizedName, Block.Properties props) {
		super(props);
		this.itemBlock = new ItemBlockDoor(this);
		
        setRegistryName(unlocalizedName);                //Key!

        this.itemBlock.setRegistryName(this.getRegistryName());
	}
}
