package org.ztouhou.mcmod.decoration.client;

import org.ztouhou.mcmod.decoration.BlockRegistry;
import org.ztouhou.mcmod.decoration.blocks.BlockSign4;
import org.ztouhou.mcmod.decoration.blocks.BlockSimpleSign;
import org.ztouhou.mcmod.decoration.client.model.ModelExit;
import org.ztouhou.mcmod.decoration.client.model.ModelFireExtinguisher;
import org.ztouhou.mcmod.decoration.client.model.ModelLCD;
import org.ztouhou.mcmod.decoration.client.model.ModelSign;
import org.ztouhou.mcmod.decoration.client.model.ModelSignLarge;
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
import rikka.librikka.Properties;
import rikka.librikka.model.loader.IModelLoader;

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
    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
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
        	String textureSheet = ((BlockSign4)block).getIconName(type);
        	
        	varStr = rotation + "," + type + "," + textureSheet;
        } else if (block == BlockRegistry.blockSign5) {
        	int rotation = state.getValue(Properties.facing3bit);
        	
        	varStr = rotation + "";
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
        	int textureIndex = Integer.parseInt(splited[3]);
        	String particle = splited[4];
        	
        	if (block == BlockRegistry.blockSign1 || block == BlockRegistry.blockSign2) {
            	return new ModelSign(
            			texture1,
            			domain + ":items/" + particle,
            			rotation, textureIndex);
        	} else if (block == BlockRegistry.blockSign3) {
            	return new ModelSignLarge(
            			texture1,
            			domain + ":items/" + particle,
            			rotation, textureIndex);
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
            			rotation, true, 1);        		
        	}
        } else if (block == BlockRegistry.blockSign5) {
        	int rotation = Integer.parseInt(splited[2]);
        	
        	return new ModelWetFloor(texture1, domain + ":items/sign5_wetfloor", rotation);
        }
        
        return null;
    }
    
    public void register(Block block) {
        ModelLoader.setCustomStateMapper(block, this);
    }
}
