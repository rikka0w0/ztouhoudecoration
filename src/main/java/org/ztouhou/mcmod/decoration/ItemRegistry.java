package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.item.ItemFireExtinguisher;
import org.ztouhou.mcmod.decoration.item.ItemLED12864ISP;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {
	public static ItemFireExtinguisher fireExtinguisher;
	public static ItemLED12864ISP led12864isp;
	
	public static void initItems() {
		fireExtinguisher = new ItemFireExtinguisher();
		led12864isp = new ItemLED12864ISP();
	}
	
	public static void registerItems(IForgeRegistry<Item> registry) {
		registry.registerAll(
				fireExtinguisher,
				led12864isp
				);
	}
}
