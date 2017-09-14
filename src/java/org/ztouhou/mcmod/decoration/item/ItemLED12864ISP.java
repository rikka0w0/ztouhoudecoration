package org.ztouhou.mcmod.decoration.item;

import java.util.List;

import org.ztouhou.mcmod.decoration.CreativeTab;
import org.ztouhou.mcmod.decoration.GuiHandler;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBase;

public class ItemLED12864ISP extends ItemBase implements ISimpleTexture {
	public static final int numOfRows = 4;
	
	public ItemLED12864ISP() {
		super("led12864isp", false);
		setCreativeTab(CreativeTab.instance);
        setMaxStackSize(1);
	}

	public static String[] getContent(ItemStack stack) {
		String[] ret;
				
        if(stack != null && stack.hasTagCompound() && stack.getItem() instanceof ItemLED12864ISP) {
        	NBTTagCompound nbt = stack.getTagCompound();
        	ret = new String[numOfRows];
        	
        	for (int i=0; i<numOfRows; i++) 
        		ret[i] = nbt.getString("line"+i);
        	
        	return ret;
        }
        
        return new String[] {"Zry", "LED12864"};
	}
	
	public static void setContent(ItemStack stack, String[] content) {
		if (stack == null)
			return;
		
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		
		NBTTagCompound nbt = stack.getTagCompound();
		for (int i=0; i<content.length; i++)
			nbt.setString("line"+i, content[i]);
	}
	
	/**
	 * Called when the player is not looking at a block & sneaking
	 */
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemStack = player.getHeldItem(hand);
    	
		if (hand == EnumHand.OFF_HAND)
			return ActionResult.newResult(EnumActionResult.FAIL, itemStack);
		
		if (player.isSneaking()) {
			
			//Open the client-only ISP Gui
    		if (world.isRemote) 
    			GuiHandler.openGui(player, world, player.getPosition(), GuiHandler.GuiType.LED12864ISP);
    		
    		return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
    	} else {
        	return ActionResult.newResult(EnumActionResult.PASS, itemStack);
    	}    		
    }

	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tileEntity = world.getTileEntity(pos);
		
		if (tileEntity instanceof TileEntityLED12864) {
			ItemStack itemStack = player.getHeldItem(hand);
			
			if (!world.isRemote) {
				if (player.isSneaking()) {
					//Read
					setContent(itemStack, ((TileEntityLED12864) tileEntity).content);
				}else {
					//Set
					((TileEntityLED12864) tileEntity).setContent(getContent(itemStack));
				}
				
			}
			
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
    }
 
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (!stack.hasTagCompound())
			return;
		
		String[] content = getContent(stack);
		if (content.length == 0)
			return;
		
		tooltip.add(I18n.translateToLocal("gui.ztouhoudecoration:led12864isp.content") + ":");
		for (String s: content)
			tooltip.add(s);
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public String getIconName(int damage) {
		return "item_led12864isp";
	}
}
