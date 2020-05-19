package org.ztouhou.mcmod.decoration.blocks.tileentity;

import org.ztouhou.mcmod.decoration.Decoration;
import org.ztouhou.mcmod.decoration.blocks.BlockSignBase;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rikka.librikka.tileentity.TileEntityBase;

public class TileEntityLED12864 extends TileEntityBase {
	public static final int numOfRows = 4;
	public String[] content;
	

	public TileEntityLED12864() {
		super(Decoration.MODID);
	}
	
	private void contentToNBT(CompoundNBT nbt)	{
		if (this.content != null) {
			for (int i=0; i<this.content.length; i++) 
				nbt.putString("line"+i, this.content[i]);
		}
	}
	
	private void contentFromNBT(CompoundNBT nbt)	{
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
	public void read(CompoundNBT nbt)	{
		super.read(nbt); // The super call is required to save and load the tiles location
		
		contentFromNBT(nbt);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT nbt)	{
		contentToNBT(nbt);
		
		return super.write(nbt); // The super call is required to save and load the tileEntity's location
	}
	
    /////////////////////////////////////////////////////////
    ///Sync
    /////////////////////////////////////////////////////////
	@Override
    public void prepareS2CPacketData(CompoundNBT nbt) {
		contentToNBT(nbt);
    }

	@Override
	@OnlyIn(Dist.CLIENT)
    public void onSyncDataFromServerArrived(CompoundNBT nbt) {
		contentFromNBT(nbt);
    }
	
    /////////////////////////////////////////////////////////
    ///Utils
    /////////////////////////////////////////////////////////
	private int rotation = -1;

	@OnlyIn(Dist.CLIENT)
	public int getRotation() {
		if (rotation == -1) {
			int facing = BlockSignBase.getRotationInt(getBlockState());
			rotation = -facing*90;
		}
		
		return rotation;
	}
}
