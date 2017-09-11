package org.ztouhou.mcmod.decoration.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.properties.Properties;
import rikka.librikka.tileentity.TileEntityBase;

public class TileEntityLED12864 extends TileEntityBase{
	public static final int numOfRows = 4;
	public String[] content;
	
	private void contentToNBT(NBTTagCompound nbt)	{
		if (this.content != null) {
			for (int i=0; i<this.content.length; i++) 
				nbt.setString("line"+i, this.content[i]);
		}
	}
	
	private void contentFromNBT(NBTTagCompound nbt)	{
		this.content = new String[numOfRows];
		for (int i=0; i<numOfRows; i++) {
			this.content[i] = nbt.getString("line"+i);
		}
	}
	
	public void setContent(String[] content) {
		this.content = content;
		
		this.markTileEntityForS2CSync();
	}
    ///////////////////////////////////
    /// TileEntity
    ///////////////////////////////////
	@Override
	public void readFromNBT(NBTTagCompound nbt)	{
		super.readFromNBT(nbt); // The super call is required to save and load the tiles location
		
		contentFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)	{
		contentToNBT(nbt);
		
		return super.writeToNBT(nbt); // The super call is required to save and load the tileEntity's location
	}
	
    /////////////////////////////////////////////////////////
    ///Sync
    /////////////////////////////////////////////////////////
	@Override
    public void prepareS2CPacketData(NBTTagCompound nbt) {
		contentToNBT(nbt);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void onSyncDataFromServerArrived(NBTTagCompound nbt) {
		contentFromNBT(nbt);
    }
	
    /////////////////////////////////////////////////////////
    ///Utils
    /////////////////////////////////////////////////////////
	private int rotation = -1;
	
	@SideOnly(Side.CLIENT)
	public int getRotation() {
		if (rotation == -1) {
			int facing = this.getBlockType().getStateFromMeta(this.getBlockMetadata()).getValue(Properties.facing2bit);
			rotation = -facing*90;
		}
		
		return rotation;
	}
}
