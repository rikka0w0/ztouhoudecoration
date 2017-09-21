package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;
import org.ztouhou.mcmod.decoration.client.CustomStateMapper;
import org.ztouhou.mcmod.decoration.client.LED12864Render;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import rikka.librikka.model.loader.AdvancedModelLoader;

@Mod.EventBusSubscriber(modid = Decoration.MODID, value = Side.CLIENT)
public class ClientRegistrationHandler {
	@SubscribeEvent
	public static void registerModel(ModelRegistryEvent event) {
		AdvancedModelLoader loader = new AdvancedModelLoader(Decoration.MODID);
		
		//Blocks
		CustomStateMapper customStateMapper = new CustomStateMapper(Decoration.MODID);
		loader.registerModelLoader(customStateMapper);
		loader.registerInventoryIcon(BlockRegistry.blockSign1);
		customStateMapper.register(BlockRegistry.blockSign1);
		loader.registerInventoryIcon(BlockRegistry.blockSign2);
		customStateMapper.register(BlockRegistry.blockSign2);
		loader.registerInventoryIcon(BlockRegistry.blockSign3);
		customStateMapper.register(BlockRegistry.blockSign3);
		loader.registerInventoryIcon(BlockRegistry.blockSign4);
		customStateMapper.register(BlockRegistry.blockSign4);
		loader.registerInventoryIcon(BlockRegistry.blockSign5);
		customStateMapper.register(BlockRegistry.blockSign5);
		loader.registerInventoryIcon(BlockRegistry.blockSign6);
		customStateMapper.register(BlockRegistry.blockSign6);
		
		//Items
		loader.registerInventoryIcon(ItemRegistry.fireExtinguisher);
		loader.registerInventoryIcon(ItemRegistry.led12864isp);
		
		//Doors
		IStateMapper doorStateMapper = (new StateMap.Builder()).ignore(new IProperty[] {BlockDoor.POWERED}).build();
		loader.registerInventoryIcon(BlockRegistry.blockDoor1.itemBlock);
		ModelLoader.setCustomStateMapper(BlockRegistry.blockDoor1, doorStateMapper);
		loader.registerInventoryIcon(BlockRegistry.blockDoor2.itemBlock);
		ModelLoader.setCustomStateMapper(BlockRegistry.blockDoor2, doorStateMapper);
		loader.registerInventoryIcon(BlockRegistry.blockDoor3.itemBlock);
		ModelLoader.setCustomStateMapper(BlockRegistry.blockDoor3, doorStateMapper);
		loader.registerInventoryIcon(BlockRegistry.blockDoor4.itemBlock);
		ModelLoader.setCustomStateMapper(BlockRegistry.blockDoor4, doorStateMapper);
		
		//Misc
		customStateMapper.register(BlockRegistry.blockMisc);
		loader.registerInventoryIcon(BlockRegistry.blockMisc);
	}
	
	public static void registerTileEntityRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLED12864.class, new LED12864Render());
	}
}
