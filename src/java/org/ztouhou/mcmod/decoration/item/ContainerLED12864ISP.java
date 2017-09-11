package org.ztouhou.mcmod.decoration.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class ContainerLED12864ISP extends Container{
	public ContainerLED12864ISP(ItemStack stack) {
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}
