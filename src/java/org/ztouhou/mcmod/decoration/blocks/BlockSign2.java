package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.creativetab.CreativeTabs;

public class BlockSign2 extends BlockSimpleSign{
	public static String[] subNames = new String[] {"disabled", "transgender", "flush", "closer"};
	
	public BlockSign2() {
		super("sign2");
	}
	
	@Override
	public void beforeRegister() {
		setCreativeTab(CreativeTabs.REDSTONE);
	}

	@Override
	public String[] getSubBlockUnlocalizedNames() {
		return subNames;
	}

	@Override
	public int getTextureOffset() {
		return 4;
	}
}
