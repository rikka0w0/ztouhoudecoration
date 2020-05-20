package org.ztouhou.mcmod.decoration;

import java.util.HashMap;
import java.util.Map;

import org.ztouhou.mcmod.decoration.blocks.BlockSignBase;
import org.ztouhou.mcmod.decoration.blocks.BlockSimpleSign;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;
import org.ztouhou.mcmod.decoration.client.LED12864Render;
import org.ztouhou.mcmod.decoration.client.ModelDataProvider;
import org.ztouhou.mcmod.decoration.client.model.ModelCellingLight;
import org.ztouhou.mcmod.decoration.client.model.ModelExit;
import org.ztouhou.mcmod.decoration.client.model.ModelFenceLight;
import org.ztouhou.mcmod.decoration.client.model.ModelFenceLightSmall;
import org.ztouhou.mcmod.decoration.client.model.ModelFireExtinguisher;
import org.ztouhou.mcmod.decoration.client.model.ModelLCD;
import org.ztouhou.mcmod.decoration.client.model.ModelSignLarge;
import org.ztouhou.mcmod.decoration.client.model.ModelSignStandard;
import org.ztouhou.mcmod.decoration.client.model.ModelUrinals;
import org.ztouhou.mcmod.decoration.client.model.ModelWetFloor;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.data.DataGenerator;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import rikka.librikka.DirHorizontal8;
import rikka.librikka.block.ICustomBoundingBox;
import rikka.librikka.gui.AutoGuiHandler;
import rikka.librikka.model.CodeBasedModel;
import rikka.librikka.model.loader.TERHelper;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Decoration.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrationHandler {
	public  final static Map<BlockState, CodeBasedModel> dynamicModels = new HashMap<>();
	private final static String texture1 = Decoration.MODID + ":block/texture1";
	private final static String domain = Decoration.MODID;
	
	private static void modelSignStandard(BlockSimpleSign... blocks) {
		for(BlockSimpleSign block: blocks) {
			block.getStateContainer().getValidStates().forEach((blockState) -> {
				int type = block.getType() + block.getTextureOffset();
				int rotation = BlockSignBase.getRotationInt(blockState);
				String particle = block.getRegistryName().getPath();
				
				CodeBasedModel model = block.isLargeSign() ? 
						new ModelSignLarge(texture1, domain + ":item/" + particle, rotation, type) :
						new ModelSignStandard(texture1, domain + ":item/" + particle, rotation, 1, 0.4375F, 0.1F, 1024,
								544, 512 + 56 * type, 672, 568 + 56 * type);
				dynamicModels.put(blockState, model);
			});
		}
	}
	
	private static void modelSignStandard2(BlockSignBase... blocks) {
		for(BlockSignBase block: blocks) {
			block.getStateContainer().getValidStates().forEach((blockState) -> {
				int type = block.getType();
				int rotation = BlockSignBase.getRotationInt(blockState);
				String particle = block.getRegistryName().getPath();
				
				CodeBasedModel model = new ModelSignStandard(texture1, domain + ":item/" + particle, rotation,
	        			1 , 12F/16F, 1F/16F,
	    				1024, 672, type*96+512, 800, type*96+608);
				dynamicModels.put(blockState, model);
			});
		}
	}
	
	private static void assignInventoryModel(ModelBakeEvent event, Block... blocks) {
    	for (int i=0; i<blocks.length; i++) {
    		Block block = blocks[i];
			BlockState blockstate = block.getDefaultState();
    		ModelResourceLocation resLoc = new ModelResourceLocation(block.getRegistryName(), "inventory");
    		IBakedModel newItemModel = event.getModelRegistry().get(BlockModelShapes.getModelLocation(blockstate));
    		event.getModelRegistry().put(resLoc, newItemModel);
    	}
	}
	
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event){
		MinecraftForge.EVENT_BUS.register(new Object() {
			@SubscribeEvent
			public void onBlockHighLight(DrawHighlightEvent.HighlightBlock event) {
				ICustomBoundingBox.onBlockHighLight(event);
			}
		});
		
		for (Class<? extends Container> containerCls: BlockRegistry.registeredGuiContainers) {
			AutoGuiHandler.registerContainerGui(containerCls);
		}
		
		modelSignStandard(BlockRegistry.blockSign1);
		modelSignStandard(BlockRegistry.blockSign2);
		modelSignStandard(BlockRegistry.blockSign3);
		
		// BlockSign4
		BlockRegistry.blockSign4[0].getStateContainer().getValidStates().forEach((blockState) -> {
			BlockSignBase block = (BlockSignBase) blockState.getBlock();
			int rotation = BlockSignBase.getRotationInt(blockState);
			String particle = block.getRegistryName().getPath();
			
			dynamicModels.put(blockState, new ModelLCD(
    				texture1,
        			domain + ":item/" + particle,
        			rotation));
		});
		BlockRegistry.blockSign4[1].getStateContainer().getValidStates().forEach((blockState) -> {
			BlockSignBase block = (BlockSignBase) blockState.getBlock();
			int rotation = BlockSignBase.getRotationInt(blockState);
			String particle = block.getRegistryName().getPath();
			
			dynamicModels.put(blockState, new ModelUrinals(
    				texture1,
        			domain + ":item/" + particle,
        			rotation));
		});
		BlockRegistry.blockSign4[2].getStateContainer().getValidStates().forEach((blockState) -> {
			BlockSignBase block = (BlockSignBase) blockState.getBlock();
			int rotation = BlockSignBase.getRotationInt(blockState);
			String particle = block.getRegistryName().getPath();
			
			dynamicModels.put(blockState, new ModelFireExtinguisher(
    				texture1,
        			domain + ":item/" + particle,
        			rotation));
		});
		BlockRegistry.blockSign4[3].getStateContainer().getValidStates().forEach((blockState) -> {
			BlockSignBase block = (BlockSignBase) blockState.getBlock();
			int rotation = BlockSignBase.getRotationInt(blockState);
			String particle = block.getRegistryName().getPath();
			
			dynamicModels.put(blockState, new ModelExit(
    				texture1,
        			domain + ":item/" + particle,
        			rotation));
		});
		
		// BlockSign5
		BlockRegistry.blockSign5.getStateContainer().getValidStates().forEach((blockState) -> {
        	int rotation = (8-blockState.get(DirHorizontal8.prop).ordinal()) & 7;
        	
        	dynamicModels.put(blockState, new ModelWetFloor(texture1, domain + ":item/sign_wetfloor", rotation));
		});
		
		// BlockMisc
		BlockRegistry.blockMisc[0].getStateContainer().getValidStates().forEach((blockState) -> {			
			dynamicModels.put(blockState, new ModelFenceLight(texture1, domain + ":item/fencelight"));
		});
		BlockRegistry.blockMisc[1].getStateContainer().getValidStates().forEach((blockState) -> {
			dynamicModels.put(blockState, new ModelFenceLightSmall(texture1, domain + ":item/fencelight_small"));
		});
		BlockRegistry.blockMisc[2].getStateContainer().getValidStates().forEach((blockState) -> {
			dynamicModels.put(blockState, new ModelCellingLight(texture1, domain + ":item/cellinglight"));
		});
		
		// BlockSign6
		modelSignStandard2(BlockRegistry.blockSign6);
		
		// Doors
		RenderTypeLookup.setRenderLayer(BlockRegistry.blockDoor1, RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegistry.blockDoor3, RenderType.getTranslucent());

		registerTileEntityRenders();
	}
	
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {   	
    	for (CodeBasedModel dynamicModel: dynamicModels.values())
    		dynamicModel.onPreTextureStitchEvent(event);
    }
	
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
    	Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
    	
    	dynamicModels.forEach((blockstate, dynamicModel) -> {
    		dynamicModel.onModelBakeEvent();
    		registry.put(BlockModelShapes.getModelLocation(blockstate), dynamicModel);
    	});

    	assignInventoryModel(event, BlockRegistry.blockMisc);
    }
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper exfh = event.getExistingFileHelper();

		if (event.includeClient()) {
			generator.addProvider(new ModelDataProvider(generator, exfh));
		}
	}
	
	public static void registerTileEntityRenders() {
		TERHelper.bind(TileEntityLED12864.class, LED12864Render::new);
	}
}
