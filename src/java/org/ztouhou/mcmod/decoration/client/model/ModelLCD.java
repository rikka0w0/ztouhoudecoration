package org.ztouhou.mcmod.decoration.client.model;

import java.util.List;

import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.RawQuadCube2;

@SideOnly(Side.CLIENT)
public class ModelLCD extends ModelComplexBase{
	public ModelLCD(String texture, String particle, int rotation) {
		super(texture, particle, rotation);
	}
	
	@Override
	protected void bake() {
		RawQuadCube2 cube = new RawQuadCube2(1, 0.6F, 0.1F, texture, 1024,
				140,	952,	263,	959,
				131,	959,	8, 		952,
				8,		960,	135,	1023,
				144,	960,	271,	1023,
				136,	960,	143,	1023,
				0,		960,	7,		1023
				);
		cube.translateCoord(0, 0.2F, 0.45F);
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 0, 0.5F);
		cube.bake(quads);
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return quads;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return particle;
	}
}
