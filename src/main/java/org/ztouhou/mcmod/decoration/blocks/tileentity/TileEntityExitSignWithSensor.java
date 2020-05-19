package org.ztouhou.mcmod.decoration.blocks.tileentity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;

import org.ztouhou.mcmod.decoration.Decoration;
import org.ztouhou.mcmod.decoration.blocks.BlockSign4;
import org.ztouhou.mcmod.decoration.blocks.BlockSignBase;

import rikka.librikka.RayTraceHelper;
import rikka.librikka.tileentity.TileEntityBase;

public class TileEntityExitSignWithSensor extends TileEntityBase implements ITickableTileEntity {
	public static ModelProperty<Boolean> isForced = new ModelProperty<>(), isDetected = new ModelProperty<>();
	private boolean forcedOn, detected;
	private byte timer;
	
	private int rotationInt = -1;

	public TileEntityExitSignWithSensor() {
		super(Decoration.MODID);
	}
    /////////////////////////////////////////////////////////
    ///Utils
    /////////////////////////////////////////////////////////
	public void toggleForcedOn() {
		this.forcedOn = !this.forcedOn;
		this.markTileEntityForS2CSync();
		updateRedstoneSignal();
	}
	
	private void updateRedstoneSignal() {
		BlockState blockState = this.getBlockState();
		Block block = blockState.getBlock();
		if (block instanceof BlockSign4) {
			((BlockSign4) block).updateRedstoneSignal(this.getWorld(), this.getPos(), blockState);
		}
	}
	
	private int getRotation() {
		if (rotationInt == -1)
			rotationInt = BlockSignBase.getRotationInt(world.getBlockState(pos));
		
		return rotationInt;
	}
	
	public boolean isForced() {	return forcedOn;}
	
	public boolean isDetected() { return detected;}
	
	public boolean isPowered() { return forcedOn || detected;}
	
    ///////////////////////////////////
    /// ITickable
    ///////////////////////////////////
	@Override
	public void tick() {
		if (world.isRemote)
			return;
		
		if (timer < 10) {
			timer++;
			return;
		}
		
		timer = 0;

		//Scan for players
		List<PlayerEntity> playerList = world.getEntitiesWithinAABB(PlayerEntity.class, scanRange[getRotation()].offset(pos));
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
	public void read(CompoundNBT nbt)	{
		super.read(nbt); // The super call is required to save and load the tiles location
		
		this.forcedOn = nbt.getBoolean("forcedOn");
		this.detected = nbt.getBoolean("detected");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT nbt)	{
		nbt.putBoolean("forcedOn", forcedOn);
		nbt.putBoolean("detected", detected);
		
		return super.write(nbt); // The super call is required to save and load the tileEntity's location
	}
	
	@Override
    protected void collectModelData(ModelDataMap.Builder builder) {
		builder.withInitial(isForced, forcedOn);
		builder.withInitial(isDetected, detected);
    }
	
    /////////////////////////////////////////////////////////
    ///Sync
    /////////////////////////////////////////////////////////
	@Override
    public void prepareS2CPacketData(CompoundNBT nbt) {
		nbt.putBoolean("forcedOn", forcedOn);
		nbt.putBoolean("detected", detected);
    }

	@Override
	@OnlyIn(Dist.CLIENT)
    public void onSyncDataFromServerArrived(CompoundNBT nbt) {
		this.forcedOn = nbt.getBoolean("forcedOn");
		this.detected = nbt.getBoolean("detected");
		
		this.markForRenderUpdate();
    }
	
    /////////////////////////////////////////////////////////
    /// Player Detection
    /////////////////////////////////////////////////////////
	private final static AxisAlignedBB[] scanRange = new AxisAlignedBB[4];;
	
	static {
		scanRange[0] = RayTraceHelper.createAABB(Direction.SOUTH, -2, 0, -1, 1, 2, 2);
		scanRange[1] = RayTraceHelper.createAABB(Direction.EAST, -2, 0, -1, 1, 2, 2);
    	scanRange[2] = RayTraceHelper.createAABB(Direction.NORTH, -2, 0, -1, 1, 2, 2);
    	scanRange[3] = RayTraceHelper.createAABB(Direction.WEST, -2, 0, -1, 1, 2, 2);
	}
}
