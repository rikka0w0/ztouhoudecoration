package org.ztouhou.mcmod.decoration.client.model;

import java.util.LinkedList;
import java.util.List;

import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityExitSignWithSensor;
import org.ztouhou.mcmod.decoration.client.ModelComplexBase;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.RawQuadCube2;
import rikka.librikka.properties.UnlistedPropertyRef;

@SideOnly(Side.CLIENT)
public class ModelExit extends ModelComplexBase{
	private final boolean hasSensor;
	private final int state;
	
	private final List<BakedQuad> deteced = new LinkedList();
	private final List<BakedQuad> forced = new LinkedList();
	
	public ModelExit(String texture, String particle, int rotation, boolean hasSensor, int state) {
		super(texture, particle, rotation);
		
		this.hasSensor = hasSensor;
		this.state = state;
	}

	@Override
	protected void bake() {
		if (hasSensor) {
			RawQuadCube2 cube = new RawQuadCube2(1, 0.4375F, 0.1F,
					272, 952, texture, 1024,
					128, 56, 8);
			
			cube.rotateAroundY(rotation);
			cube.translateCoord(0.5F, 0, 0.5F);
			cube.bake(quads);
			
			deteced.clear();
			cube = new RawQuadCube2(1, 0.4375F, 0.1F,
					272, 448, texture, 1024,
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
		} else {
			RawQuadCube2 cube = new RawQuadCube2(1, 0.4375F, 0.1F,
					544, 512, texture, 1024,
					128, 56, 8);
			
			cube.rotateAroundY(rotation);
			cube.translateCoord(0.5F, 0, 0.5F);
			cube.bake(quads);
		}
	}

	
	@Override
	public List<BakedQuad> getQuads(IBlockState blockState, EnumFacing side, long rand) {
        TileEntityExitSignWithSensor te = UnlistedPropertyRef.get(blockState, TileEntityExitSignWithSensor.class);
		
        if (te != null) {
    		if (((TileEntityExitSignWithSensor) te).isForced())
    			return forced;
    		
    		if (((TileEntityExitSignWithSensor) te).isDetected())
    			return deteced;
        }
        
		return quads;
	}
}
