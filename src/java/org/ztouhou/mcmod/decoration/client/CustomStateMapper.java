package org.ztouhou.mcmod.decoration.client;

import org.ztouhou.mcmod.decoration.BlockRegistry;
import org.ztouhou.mcmod.decoration.blocks.BlockSimpleSign;
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
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.block.MetaBlock;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.model.loader.IModelLoader;
import rikka.librikka.properties.Properties;

@SideOnly(Side.CLIENT)
public class CustomStateMapper extends StateMapperBase implements IModelLoader {
    public static final String VPATH = "virtual/blockstates/sign";
    public final String domain;

    public CustomStateMapper(String domain) {
        this.domain = domain;
    }

    @Override
    public boolean accepts(String resPath) {
        return resPath.startsWith(VPATH);
    }
    
    @Override
    public ModelResourceLocation getModelResourceLocation(IBlockState state) {
        Block block = state.getBlock();
        String blockDomain = block.getRegistryName().getResourceDomain();
        String blockName = block.getRegistryName().getResourcePath();

        String varStr = "";
        
        if (block instanceof BlockSimpleSign) {
        	int rotation = state.getValue(Properties.facing2bit);
        	int type = state.getValue(Properties.type2bit);
        	String particle = ((BlockSimpleSign)block).getIconName(type);
        	
        	int textureIndex = type + ((BlockSimpleSign)block).getTextureOffset();
        	
        	varStr = rotation + "," + textureIndex  + "," + particle;
        } else if (block == BlockRegistry.blockSign4) {
        	int rotation = state.getValue(Properties.facing2bit);
        	int type = state.getValue(Properties.type2bit);
        	String inventoryIcon = ((ISimpleTexture)block).getIconName(type);
        	
        	varStr = rotation + "," + type + "," + inventoryIcon;
        } else if (block == BlockRegistry.blockSign5) {
        	int rotation = state.getValue(Properties.facing3bit);
        	
        	varStr = rotation + "";
        } else if (block == BlockRegistry.blockMisc) {
        	int type = BlockRegistry.blockMisc.getMetaFromState(state);
        	
        	varStr = type + "";
        } else if (block == BlockRegistry.blockSign6) {
        	int rotation = state.getValue(Properties.facing2bit);
        	int type = state.getValue(Properties.type2bit);
        	String inventoryIcon = ((ISimpleTexture)block).getIconName(type);
        	
        	varStr = rotation + "," + type + "," + inventoryIcon;
        }
        
        ModelResourceLocation res = new ModelResourceLocation(domain + ":" + VPATH,
                blockDomain + "," + blockName + "," + varStr);
        return res;
    }
    
    @Override
    public IModel loadModel(String domain, String resPath, String variantStr) throws Exception {
        String[] splited = variantStr.split(",");
        String blockDomain = splited[0];
        String blockName = splited[1];
        Block block = Block.getBlockFromName(blockDomain + ":" + blockName);
        
        String texture1 = domain + ":blocks/texture1";
        if (block instanceof BlockSimpleSign) {
        	int rotation = Integer.parseInt(splited[2]);
        	int type = Integer.parseInt(splited[3]);
        	String particle = splited[4];
        	
        	if (block == BlockRegistry.blockSign1 || block == BlockRegistry.blockSign2) {
            	return new ModelSignStandard(texture1, domain + ":items/" + particle, rotation, 
            			1, 0.4375F, 0.1F,
            			1024, 544, 512+56*type , 672, 568+56*type);
        	} else if (block == BlockRegistry.blockSign3) {
            	return new ModelSignLarge(
            			texture1,
            			domain + ":items/" + particle,
            			rotation, type);
        	}
        } else if (block == BlockRegistry.blockSign4) {
        	int rotation = Integer.parseInt(splited[2]);
        	int type = Integer.parseInt(splited[3]);
        	String particle = splited[4];
        	
        	switch (type) {
        	case 0:
        		return new ModelLCD(
        				texture1,
            			domain + ":items/" + particle,
            			rotation);
        	case 1:
        		return new ModelUrinals(
        				texture1,
            			domain + ":items/" + particle,
            			rotation);
        	case 2:
        		return new ModelFireExtinguisher(
        				texture1,
            			domain + ":items/" + particle,
            			rotation);
        	case 3:
        		return new ModelExit(
        				texture1,
            			domain + ":items/" + particle,
            			rotation);        		
        	}
        } else if (block == BlockRegistry.blockSign5) {
        	int rotation = Integer.parseInt(splited[2]);
        	
        	return new ModelWetFloor(texture1, domain + ":items/sign5_wetfloor", rotation);
        }  else if (block == BlockRegistry.blockMisc) {
        	int type = Integer.parseInt(splited[2]);
        	switch (type) {
        	case 0:
        		return new ModelFenceLight(texture1, domain + ":items/misc_fencelight");
        	case 1:
        		return new ModelFenceLightSmall(texture1, domain + ":items/misc_fencelight_small");
        	case 2:
        		return new ModelCellingLight(texture1, domain + ":items/misc_cellinglight");
        	}
        	
        } else if (block == BlockRegistry.blockSign6) {
        	int rotation = Integer.parseInt(splited[2]);
        	int type = Integer.parseInt(splited[3]);
        	String particle = splited[4];
        	
        	return new ModelSignStandard(texture1, domain + ":items/" + particle, rotation,
        			1 , 12F/16F, 1F/16F,
    				1024, 672, type*96+512, 800, type*96+608);
        }
        
        return null;
    }
    
    public void register(Block block) {
        ModelLoader.setCustomStateMapper(block, this);
    }
    
    public void register3D(MetaBlock block) {
    	ModelLoader.setCustomStateMapper(block, this);
    	
    	for (int i: block.propertyMeta.getAllowedValues()) {
    		ModelLoader.setCustomModelResourceLocation(block.itemBlock, i, 
    				this.getModelResourceLocation(block.getStateFromMeta(i)));
    	}
    }
}
