package org.ztouhou.mcmod.decoration;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.ztouhou.mcmod.decoration.blocks.BlockDoorBase;
import org.ztouhou.mcmod.decoration.blocks.BlockMisc;
import org.ztouhou.mcmod.decoration.blocks.BlockSign1;
import org.ztouhou.mcmod.decoration.blocks.BlockSign2;
import org.ztouhou.mcmod.decoration.blocks.BlockSign3;
import org.ztouhou.mcmod.decoration.blocks.BlockSign4;
import org.ztouhou.mcmod.decoration.blocks.BlockSign5;
import org.ztouhou.mcmod.decoration.blocks.BlockSign6;
import org.ztouhou.mcmod.decoration.blocks.tileentity.ContainerFireExtinguisherBox;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEnityUrinals;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityExitSignWithSensor;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityFireExtinguisherBox;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.IForgeRegistry;
import rikka.librikka.block.BlockBase;
import rikka.librikka.container.ContainerHelper;
import rikka.librikka.tileentity.TileEntityHelper;

public class BlockRegistry {
	public final static List<Class<? extends Container>> registeredGuiContainers = new LinkedList<>();
	private final static Set<Item> blockItems = new LinkedHashSet<>();

	public static BlockSign1[] blockSign1;
	public static BlockSign2[] blockSign2;
	public static BlockSign3[] blockSign3;
	public static BlockSign4[] blockSign4;
	public static BlockSign5 blockSign5;
	public static BlockSign6[] blockSign6;
	
	public static BlockDoorBase blockDoor1, blockDoor2, blockDoor3, blockDoor4;
	public static BlockMisc[] blockMisc;
	
	public static void initBlocks() {
		blockSign1 = BlockSign1.create();
		blockSign2 = BlockSign2.create();
		blockSign3 = BlockSign3.create();
		blockSign4 = BlockSign4.create();
		blockSign5 = new BlockSign5();
		blockSign6 = BlockSign6.create();
		
		blockDoor1 = new BlockDoorBase.Door1();
		blockDoor2 = new BlockDoorBase.Door2();
		blockDoor3 = new BlockDoorBase.Door3();
		blockDoor4 = new BlockDoorBase.Door4();
		
		blockMisc = BlockMisc.create();
	}
	
	public static void registerBlocks(IForgeRegistry<Block> registry) {
		registerBlocks(registry, blockSign1);
		registerBlocks(registry, blockSign2);
		registerBlocks(registry, blockSign3);
		registerBlocks(registry, blockSign4);
		registerBlocks(registry, blockSign5);
		registerBlocks(registry, blockSign6);
		registerBlocks(registry, blockMisc);
		
		registry.registerAll(
				blockDoor1,
				blockDoor2,
				blockDoor3,
				blockDoor4
				);
	}
	
	public static void registerBlockItems(IForgeRegistry<Item> registry) {
		blockItems.forEach(registry::register);
		
		registry.registerAll(
				blockDoor1.itemBlock,
				blockDoor2.itemBlock,
				blockDoor3.itemBlock,
				blockDoor4.itemBlock
				);
	}
	
	public static void registerTileEntities(final IForgeRegistry<TileEntityType<?>> registry) {
		TileEntityHelper.registerTileEntity(registry, TileEntityLED12864.class, blockSign4);
		TileEntityHelper.registerTileEntity(registry, TileEnityUrinals.class, blockSign4);
		TileEntityHelper.registerTileEntity(registry, TileEntityFireExtinguisherBox.class, blockSign4);
		TileEntityHelper.registerTileEntity(registry, TileEntityExitSignWithSensor.class, blockSign4);
	}
	
	public static void registerContainers(final IForgeRegistry<ContainerType<?>> registry) {
		ContainerHelper.register(registry, ContainerFireExtinguisherBox.class);
		registeredGuiContainers.add(ContainerFireExtinguisherBox.class);
	}
    
    private static void registerBlocks(IForgeRegistry<Block> registry, BlockBase... blocks) {
		registry.registerAll(blocks);
		
		for (BlockBase block: blocks)
    		blockItems.add(block.asItem());
    }
}
