package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.blocks.BlockSign1;
import org.ztouhou.mcmod.decoration.blocks.BlockSign2;
import org.ztouhou.mcmod.decoration.blocks.BlockSign3;
import org.ztouhou.mcmod.decoration.blocks.BlockSign4;
import org.ztouhou.mcmod.decoration.blocks.BlockSign5;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityFireExtinguisherBox;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry {
	public static BlockSign1 blockSign1;
	public static BlockSign2 blockSign2;
	public static BlockSign3 blockSign3;
	public static BlockSign4 blockSign4;
	public static BlockSign5 blockSign5;
	
	public static void registerBlocks() {
		blockSign1 = new BlockSign1();
		blockSign2 = new BlockSign2();
		blockSign3 = new BlockSign3();
		blockSign4 = new BlockSign4();
		blockSign5 = new BlockSign5();
	}
	
	public static void registerTileEntities() {
		registerTile(TileEntityFireExtinguisherBox.class);
		registerTile(TileEntityLED12864.class);
	}
	
    private static void registerTile(Class<? extends TileEntity> teClass) {
        String registryName = teClass.getName();
        registryName = registryName.substring(registryName.lastIndexOf(".") + 1);
        registryName = Decoration.MODID + ":" + registryName;
        GameRegistry.registerTileEntity(teClass, registryName);
    }
}
