package org.ztouhou.mcmod.decoration.blocks.tileentity;

import org.ztouhou.mcmod.decoration.Decoration;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import rikka.librikka.container.StandardInventory;
import rikka.librikka.tileentity.TileEntityBase;

public class TileEntityFireExtinguisherBox extends TileEntityBase implements INamedContainerProvider {
	public final StandardInventory inventory = new StandardInventory(this, 2);

	public TileEntityFireExtinguisherBox() {
		super(Decoration.MODID);
	}
	
	@Override
	public void read(CompoundNBT parentCompoundNBT)	{
		super.read(parentCompoundNBT); // The super call is required to save and load the tiles location
		inventory.readFromNBT(parentCompoundNBT);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT parentCompoundNBT)	{
		inventory.writeToNBT(parentCompoundNBT);
		return super.write(parentCompoundNBT); // The super call is required to save and load the tileEntity's location
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory invPlayer, PlayerEntity player) {
		return new ContainerFireExtinguisherBox(windowId, invPlayer, this.inventory);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent(this.getBlockState().getBlock().getTranslationKey());
	}
}
