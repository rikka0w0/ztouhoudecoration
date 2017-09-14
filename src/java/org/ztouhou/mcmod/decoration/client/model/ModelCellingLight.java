package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.RawQuadCube2;

@SideOnly(Side.CLIENT)
public class ModelCellingLight extends ModelComplexBase {
	public ModelCellingLight(String texture, String particle) {
		super(texture, particle, 0);
	}
	
	@Override
	protected void bake() {
		RawQuadCube2 cube = new RawQuadCube2(1, 1F/16F, 1, 
				288, 128, texture, 1024,
				32, 2, 32);
		cube.translateCoord(0, 1F/32F - 0.5F, 0);
		cube.rotateAroundX(180);
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 1, 0.5F);
		cube.bake(quads);

		cube = new RawQuadCube2(14F/16F, 1F/16F, 14F/16F, texture, 1024,
				 288, 128, 320, 160,
				 288, 128, 320, 160,
				 288, 128, 320, 160,
				 288, 128, 320, 160,
				 288, 128, 320, 160,
				 288, 128, 320, 160);
			
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 14F/16F, 0.5F);
		cube.bake(quads);
	}
}
