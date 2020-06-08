package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.TechneModel;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemTransformVec3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rikka.librikka.model.quadbuilder.TechneModelPart;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
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
	
	private final static ItemCameraTransforms itemCameraTransforms = new ItemCameraTransforms(
    		//						Rotation					Translation						Scale
    		new ItemTransformVec3f(new Vector3f(75, 45, 0), 	new Vector3f(0, 0.15625F, 0), 	new Vector3f(0.375F, 0.375F, 0.375F)),	//thirdperson_leftIn
    		new ItemTransformVec3f(new Vector3f(75, 45, 0), 	new Vector3f(0, 0.15625F, 0), 	new Vector3f(0.375F, 0.375F, 0.375F)),	//thirdperson_rightIn
    		new ItemTransformVec3f(new Vector3f(0, 90, 0), 		new Vector3f(0,0.1F,0), 		new Vector3f(0.4F, 0.4F, 0.4F)),		//firstperson_leftIn
    		new ItemTransformVec3f(new Vector3f(0, 90, 0), 		new Vector3f(0,0.1F,0), 		new Vector3f(0.4F, 0.4F, 0.4F)),		//firstperson_rightIn
    		new ItemTransformVec3f(new Vector3f(), 				new Vector3f(), 				new Vector3f()), 						//headIn
            new ItemTransformVec3f(new Vector3f(30, 135, 0), 	new Vector3f(-0.1F,-0.05F,0),	new Vector3f(0.75F, 0.75F, 0.75F)), 	//guiIn
            new ItemTransformVec3f(new Vector3f(), 				new Vector3f(0, 0.1875F, 0), 	new Vector3f(0.35F, 0.35F, 0.35F)),		//groundIn
            new ItemTransformVec3f(new Vector3f(), 				new Vector3f(), 				new Vector3f(0.5F, 0.5F, 0.5F)));		//fixedIn
	
    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
    	return itemCameraTransforms;
    }
}
