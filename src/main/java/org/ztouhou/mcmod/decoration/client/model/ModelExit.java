package org.ztouhou.mcmod.decoration.client.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityExitSignWithSensor;
import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.IModelData;
import rikka.librikka.model.quadbuilder.RawQuadCube2;

@OnlyIn(Dist.CLIENT)
public class ModelExit extends ModelComplexBase{
	private final List<BakedQuad> deteced = new LinkedList<>();
	private final List<BakedQuad> forced = new LinkedList<>();
	
	public ModelExit(String texture, String particle, int rotation) {
		super(texture, particle, rotation);
	}

	@Override
	protected void bake() {
		RawQuadCube2 cube = new RawQuadCube2(1, 0.4375F, 0.1F,
				272, 952, texture, 1024,
				128, 56, 8);
		
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 0, 0.5F);
		cube.bake(quads);
		
		deteced.clear();
		cube = new RawQuadCube2(1, 0.4375F, 0.1F,
				256, 448, texture, 1024,
				128, 56, 8);
		
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 0, 0.5F);
		cube.bake(deteced);
		
		forced.clear();
		cube = new RawQuadCube2(1, 0.4375F, 0.1F,
				272, 576, texture, 1024,
				128, 56, 8);
		
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 0, 0.5F);
		cube.bake(forced);
	}

	
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, 
			@Nonnull Random rand, @Nonnull IModelData extraData) {
		if (extraData.getData(TileEntityExitSignWithSensor.isForced))
			return forced;
		
		if (extraData.getData(TileEntityExitSignWithSensor.isDetected))
			return deteced;
        
		return quads;
	}
}
