package org.ztouhou.mcmod.decoration.client.model;

import org.lwjgl.util.vector.Vector3f;
import org.ztouhou.mcmod.decoration.client.TechneModel;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.ModelPerspectives;
import rikka.librikka.model.quadbuilder.TechneModelPart;

@SideOnly(Side.CLIENT)
public class ModelFenceLight extends TechneModel {
	public ModelFenceLight(String texture, String particle) {
		super(texture, particle, 0);
	}

	@Override
	protected void bake() {
		TechneModelPart Pole;
	    TechneModelPart LightBox;
	    TechneModelPart Base;
	    TechneModelPart Cover;
	    TechneModelPart Cap;
		
        Pole = new TechneModelPart(this, 37, 22);
        Pole.addBox(0F, 0F, 0F, 4, 2, 4);
        Pole.setRotationPoint(-2F, 22F, -2F);
        Pole.setTextureSize(128, 64);
        Pole.mirror = true;
        setRotation(Pole, 0F, 0F, 0F);
        LightBox = new TechneModelPart(this, 0, 12);
        LightBox.addBox(0F, 0F, 0F, 8, 10, 8);
        LightBox.setRotationPoint(-4F, 11F, -4F);
        LightBox.setTextureSize(128, 64);
        LightBox.mirror = true;
        setRotation(LightBox, 0F, 0F, 0F);
        Base = new TechneModelPart(this, 0, 32);
        Base.addBox(0F, 0F, 0F, 10, 1, 10);
        Base.setRotationPoint(-5F, 21F, -5F);
        Base.setTextureSize(128, 64);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Cover = new TechneModelPart(this, 26, 0);
        Cover.addBox(0F, 0F, 0F, 10, 1, 10);
        Cover.setRotationPoint(-5F, 10F, -5F);
        Cover.setTextureSize(128, 64);
        Cover.mirror = true;
        setRotation(Cover, 0F, 0F, 0F);
        Cap = new TechneModelPart(this, 0, 0);
        Cap.addBox(0F, 0F, 0F, 6, 1, 6);
        Cap.setRotationPoint(-3F, 9F, -3F);
        Cap.setTextureSize(128, 64);
        Cap.mirror = true;
        setRotation(Cap, 0F, 0F, 0F);
        
        TechneModelPart.render(quads, rotation, 1, Pole, LightBox, Base, Cover, Cap);
	}
	
	@Override
	public int getTextureRelativeSize() {
		return 128;	// 1024 / 512 * 64
	}

	@Override
	public int getUOffset() {
		return 0;
	}

	@Override
	public int getVOffset() {
		return 42;
	}
	
    public final static ItemCameraTransforms itemCameraTransforms = ModelPerspectives.create(ModelPerspectives.ItemBlock,
    		new ItemTransformVec3f(new Vector3f(75, 45, 0), 	new Vector3f(0, 0.15625F, 0), 	new Vector3f(0.45F, 0.45F, 0.45F)),	//thirdperson_leftIn
    		new ItemTransformVec3f(new Vector3f(75, 45, 0), 	new Vector3f(0, 0.15625F, 0), 	new Vector3f(0.45F, 0.45F, 0.45F)),	//thirdperson_rightIn
    		new ItemTransformVec3f(new Vector3f(0, 225, 0), 	new Vector3f(0,0.15F,0), 		new Vector3f(0.5F, 0.5F, 0.5F)),		//firstperson_leftIn
    		new ItemTransformVec3f(new Vector3f(0, 45, 0), 		new Vector3f(0,0.15F,0), 		new Vector3f(0.5F, 0.5F, 0.5F)),		//firstperson_rightIn
    		null, 
    		new ItemTransformVec3f(new Vector3f(30, 225, 0), 	new Vector3f(), 		new Vector3f(0.75F, 0.75F, 0.75F))	//gui
    		, null, null);
    
    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
    	return itemCameraTransforms;
    }
}
