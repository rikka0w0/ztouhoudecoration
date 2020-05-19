package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockSimpleSign extends BlockSignBase {
	public BlockSimpleSign(String unlocalizedName) {
		super(unlocalizedName, Block.Properties.create(Material.GLASS).hardnessAndResistance(1.0F, 5.0F));
	}
	
	public abstract int getTextureOffset();
	
	public boolean isLargeSign() {
		return false;
	}
}
