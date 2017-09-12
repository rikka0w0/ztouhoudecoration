package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;
import org.ztouhou.mcmod.decoration.client.CustomStateMapper;
import org.ztouhou.mcmod.decoration.client.LED12864Render;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import rikka.librikka.model.loader.AdvancedModelLoader;

public class ClientProxy extends CommonProxy{
    @Override
    public IThreadListener getClientThread() {
        return Minecraft.getMinecraft();
    }
	
	@Override
	public void preInit() {
		AdvancedModelLoader loader = new AdvancedModelLoader(Decoration.MODID);
		
		//Blocks
		CustomStateMapper signStateMapper = new CustomStateMapper(Decoration.MODID);
		loader.registerModelLoader(signStateMapper);
		loader.registerInventoryIcon(BlockRegistry.blockSign1);
		signStateMapper.register(BlockRegistry.blockSign1);
		loader.registerInventoryIcon(BlockRegistry.blockSign2);
		signStateMapper.register(BlockRegistry.blockSign2);
		loader.registerInventoryIcon(BlockRegistry.blockSign3);
		signStateMapper.register(BlockRegistry.blockSign3);
		loader.registerInventoryIcon(BlockRegistry.blockSign4);
		signStateMapper.register(BlockRegistry.blockSign4);
		loader.registerInventoryIcon(BlockRegistry.blockSign5);
		signStateMapper.register(BlockRegistry.blockSign5);
		
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
	}
	
	@Override
	public void init() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLED12864.class, new LED12864Render());
	}
}
