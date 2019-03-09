package org.ztouhou.mcmod.decoration.blocks.tileentity;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.RayTraceHelper;
import rikka.librikka.properties.Properties;
import rikka.librikka.tileentity.TileEntityBase;

public class TileEnityUrinals extends TileEntityBase implements ITickable {
	private State state = State.Idle;
	private boolean detected;
	private byte timer;
	private byte flushTimer;
	
	private int rotationInt = -1;
	private EnumFacing facing = null;
	
    /////////////////////////////////////////////////////////
    ///Utils
    /////////////////////////////////////////////////////////
	private int getRotation() {
		if (rotationInt == -1)
			rotationInt = world.getBlockState(pos).getValue(Properties.facing2bit);
		
		return rotationInt;
	}
	
	@SideOnly(Side.CLIENT)
	public State getStateForRendering() {	
		return state;
	}
	
    ///////////////////////////////////
    /// ITickable
    ///////////////////////////////////
	@Override
	public void update() {		
		if (world.isRemote) {
			//Update client rendering
			if (this.state == State.Flushing) {
				double xpos = pos.getX() + 0.5 + 0.25 * world.rand.nextDouble();
			    double ypos = pos.getY() + 0.5;
			    double zpos = pos.getZ() + 0.5 + 0.25 * world.rand.nextDouble();
			    double velocityX = 0; // increase in x position every tick
			    double velocityY = 0.1 + 0.2 * world.rand.nextDouble(); // increase in y position every tick;
			    double velocityZ = 0; // increase in z position every tick
			    int [] extraInfo = new int[0];  // extra information if needed by the particle - in this case unused
			    
			    world.spawnParticle(EnumParticleTypes.WATER_DROP, xpos, ypos, zpos, velocityX, velocityY, velocityZ, extraInfo);
			    world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, xpos, ypos, zpos, velocityX, velocityY, velocityZ, extraInfo);
			}
			return;
		}
		
		boolean flushDone = false;
		if (this.state == State.Flushing) {
			if (this.flushTimer <= 30)
				this.flushTimer++;
			else 
				flushDone = true;
		}
		
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
		
		
		if (detected != this.detected || this.state == State.Flushing || this.state == State.AfterUse) {
			this.detected = detected;
			
			//Update State Machine
			State newState = this.state;
			switch (this.state) {
			case Idle:
				if (detected) 
					newState = State.Using;
				break;
			case Using:
				if (!detected) {
					newState = State.Flushing;
					this.flushTimer = 0;
				}
				break;
			case Flushing:
				if (flushDone)
					newState = State.AfterUse;
				
				break;
			case AfterUse:
				if (!detected)
					newState = State.Idle;
				break;
			default:
				break;
			}
			
			if (newState == this.state)
				return;	//State does not change
			
			this.state = newState;
			this.markTileEntityForS2CSync();
		}
	}

	
    /////////////////////////////////////////////////////////
    ///Sync
    /////////////////////////////////////////////////////////
	@Override
    public void prepareS2CPacketData(NBTTagCompound nbt) {
		nbt.setByte("state", (byte) state.ordinal());
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void onSyncDataFromServerArrived(NBTTagCompound nbt) {
		this.state = State.parse(nbt.getByte("state"));
		
		this.markForRenderUpdate();
    }
	
    /////////////////////////////////////////////////////////
    /// Player Detection
    /////////////////////////////////////////////////////////
	private final static AxisAlignedBB[] scanRange = new AxisAlignedBB[4];;
	
	static {
		scanRange[0] = RayTraceHelper.createAABB(EnumFacing.SOUTH, -2, 0, -0.5F, 1, 1.5F, 1.5F);
		scanRange[1] = RayTraceHelper.createAABB(EnumFacing.EAST, -2, 0, -0.5F, 1, 1.5F, 1.5F);
    	scanRange[2] = RayTraceHelper.createAABB(EnumFacing.NORTH, -2, 0, -0.5F, 1, 1.5F, 1.5F);
    	scanRange[3] = RayTraceHelper.createAABB(EnumFacing.WEST, -2, 0, -0.5F, 1, 1.5F, 1.5F);
	}
	
	public static enum State {
		Idle(0), Using(1), Flushing(2), AfterUse(3);
		
		private final int id;
		State(int id){
			this.id = id;
		}
		
		public static State parse(int id) {
			if (id<State.values().length)
				return State.values()[id];
			return Idle;
		}
	}
}
