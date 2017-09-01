package org.ztouhou.mcmod.decoration.client;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.TechneModelPart;

@SideOnly(Side.CLIENT)
public abstract class TechneModel extends ModelComplexBase implements TechneModelPart.TextureProvider {
	protected TechneModel(String texture, String particle, int rotation) {
		super(texture, particle, rotation);
	}

	////////////////////////////////////////////////
	/// ModelRenderBaker.TextureProvider
	////////////////////////////////////////////////
	@Override
	public TextureAtlasSprite getTexture() {
		return texture;
	}
}
