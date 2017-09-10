package org.ztouhou.mcmod.decoration.blocks;

public class BlockSign1 extends BlockSimpleSign{
	public static String[] subNames = new String[] {"exit", "toilet", "male", "female"};
	
	public BlockSign1() {
		super("sign1");
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
