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
public class ModelSign extends ModelComplexBase{
	protected final int index;
	
	public ModelSign(String texture, String particle, int rotation, int index) {
		super(texture, particle, rotation);
		this.index = index;
	}
	
	@Override
	protected void bake() {
		RawQuadCube2 cube = new RawQuadCube2(1, 0.4375F, 0.1F,
				544, 512 + index * 64, texture, 1024,
				128, 56, 8);

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
