package org.ztouhou.mcmod.decoration.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import rikka.librikka.Utils;
import rikka.librikka.properties.Properties;

public class BlockSign3 extends BlockSimpleSign {
	public static String[] subNames = new String[] {"broadcast", "wirebox", "hydrant", "radiation"};
	
	public BlockSign3() {
		super("sign3");
	}
	
	@Override
	public String[] getSubBlockUnlocalizedNames() {
		return subNames;
	}
	
	@Override
	public int getTextureOffset() {
		return 0;
	}
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	int rotation = state.getValue(Properties.facing2bit);
        return boundingBoxesLarge[rotation];
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = player.getHeldItem(hand);
        
        if(held != null && !held.isEmpty() && held.isItemEqual(new ItemStack(Items.BUCKET))) {
        	player.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
        } else {
        	if (world.isRemote)
        		Utils.chatWithLocalization(player, "msg.ztouhoudecoration:sign3.hydrant.needbucket");
        }
        
        return true;
    }
}
