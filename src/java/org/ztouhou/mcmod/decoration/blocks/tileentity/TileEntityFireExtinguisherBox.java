package org.ztouhou.mcmod.decoration.blocks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import rikka.librikka.container.StandardInventory;
import rikka.librikka.tileentity.IGuiProviderTile;
import rikka.librikka.tileentity.TileEntityBase;

public class TileEntityFireExtinguisherBox extends TileEntityBase implements IGuiProviderTile {
	public final StandardInventory inventory = new StandardInventory(this, 2, "container.mbe31_inventory_furnace.name");
	
	@Override
	public Container getContainer(EntityPlayer player, EnumFacing side) {
		return new ContainerFireExtinguisherBox(player.inventory, this);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound parentNBTTagCompound)	{
		super.readFromNBT(parentNBTTagCompound); // The super call is required to save and load the tiles location
		inventory.readFromNBT(parentNBTTagCompound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound)	{
		inventory.writeToNBT(parentNBTTagCompound);
		return super.writeToNBT(parentNBTTagCompound); // The super call is required to save and load the tileEntity's location
	}
}
