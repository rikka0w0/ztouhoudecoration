package org.ztouhou.mcmod.decoration.blocks;

public class BlockSign1 extends BlockSimpleSign {
	public static String[] subNames = new String[] {"exit", "toilet", "male", "female"};
	
	public final int meta;
	private BlockSign1(int meta) {
		super("sign_" + subNames[meta]);
		this.meta = meta;
	}

	public static BlockSign1[] create() {
		BlockSign1[] ret = new BlockSign1[subNames.length];
		for (int i=0; i<subNames.length; i++) {
			ret[i] = new BlockSign1(i);
		}
		return ret;
	}
	
	@Override
	public int getTextureOffset() {
		return 0;
	}

	@Override
	public int getType() {
		return meta;
	}
}
