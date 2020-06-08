package org.ztouhou.mcmod.decoration.client.model;

import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemTransformVec3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rikka.librikka.model.ModelPerspectives;
import rikka.librikka.model.quadbuilder.RawQuadCube2;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
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

	public final static ItemCameraTransforms itemCameraTransforms = ModelPerspectives.create(ModelPerspectives.ItemBlock,
    		new ItemTransformVec3f(new Vector3f(75, 45, 0), 	new Vector3f(0, 0.2F, 0), 	new Vector3f(0.45F, 0.45F, 0.45F)),	//thirdperson_leftIn
    		new ItemTransformVec3f(new Vector3f(75, 45, 0), 	new Vector3f(0, 0.2F, 0), 	new Vector3f(0.45F, 0.45F, 0.45F)),	//thirdperson_rightIn
    		new ItemTransformVec3f(new Vector3f(0, 225, 0), 	new Vector3f(0, 0.2F, 0), 		new Vector3f(0.5F, 0.5F, 0.5F)),		//firstperson_leftIn
    		new ItemTransformVec3f(new Vector3f(0, 45, 0), 		new Vector3f(0, 0.2F, 0), 		new Vector3f(0.5F, 0.5F, 0.5F)),		//firstperson_rightIn
    		null, 
    		new ItemTransformVec3f(new Vector3f(30, 225, 0), 	new Vector3f(0, 0.1F, 0), 		new Vector3f(0.75F, 0.75F, 0.75F))	//gui
    		, null, null);
	
    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
    	return itemCameraTransforms;
    }
}
