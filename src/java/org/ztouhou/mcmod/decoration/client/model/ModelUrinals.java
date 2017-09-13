package org.ztouhou.mcmod.decoration.client.model;

import java.util.LinkedList;
import java.util.List;

import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEnityUrinals;
import org.ztouhou.mcmod.decoration.client.TechneModel;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.RawQuadCube2;
import rikka.librikka.model.quadbuilder.TechneModelPart;
import rikka.librikka.properties.UnlistedPropertyRef;

@SideOnly(Side.CLIENT)
public class ModelUrinals extends TechneModel {
	private final List<BakedQuad> idle = new LinkedList();
	private final List<BakedQuad> detected = new LinkedList();
	private final List<BakedQuad> flushing = new LinkedList();
	
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
        

        idle.clear();
		float size = 0.125F;
		RawQuadCube2 cube = new RawQuadCube2(size*2, size, 0.01F, texture, 1024,
				-1, 0, -1, 0,
				-1, 0, -1, 0,
				0, 272, 32, 288,
				-1, 0, -1, 0,
				-1, 0, -1, 0,
				-1, 0, -1, 0);
		cube.translateCoord(0.5F-size, -size, 0.505F);
		cube.translateCoord(-0.3828125F, -0.046875F + 0.15F/16F, -0.2505F);
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 1, 0.5F);
		cube.bake(idle);
		
		
		detected.clear();
		cube = new RawQuadCube2(size*2, size, 0.01F, texture, 1024,
				-1, 0, -1, 0,
				-1, 0, -1, 0,
				32, 272, 64, 288,
				-1, 0, -1, 0,
				-1, 0, -1, 0,
				-1, 0, -1, 0);
		cube.translateCoord(0.5F-size, -size, 0.505F);
		cube.translateCoord(-0.3828125F, -0.046875F + 0.15F/16F, -0.2505F);
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 1, 0.5F);
		cube.bake(detected);
		
		flushing.clear();
		cube = new RawQuadCube2(size*2, size, 0.01F, texture, 1024,
				-1, 0, -1, 0,
				-1, 0, -1, 0,
				64, 272, 96, 288,
				-1, 0, -1, 0,
				-1, 0, -1, 0,
				-1, 0, -1, 0);
		cube.translateCoord(0.5F-size, -size, 0.505F);
		cube.translateCoord(-0.3828125F, -0.046875F + 0.15F/16F, -0.2505F);
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 1, 0.5F);
		cube.bake(flushing);
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
	
	@Override
	public List<BakedQuad> getQuads(IBlockState blockState, EnumFacing side, long rand) {
		TileEnityUrinals te = UnlistedPropertyRef.get(blockState, TileEnityUrinals.class);
		if (te != null) {
			TileEnityUrinals.State state= te.getStateForRendering();
			LinkedList<BakedQuad> list = new LinkedList();
			list.addAll(quads);
			
			switch (state) {
			case Idle:
			case AfterUse:
				list.addAll(idle);
				break;
			case Flushing:
				list.addAll(flushing);
				break;
			case Using:
				list.addAll(detected);
				break;
			default:
				break;
			
			}
			
			return list;
		}
		return quads;
	}
}
