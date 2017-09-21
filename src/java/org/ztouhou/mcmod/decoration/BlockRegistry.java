package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.blocks.BlockDoorBase;
import org.ztouhou.mcmod.decoration.blocks.BlockMisc;
import org.ztouhou.mcmod.decoration.blocks.BlockSign1;
import org.ztouhou.mcmod.decoration.blocks.BlockSign2;
import org.ztouhou.mcmod.decoration.blocks.BlockSign3;
import org.ztouhou.mcmod.decoration.blocks.BlockSign4;
import org.ztouhou.mcmod.decoration.blocks.BlockSign5;
import org.ztouhou.mcmod.decoration.blocks.BlockSign6;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEnityUrinals;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityExitSignWithSensor;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityFireExtinguisherBox;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import rikka.librikka.block.BlockBase;

public class BlockRegistry {
	public static BlockSign1 blockSign1;
	public static BlockSign2 blockSign2;
	public static BlockSign3 blockSign3;
	public static BlockSign4 blockSign4;
	public static BlockSign5 blockSign5;
	public static BlockSign6 blockSign6;
	
	public static BlockDoorBase blockDoor1, blockDoor2, blockDoor3, blockDoor4;
	public static BlockMisc blockMisc;
	
	public static void initBlocks() {
		blockSign1 = new BlockSign1();
		blockSign2 = new BlockSign2();
		blockSign3 = new BlockSign3();
		blockSign4 = new BlockSign4();
		blockSign5 = new BlockSign5();
		blockSign6 = new BlockSign6();
		
		blockDoor1 = new BlockDoorBase.Door1();
		blockDoor2 = new BlockDoorBase.Door2();
		blockDoor3 = new BlockDoorBase.Door3();
		blockDoor4 = new BlockDoorBase.Door4();
		
		blockMisc = new BlockMisc();
	}
	
	public static void registerBlocks(IForgeRegistry registry, boolean isItemBlock) {
		registerBlocks(registry, isItemBlock,
				blockSign1,
				blockSign2,
				blockSign3,
				blockSign4,
				blockSign5,
				blockSign6,
				
				blockMisc
				);
		
/*		if (isItemBlock) {
			registry.registerAll(
					blockDoor1.itemBlock,
					blockDoor2.itemBlock,
					blockDoor3.itemBlock,
					blockDoor4.itemBlock
					);
		} else {
			registry.registerAll(
					blockDoor1,
					blockDoor2,
					blockDoor3,
					blockDoor4
					);
		}*/
	}
	
	public static void registerTileEntities() {
		registerTile(TileEntityLED12864.class);
		registerTile(TileEnityUrinals.class);
		registerTile(TileEntityFireExtinguisherBox.class);
		registerTile(TileEntityExitSignWithSensor.class);
	}
	
    
    private static void registerBlocks(IForgeRegistry registry, boolean isItemBlock, BlockBase... blocks) {
    	if (isItemBlock) {
        	for (BlockBase block: blocks)
        		registry.register(block.itemBlock);
    	} else {
    		registry.registerAll(blocks);
    	}
    }
	
    private static void registerTile(Class<? extends TileEntity> teClass) {
        String registryName = teClass.getName();
        registryName = registryName.substring(registryName.lastIndexOf(".") + 1);
        registryName = Decoration.MODID + ":" + registryName;
        GameRegistry.registerTileEntity(teClass, registryName);
    }
}
