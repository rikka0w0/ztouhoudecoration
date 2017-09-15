package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.RawQuadCube2;

@SideOnly(Side.CLIENT)
public class ModelSignStandard extends ModelComplexBase {
	private final float width, height, depth;
	private final int textureSize, u1, v1, u2, v2;
	
	public ModelSignStandard(String texture, String particle, int rotation,
			float width, float height, float depth,
			int textureSize, int u1, int v1, int u2, int v2) {
		super(texture, particle, rotation);
		
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.textureSize = textureSize;
		this.u1 = u1;
		this.v1 = v1;
		this.u2 = u2;
		this.v2 = v2;
	}

	@Override
	protected void bake() {
		RawQuadCube2 cube = new RawQuadCube2(width, height, depth, texture, 1024,
			408, 952, 536, 960,
			280, 952, 408, 960,
			u1, v1, u2, v2,
			416, 960, 544, 1016,
			408, 960, 416, 1016,
			272, 960, 280, 1016);
		
		cube.translateCoord(0, 0.5F - height/2F, 0.5F - depth/2F);
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 0, 0.5F);
		cube.bake(quads);
	}
}
