package org.ztouhou.mcmod.decoration.blocks.tileentity;

import org.ztouhou.mcmod.decoration.Decoration;
import org.ztouhou.mcmod.decoration.client.GuiFireExtinguisherBox;
import org.ztouhou.mcmod.decoration.item.ItemFireExtinguisher;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import rikka.librikka.container.ContainerInventory;
import rikka.librikka.gui.AutoGuiHandler;

@AutoGuiHandler.Marker(GuiFireExtinguisherBox.class)
public class ContainerFireExtinguisherBox extends ContainerInventory<IInventory> {
    // Server side
    public ContainerFireExtinguisherBox(int windowId, PlayerInventory invPlayer, IInventory invBlock) {
    	super(Decoration.MODID, windowId, invPlayer, invBlock);

		addSlot(new FireExtinguisherSlot(inventoryTile, 0, 96, 22));
		addSlot(new FireExtinguisherSlot(inventoryTile, 1, 132, 22));

    }

    // Client side
    public ContainerFireExtinguisherBox(int windowId, PlayerInventory inv, PacketBuffer data) {
    	this(windowId, inv, new Inventory(2));
    }
    
	public static class FireExtinguisherSlot extends Slot {
		public FireExtinguisherSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
	    @Override
	    public boolean isItemValid(ItemStack itemStack) {
	        return itemStack.getItem() instanceof ItemFireExtinguisher;
	    }
	}
}
