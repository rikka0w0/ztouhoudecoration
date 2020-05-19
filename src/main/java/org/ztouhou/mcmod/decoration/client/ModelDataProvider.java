package org.ztouhou.mcmod.decoration.client;


import org.ztouhou.mcmod.decoration.BlockRegistry;
import org.ztouhou.mcmod.decoration.Decoration;
import org.ztouhou.mcmod.decoration.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import rikka.librikka.model.loader.ISimpleItemDataProvider;

public class ModelDataProvider extends BlockStateProvider implements ISimpleItemDataProvider {
	private final ExistingFileHelper exfh;
	public ModelDataProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, Decoration.MODID, exFileHelper);
        this.exfh = exFileHelper;
	}
	
	/**
	 * Both the block model and item model are dynamic
	 * @param block
	 */
	private void registerDynamic2(Block... blocks) {
		for (Block block: blocks) {
			VariantBlockStateBuilder builder = getVariantBuilder(block);
			String namespace = block.getRegistryName().getNamespace();
			String blockName =  block.getRegistryName().getPath();
			final ModelFile modelFile = new ModelFile.ExistingModelFile(mcLoc("block/torch"), exfh);
			builder.forAllStates((blockstate)->ConfiguredModel.builder().modelFile(modelFile).build());
		}
		registerSimpleItem(blocks);
	}

	@Override
	protected void registerStatesAndModels() {
		registerDynamic2(BlockRegistry.blockSign1);
		registerDynamic2(BlockRegistry.blockSign2);
		registerDynamic2(BlockRegistry.blockSign3);
		registerDynamic2(BlockRegistry.blockSign4);
		registerDynamic2(BlockRegistry.blockSign5);
		registerDynamic2(BlockRegistry.blockSign6);
		registerDynamic2(BlockRegistry.blockMisc);
		
		registerDynamic2(BlockRegistry.blockDoor1);
		registerDynamic2(BlockRegistry.blockDoor2);
		registerDynamic2(BlockRegistry.blockDoor3);
		registerDynamic2(BlockRegistry.blockDoor4);
		
		registerSimpleItem(ItemRegistry.fireExtinguisher);
		registerSimpleItem(ItemRegistry.led12864isp);
	}
}
