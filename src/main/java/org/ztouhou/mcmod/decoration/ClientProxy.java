package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.client.GuiLED12864ISP;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class ClientProxy extends CommonProxy {
	@Override
	public void openGuiLED12864ISP(ItemStack stack) {
		Minecraft.getInstance().displayGuiScreen(new GuiLED12864ISP(stack));
	}
}
