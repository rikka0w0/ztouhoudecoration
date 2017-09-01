package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.block.material.Material;

public abstract class BlockSimpleSign extends BlockSignBase{
	public BlockSimpleSign(String unlocalizedName) {
		super(unlocalizedName, Material.GLASS);
		
        setHardness(1.0F);
        setResistance(5.0F);
	}
	
	public abstract int getTextureOffset();
}
