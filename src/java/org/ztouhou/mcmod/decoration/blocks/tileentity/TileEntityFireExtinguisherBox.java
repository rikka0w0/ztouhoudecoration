package org.ztouhou.mcmod.decoration.blocks.tileentity;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import rikka.librikka.tileentity.IGuiProviderTile;
import rikka.librikka.tileentity.InventoryHelper;
import rikka.librikka.tileentity.SETileEntity;

public class TileEntityFireExtinguisherBox extends SETileEntity implements IInventory, IGuiProviderTile {
	public TileEntityFireExtinguisherBox()	{
		itemStacks = new ItemStack[2];
		clear();
	}
	
	@Override
	public Container getContainer(EntityPlayer player, EnumFacing side) {
		return new ContainerFireExtinguisherBox(player.inventory, this);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound parentNBTTagCompound)	{
		super.readFromNBT(parentNBTTagCompound); // The super call is required to save and load the tiles location
		InventoryHelper.stacksFromNBT(parentNBTTagCompound, itemStacks);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound)	{
		InventoryHelper.stacksToNBT(parentNBTTagCompound, itemStacks);
		return super.writeToNBT(parentNBTTagCompound); // The super call is required to save and load the tileEntity's location
	}
	////////////////////////
	/// IInventory
	////////////////////////
	@Override
	public String getName() {
		return "container.mbe31_inventory_furnace.name";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	private ItemStack[] itemStacks;
	
	@Override
	public int getSizeInventory() {
		return itemStacks.length;
	}

	@Override
	public boolean isEmpty() {
		return InventoryHelper.isEmpty(itemStacks);
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		return itemStacks[slotIndex];
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
		InventoryHelper.setInventorySlotContents(this, itemStacks, slotIndex, itemstack);
	}
	
	@Override
	public ItemStack decrStackSize(int slotIndex, int count) {
		return InventoryHelper.decrStackSize(this, slotIndex, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int slotIndex) {
		return InventoryHelper.removeStackFromSlot(this, slotIndex);
	}

	@Override
	public void clear() {
		Arrays.fill(itemStacks, ItemStack.EMPTY);  //EMPTY_ITEM
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return InventoryHelper.withInRange(this, player);
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}
}
