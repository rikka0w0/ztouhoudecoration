package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.item.ItemFireExtinguisher;

public class ItemRegistry {
	public static ItemFireExtinguisher fireExtinguisher;
	
	public static void registerItems() {
		fireExtinguisher = new ItemFireExtinguisher();
	}
}
