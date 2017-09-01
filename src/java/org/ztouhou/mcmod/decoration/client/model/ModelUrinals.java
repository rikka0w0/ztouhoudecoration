package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.TechneModel;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.TechneModelPart;

@SideOnly(Side.CLIENT)
public class ModelUrinals extends TechneModel {
	public ModelUrinals(String texture, String particle, int rotation) {
		super(texture, particle, rotation);
	}

	@Override
	protected void bake() {
		TechneModelPart Shape1;
		TechneModelPart Shape2;
		TechneModelPart Shape3;
		TechneModelPart Shape4;
	    TechneModelPart Shape5;
	    TechneModelPart Shape6;
	    TechneModelPart Shape7;
	    TechneModelPart Shape8;
		
		Shape1 = new TechneModelPart(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 14, 16, 1);
        Shape1.setRotationPoint(-7F, 8F, 7F);
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new TechneModelPart(this, 0, 18);
        Shape2.addBox(0F, 0F, 0F, 14, 1, 8);
        Shape2.setRotationPoint(-7F, 23F, -1F);
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new TechneModelPart(this, 31, 0);
        Shape3.addBox(0F, 0F, 0F, 1, 4, 8);
        Shape3.setRotationPoint(-7F, 19F, -1F);
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new TechneModelPart(this, 45, 15);
        Shape4.addBox(0F, 0F, 0F, 1, 4, 8);
        Shape4.setRotationPoint(6F, 19F, -1F);
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new TechneModelPart(this, 0, 28);
        Shape5.addBox(0F, 0F, 0F, 14, 5, 1);
        Shape5.setRotationPoint(-7F, 19F, -2F);
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new TechneModelPart(this, 50, 0);
        Shape6.addBox(0F, 0F, 0F, 1, 11, 3);
        Shape6.setRotationPoint(-7F, 8F, 4F);
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new TechneModelPart(this, 59, 0);
        Shape7.addBox(2F, 0F, 0F, 1, 11, 3);
        Shape7.setRotationPoint(4F, 8F, 4F);
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new TechneModelPart(this, 0, 36);
        Shape8.addBox(0F, 0F, 0F, 12, 3, 3);
        Shape8.setRotationPoint(-6F, 8F, 4F);
        setRotation(Shape8, 0F, 0F, 0F);
        
        TechneModelPart.render(quads, rotation, 1,  Shape1, Shape2, Shape3, Shape4, Shape5, Shape6, Shape7, Shape8);
	}

	@Override
	public int getTextureRelativeSize() {
		return 128;
	}

	@Override
	public int getUOffset() {
		return 0;
	}

	@Override
	public int getVOffset() {
		return 0;
	}
}
