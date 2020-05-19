package org.ztouhou.mcmod.decoration.blocks;

public class BlockSign2 extends BlockSimpleSign{
	public static String[] subNames = new String[] {"disabled", "transgender", "flush", "closer"};
	
	public final int meta;
	private BlockSign2(int meta) {
		super("sign_" + subNames[meta]);
		this.meta = meta;
	}

	public static BlockSign2[] create() {
		BlockSign2[] ret = new BlockSign2[subNames.length];
		for (int i=0; i<subNames.length; i++) {
			ret[i] = new BlockSign2(i);
		}
		return ret;
	}

	@Override
	public int getTextureOffset() {
		return 4;
	}
	
	@Override
	public int getType() {
		return meta;
	}
}
