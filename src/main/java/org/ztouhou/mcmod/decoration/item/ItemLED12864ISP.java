package org.ztouhou.mcmod.decoration.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import org.ztouhou.mcmod.decoration.Decoration;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rikka.librikka.item.ItemBase;

import javax.annotation.Nullable;

public class ItemLED12864ISP extends ItemBase {
	public static final int numOfRows = 4;
	
	public ItemLED12864ISP() {
		super("led12864isp", (new Item.Properties()).group(Decoration.itemGroup).maxStackSize(1));
		
	}

	public static String[] getContent(ItemStack stack) {
		String[] ret;
				
        if(stack != null && stack.hasTag() && stack.getItem() instanceof ItemLED12864ISP) {
        	CompoundNBT nbt = stack.getTag();
        	ret = new String[numOfRows];
        	
        	for (int i=0; i<numOfRows; i++) 
        		ret[i] = nbt.getString("line"+i);
        } else {
        	ret = new String[] {"Zry", "LED12864", "", ""};
        }
        
        return ret;
	}
	
	public static void setContent(ItemStack stack, String[] content) {
		if (stack == null || content == null)
			return;

		CompoundNBT nbt = stack.getOrCreateTag();
		for (int i=0; i<content.length; i++)
			nbt.putString("line"+i, content[i]);
	}
	
	/**
	 * Called when the player is not looking at a block & sneaking
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getHeldItem(hand);
    	
		if (hand == Hand.OFF_HAND)
			return ActionResult.resultFail(itemStack);
		
		if (player.isCrouching()) {
			//Open the client-only ISP Gui
    		Decoration.proxy.openGuiLED12864ISP(itemStack);
    		
    		return ActionResult.resultSuccess(itemStack);
    	} else {
        	return ActionResult.resultPass(itemStack);
    	}    		
    }

	@Override
    public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		TileEntity tileEntity = world.getTileEntity(pos);
		
		if (tileEntity instanceof TileEntityLED12864) {
			ItemStack itemStack = player.getHeldItem(hand);
			
			if (!world.isRemote) {
				if (player.isCrouching()) {
					//Read
					setContent(itemStack, ((TileEntityLED12864) tileEntity).content);
				}else {
					//Set
					((TileEntityLED12864) tileEntity).setContent(getContent(itemStack));
				}
				
			}
			
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.FAIL;
		}
    }
 
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {	
		String[] content = getContent(stack);
		if (content.length == 0)
			return;
		
		tooltip.add((new TranslationTextComponent("gui.ztouhoudecoration.led12864isp.content")).appendText(":"));
		for (String s: content)
			tooltip.add(new StringTextComponent(s));
    }
}
