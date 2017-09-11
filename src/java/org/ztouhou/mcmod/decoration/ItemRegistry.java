package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.item.ItemFireExtinguisher;
import org.ztouhou.mcmod.decoration.item.ItemLED12864ISP;

public class ItemRegistry {
	public static ItemFireExtinguisher fireExtinguisher;
	public static ItemLED12864ISP led12864isp;
	
	public static void registerItems() {
		fireExtinguisher = new ItemFireExtinguisher();
		led12864isp = new ItemLED12864ISP();
	}
}
