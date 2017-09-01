package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.RawQuadCube2;

@SideOnly(Side.CLIENT)
public class ModelExit extends ModelComplexBase{
	private final boolean hasSensor;
	private final int state;
	
	public ModelExit(String texture, String particle, int rotation, boolean hasSensor, int state) {
		super(texture, particle, rotation);
		
		this.hasSensor = hasSensor;
		this.state = state;
	}

	@Override
	protected void bake() {
		int u,v;
		if (hasSensor) {
			u = 272;
			v = 952;
		} else {
			u = 544;
			v = 512;
		}
		
		RawQuadCube2 cube = new RawQuadCube2(1, 0.4375F, 0.1F,
				u, v, texture, 1024,
				128, 56, 8);
		
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 0, 0.5F);
		cube.bake(quads);
	}

}
