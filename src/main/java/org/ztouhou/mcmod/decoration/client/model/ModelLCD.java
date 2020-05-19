package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rikka.librikka.model.quadbuilder.RawQuadCube2;

@OnlyIn(Dist.CLIENT)
public class ModelLCD extends ModelComplexBase{
	public ModelLCD(String texture, String particle, int rotation) {
		super(texture, particle, rotation);
	}
	
	@Override
	protected void bake() {
		RawQuadCube2 cube = new RawQuadCube2(1, 0.6F, 0.1F, texture, 1024,
				140,	952,	264,	960,
				132,	960,	8, 		952,
				8,		960,	136,	1024,
				144,	960,	272,	1024,
				136,	960,	144,	1024,
				0,		960,	8,		1024
				);
		cube.translateCoord(0, 0.2F, 0.45F);
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 0, 0.5F);
		cube.bake(quads);
	}
}
