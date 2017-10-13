package org.ztouhou.mcmod.decoration.client;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Function;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.CodeBasedModel;
import rikka.librikka.model.quadbuilder.TechneModelPart;

@SideOnly(Side.CLIENT)
public abstract class ModelComplexBase extends CodeBasedModel{
	protected final int rotation;
	protected final ResourceLocation textureLocation, particleLocation;
	protected final List<BakedQuad> quads = new LinkedList();
	
	protected TextureAtlasSprite texture, particle;
	
	protected ModelComplexBase(String texture, String particle, int rotation) {
		this.rotation = rotation * 90;
		
		this.textureLocation = registerTexture(texture);
		this.particleLocation = registerTexture(particle);
	}
	
	@Override
	protected final void bake(Function<ResourceLocation, TextureAtlasSprite> registry) {
		texture = registry.apply(textureLocation);
		particle = registry.apply(particleLocation);
		
		quads.clear();
		bake();
	}
	
	protected abstract void bake();
	
	protected static void setRotation(TechneModelPart model, float x, float y, float z) {
		model.setRotation(x, y, z);
	}
	
	@Override
	public TextureAtlasSprite getParticleTexture() {
		return particle;
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		//List<BakedQuad> quads = new LinkedList();
		/*
		//addBox
		RawQuadCube cube = new RawQuadCube(10, 25, 1, texture);
		cube.translateCoord(10/2F, 0, 1/2F);
		cube.translateCoord(0F, 4F, 0F);
		
		
		//setRotationPoint(-5F, 17F, 0F);
		cube.rotateAroundX(14.474999471378870449959129358878F);
		cube.translateCoord(-5F, 17F, 0F);
		
		//cube.translateCoord(5F, -17F, 0F);
		
		
		//TESR
		cube.scale(0.5F*0.0625F);
		cube.rotateAroundZ(180);
		cube.translateCoord(0.5F, 1.5F, 0.5F);
		cube.bake(quads);*/
		
		//ModelRenderBaker BMain = new ModelRenderBaker(this, 0, 7);	//0,56
        //BMain.addBox(0F, 4F, 0F, 10, 25, 1);	
        //BMain.setRotationPoint(-5F, 17F, 0F);
        //BMain.setRotation(0.2526364F, 0F, 0F);
        //BMain.render(quads);
		
		return quads;
	}
}
