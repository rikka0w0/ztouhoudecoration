package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;
import org.ztouhou.mcmod.decoration.client.CustomStateMapper;
import org.ztouhou.mcmod.decoration.client.LED12864Render;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
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
		
		loader.registerInventoryIcon(ItemRegistry.fireExtinguisher);
		loader.registerInventoryIcon(ItemRegistry.led12864isp);
	}
	
	@Override
	public void init() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLED12864.class, new LED12864Render());
	}
}
