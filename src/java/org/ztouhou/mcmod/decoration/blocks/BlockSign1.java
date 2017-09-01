package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.creativetab.CreativeTabs;

public class BlockSign1 extends BlockSimpleSign{
	public static String[] subNames = new String[] {"exit", "toilet", "male", "female"};
	
	public BlockSign1() {
		super("sign1");
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
		return 0;
	}
}
