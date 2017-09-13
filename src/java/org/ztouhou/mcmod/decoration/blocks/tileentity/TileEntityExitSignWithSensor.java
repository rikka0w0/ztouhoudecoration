package org.ztouhou.mcmod.decoration.blocks.tileentity;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.RayTraceHelper;
import rikka.librikka.properties.Properties;
import rikka.librikka.tileentity.TileEntityBase;

public class TileEntityExitSignWithSensor extends TileEntityBase implements ITickable {
	private boolean forcedOn, detected;
	private byte timer;
	
	private int rotationInt = -1;
	private EnumFacing facing = null;
		
    /////////////////////////////////////////////////////////
    ///Utils
    /////////////////////////////////////////////////////////
	public void toggleForcedOn() {
		this.forcedOn = !this.forcedOn;
		this.markTileEntityForS2CSync();
		updateRedstoneSignal();
	}

	public void updateRedstoneSignal() {
		this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
		this.world.notifyNeighborsOfStateChange(pos.offset(getFacing()), this.getBlockType(), false);
	}
	
	private int getRotation() {
		if (rotationInt == -1)
			rotationInt = world.getBlockState(pos).getValue(Properties.facing2bit);
		
		return rotationInt;
	}
	
	public EnumFacing getFacing() {
		if (facing == null) {
	        switch (getRotation()) {
            case 0:
            	facing = EnumFacing.SOUTH; //2
                break;
            case 1:
                facing = EnumFacing.EAST;  //5
                break;
            case 2:
            	facing = EnumFacing.NORTH; //3
                break;
            case 3:
            	facing = EnumFacing.WEST;  //4
                break;
            default:
                break;
	        }
		}
		
		return facing;
	};
	
	public boolean isForced() {	return forcedOn;}
	
	public boolean isDetected() { return detected;}
	
	public boolean isPowered() { return forcedOn || detected;}
	
    ///////////////////////////////////
    /// ITickable
    ///////////////////////////////////
	@Override
	public void update() {
		if (world.isRemote)
			return;
		
		if (timer < 10) {
			timer++;
			return;
		}
		
		timer = 0;
		
		//Scan for players
		List playerList = world.getEntitiesWithinAABB(EntityPlayer.class, scanRange[getRotation()].offset(pos));
		if (playerList == null)
			return;
		
		boolean detected = !playerList.isEmpty();
		if (detected != this.detected) {
			this.detected = detected;
			this.markTileEntityForS2CSync();
			updateRedstoneSignal();
		}
	}
	
    ///////////////////////////////////
    /// TileEntity
    ///////////////////////////////////
	@Override
	public void readFromNBT(NBTTagCompound nbt)	{
		super.readFromNBT(nbt); // The super call is required to save and load the tiles location
		
		this.forcedOn = nbt.getBoolean("forcedOn");
		this.detected = nbt.getBoolean("detected");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)	{
		nbt.setBoolean("forcedOn", forcedOn);
		nbt.setBoolean("detected", detected);
		
		return super.writeToNBT(nbt); // The super call is required to save and load the tileEntity's location
	}
	
    /////////////////////////////////////////////////////////
    ///Sync
    /////////////////////////////////////////////////////////
	@Override
    public void prepareS2CPacketData(NBTTagCompound nbt) {
		nbt.setBoolean("forcedOn", forcedOn);
		nbt.setBoolean("detected", detected);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void onSyncDataFromServerArrived(NBTTagCompound nbt) {
		this.forcedOn = nbt.getBoolean("forcedOn");
		this.detected = nbt.getBoolean("detected");
		
		this.markForRenderUpdate();
    }
	
    /////////////////////////////////////////////////////////
    /// Player Detection
    /////////////////////////////////////////////////////////
	private final static AxisAlignedBB[] scanRange = new AxisAlignedBB[4];;
	
	static {
		scanRange[0] = RayTraceHelper.createAABB(EnumFacing.SOUTH, -2, 0, -1, 1, 2, 2);
		scanRange[1] = RayTraceHelper.createAABB(EnumFacing.EAST, -2, 0, -1, 1, 2, 2);
    	scanRange[2] = RayTraceHelper.createAABB(EnumFacing.NORTH, -2, 0, -1, 1, 2, 2);
    	scanRange[3] = RayTraceHelper.createAABB(EnumFacing.WEST, -2, 0, -1, 1, 2, 2);
	}
}
