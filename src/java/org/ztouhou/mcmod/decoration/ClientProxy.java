package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.client.CustomStateMapper;

import rikka.librikka.model.loader.AdvancedModelLoader;

public class ClientProxy extends CommonProxy{
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
	}
	
	@Override
	public void init() {
		
	}
}
