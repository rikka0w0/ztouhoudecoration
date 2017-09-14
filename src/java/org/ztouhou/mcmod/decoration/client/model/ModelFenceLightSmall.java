package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.RawQuadCube2;

@SideOnly(Side.CLIENT)
public class ModelFenceLightSmall extends ModelComplexBase {
	public ModelFenceLightSmall(String texture, String particle) {
		super(texture, particle, 0);
	}
	
	@Override
	protected void bake() {
		RawQuadCube2 cube = new RawQuadCube2(0.25F,0.25F,0.25F, texture, 1024,
			0, 144, 32, 172,
			0, 144, 32, 172,
			0, 144, 32, 172,
			0, 144, 32, 172,
			0, 144, 32, 172,
			0, 144, 32, 172);
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 0, 0.5F);
		cube.bake(quads);
	}
}
