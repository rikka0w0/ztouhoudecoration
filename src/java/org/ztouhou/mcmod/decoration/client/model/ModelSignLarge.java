package org.ztouhou.mcmod.decoration.client.model;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.model.quadbuilder.RawQuadCube2;

@SideOnly(Side.CLIENT)
public class ModelSignLarge extends ModelSign{
	public ModelSignLarge(String texture, String particle, int rotation, int index) {
		super(texture, particle, rotation, index);
	}

	@Override
	protected void bake() {
		int u = (index==0||index==1) ? 0 : 272;
		int v = (index==1||index==3) ? 136+680: 680;
		RawQuadCube2 cube = new RawQuadCube2(1, 1, 0.1F, texture, 1024,
				u+144,0+v,u+263,7+v,
				u+8,0+v,u+135,7+v,
				u+8,8+v,u+135,135+v,
				u+144,8+v,u+263,135+v,
				u+0,8+v,u+7,135+v,
				u+136,8+v,u+143,135+v
				);
		cube.translateCoord(0, 0, 0.45F);
		cube.rotateAroundY(rotation);
		cube.translateCoord(0.5F, 0, 0.5F);
		cube.bake(quads);
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) { 
		return quads;
	}
}
