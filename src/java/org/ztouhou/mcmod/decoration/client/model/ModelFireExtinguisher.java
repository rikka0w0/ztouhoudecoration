package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.TechneModel;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.TechneModelPart;

@SideOnly(Side.CLIENT)
public class ModelFireExtinguisher extends TechneModel {
	public ModelFireExtinguisher(String texture, String particle, int rotation) {
		super(texture, particle, rotation);
	}	

	@Override
	protected void bake() {
		TechneModelPart Shape1;
		TechneModelPart Shape2;
		
        Shape1 = new TechneModelPart(this, 0, 0);
        Shape1.addBox(0F, 12F, 1F, 12, 12, 7);
        Shape1.setRotationPoint(-6F, 0F, 0F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new TechneModelPart(this, 0, 19);
        Shape2.addBox(0F, 0F, 0F, 14, 1, 9);
        Shape2.setRotationPoint(-7F, 11F, -1F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        
        TechneModelPart.render(quads, rotation, 1, Shape1, Shape2);
	}
	
	@Override
	public int getTextureRelativeSize() {
		return 128;	// 1024 / 512 * 64
	}

	@Override
	public int getUOffset() {
		return 67;
	}

	@Override
	public int getVOffset() {
		return 35;
	}
}
