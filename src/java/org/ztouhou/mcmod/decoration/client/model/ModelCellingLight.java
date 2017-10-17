package org.ztouhou.mcmod.decoration.client.model;

import org.lwjgl.util.vector.Vector3f;
import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.ModelPerspectives;
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
	
	public final static ItemCameraTransforms itemCameraTransforms = ModelPerspectives.create(ModelPerspectives.ItemBlock,
        		new ItemTransformVec3f(new Vector3f(225, 190, 90), 	new Vector3f(-0.1F, 0.2F, 0), 	new Vector3f(0.45F, 0.45F, 0.45F)),	//thirdperson_leftIn
        		new ItemTransformVec3f(new Vector3f(225, 190, 90), 	new Vector3f(-0.1F, 0.2F, 0), 	new Vector3f(0.45F, 0.45F, 0.45F)),	//thirdperson_rightIn
        		new ItemTransformVec3f(new Vector3f(45, 0,275), 		new Vector3f(-0.1F, 0, 0), 		new Vector3f(0.5F, 0.5F, 0.5F)),		//firstperson_leftIn
        		new ItemTransformVec3f(new Vector3f(45, 0,275), 		new Vector3f(-0.1F, 0, 0), 		new Vector3f(0.5F, 0.5F, 0.5F)),		//firstperson_rightIn
        		null, 
        		new ItemTransformVec3f(new Vector3f(215, 35, 15), 	new Vector3f(0.085F, 0.2F,0), 		new Vector3f(0.65F, 0.65F, 0.65F))	//gui
        		, new ItemTransformVec3f(new Vector3f(180,0,45),		new Vector3f(0, 0.1875F, 0), 	new Vector3f(0.25F, 0.25F, 0.25F)),		//groundIn
        		null);
	
    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
    	return itemCameraTransforms;
    }
}
