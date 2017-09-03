package org.ztouhou.mcmod.decoration.blocks.tileentity;

import org.ztouhou.mcmod.decoration.item.ItemFireExtinguisher;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.container.ContainerInventory;
import rikka.librikka.container.IContainerWithGui;

public class ContainerFireExtinguisherBox extends ContainerInventory implements IContainerWithGui {
	public ContainerFireExtinguisherBox(InventoryPlayer invPlayer, TileEntityFireExtinguisherBox tileEntity) {
		super(invPlayer, tileEntity.inventory);
		
		addSlotToContainer(new FireExtinguisherSlot(inventoryTile, 0, 96, 22));
		addSlotToContainer(new FireExtinguisherSlot(inventoryTile, 1, 132, 22));
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public GuiScreen createGui() {
		return new GuiFireExtinguisherBox(this);
	}

}
