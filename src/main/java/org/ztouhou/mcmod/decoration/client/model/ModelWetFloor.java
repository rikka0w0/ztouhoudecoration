package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.TechneModel;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rikka.librikka.model.quadbuilder.TechneModelPart;

@OnlyIn(Dist.CLIENT)
public class ModelWetFloor extends TechneModel {	
	public ModelWetFloor(String texture, String particle, int rotation) {
		super(texture, particle, rotation);
	}

	@Override
	protected void bake() {
		//Techne model start
		TechneModelPart BMain;
		TechneModelPart FMain;
		TechneModelPart BRFoot;
		TechneModelPart BLFoot;
		TechneModelPart FLFoot;
		TechneModelPart FRFoot;
		TechneModelPart FTop;
		TechneModelPart BTop;
	    TechneModelPart BRHandle;
	    TechneModelPart BLHandle;
	    TechneModelPart FLHandle;
	    TechneModelPart FRHandle;
		BMain = new TechneModelPart(this, 0, 7);	//0,56
        BMain.addBox(0F, 4F, 0F, 10, 25, 1);	
        BMain.setRotationPoint(-5F, 17F, 0F);
        setRotation(BMain, 0.2526364F, 0F, 0F);
        
        FMain = new TechneModelPart(this, 0, 7);
        FMain.addBox(0F, 4F, 0F, 10, 26, 1);
        FMain.setRotationPoint(-5F, 17F, 0F);
        setRotation(FMain, -0.2526364F, 0F, 0F);
        
        BRFoot = new TechneModelPart(this, 0, 0);
        BRFoot.addBox(0F, 30F, 0F, 3, 3, 1);
        BRFoot.setRotationPoint(-5F, 16F, -0.25F);
        setRotation(BRFoot, 0.2526364F, 0F, 0F);
        BLFoot = new TechneModelPart(this, 0, 0);
        BLFoot.addBox(0F, 30F, 0F, 3, 3, 1);
        BLFoot.setRotationPoint(2F, 16F, -0.25F);
        setRotation(BLFoot, 0.2526364F, 0F, 0F);
        FLFoot = new TechneModelPart(this, 0, 0);
        FLFoot.addBox(0F, 30F, 0F, 3, 3, 1);
        FLFoot.setRotationPoint(-5F, 16F, 0.25F);
        setRotation(FLFoot, -0.2526364F, 0F, 0F);
        FRFoot = new TechneModelPart(this, 0, 0);
        FRFoot.addBox(0F, 30F, 0F, 3, 3, 1);
        FRFoot.setRotationPoint(2F, 16F, 0.25F);
        setRotation(FRFoot, -0.2526364F, 0F, 0F);
        FTop = new TechneModelPart(this, 0, 0);
        FTop.addBox(0F, 0F, 0F, 10, 1, 1);
        FTop.setRotationPoint(-5F, 17F, 0F);
        setRotation(FTop, 0.2526364F, 0F, 0F);
        BTop = new TechneModelPart(this, 0, 0);
        BTop.addBox(0F, 0F, 0F, 10, 1, 1);
        BTop.setRotationPoint(-5F, 17F, 0F);
        setRotation(BTop, -0.2526364F, 0F, 0F);
        BRHandle = new TechneModelPart(this, 14, 0);
        BRHandle.addBox(0F, 1F, 0F, 2, 3, 1);
        BRHandle.setRotationPoint(-5F, 17F, 0F);
        setRotation(BRHandle, 0.2526364F, 0F, 0F);
        BLHandle = new TechneModelPart(this, 14, 0);
        BLHandle.addBox(0F, 1F, 0F, 2, 3, 1);
        BLHandle.setRotationPoint(3F, 17F, 0F);
        setRotation(BLHandle, 0.2526364F, 0F, 0F);
        FLHandle = new TechneModelPart(this, 14, 0);
        FLHandle.addBox(0F, 1F, 0F, 2, 3, 1);
        FLHandle.setRotationPoint(-5F, 17F, 0F);
        setRotation(FLHandle, -0.2526364F, 0F, 0F);
        FRHandle = new TechneModelPart(this, 14, 0);
        FRHandle.addBox(0F, 1F, 0F, 2, 3, 1);
        FRHandle.setRotationPoint(3F, 17F, 0F);
        setRotation(FRHandle, -0.2526364F, 0F, 0F);
		
        //Techne model end
        
        TechneModelPart.render(quads, rotation/2, 0.5F, BMain, FMain, BRFoot, BLFoot, FLFoot, FRFoot, FTop, BTop, BRHandle, BLHandle, FLHandle, FRHandle);
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
		return 0;
	}
}
