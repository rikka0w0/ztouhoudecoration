package org.ztouhou.mcmod.decoration.blocks.tileentity;

import java.util.List;

import org.ztouhou.mcmod.decoration.Decoration;
import org.ztouhou.mcmod.decoration.blocks.BlockSignBase;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import rikka.librikka.RayTraceHelper;
import rikka.librikka.tileentity.TileEntityBase;

public class TileEnityUrinals extends TileEntityBase implements ITickableTileEntity {
	private State state = State.Idle;
	private boolean detected;
	private byte timer;
	private byte flushTimer;
	
	private int rotationInt = -1;
	
	public TileEnityUrinals() {
		super(Decoration.MODID);
	}
    /////////////////////////////////////////////////////////
    ///Utils
    /////////////////////////////////////////////////////////
	private int getRotation() {
		if (rotationInt == -1)
			rotationInt = BlockSignBase.getRotationInt(world.getBlockState(pos));
		
		return rotationInt;
	}

	@OnlyIn(Dist.CLIENT)
	public State getStateForRendering() {	
		return state;
	}
	
    ///////////////////////////////////
    /// ITickable
    ///////////////////////////////////
	@Override
	public void tick() {		
		if (world.isRemote) {
			//Update client rendering
			if (this.state == State.Flushing) {
				double xpos = pos.getX() + 0.5 + 0.25 * world.rand.nextDouble();
			    double ypos = pos.getY() + 0.5;
			    double zpos = pos.getZ() + 0.5 + 0.25 * world.rand.nextDouble();
			    double velocityX = 0; // increase in x position every tick
			    double velocityY = 0.1 + 0.2 * world.rand.nextDouble(); // increase in y position every tick;
			    double velocityZ = 0; // increase in z position every tick
			    
			    world.addParticle(ParticleTypes.DRIPPING_WATER, true, xpos, ypos, zpos, velocityX, velocityY, velocityZ);
			    world.addParticle(ParticleTypes.LARGE_SMOKE, true, xpos, ypos, zpos, velocityX, velocityY, velocityZ);
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
		List<PlayerEntity> playerList = world.getEntitiesWithinAABB(PlayerEntity.class, scanRange[getRotation()].offset(pos));
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
    public void prepareS2CPacketData(CompoundNBT nbt) {
		nbt.putByte("state", (byte) state.ordinal());
    }

	@Override
	@OnlyIn(Dist.CLIENT)
    public void onSyncDataFromServerArrived(CompoundNBT nbt) {
		this.state = State.parse(nbt.getByte("state"));
		
		this.markForRenderUpdate();
    }
	
	@Override
    protected void collectModelData(ModelDataMap.Builder builder) {
		builder.withInitial(State.prop, state);
	}
	
    /////////////////////////////////////////////////////////
    /// Player Detection
    /////////////////////////////////////////////////////////
	private final static AxisAlignedBB[] scanRange = new AxisAlignedBB[4];;
	
	static {
		scanRange[0] = RayTraceHelper.createAABB(Direction.SOUTH, -2, 0, -0.5F, 1, 1.5F, 1.5F);
		scanRange[1] = RayTraceHelper.createAABB(Direction.EAST, -2, 0, -0.5F, 1, 1.5F, 1.5F);
    	scanRange[2] = RayTraceHelper.createAABB(Direction.NORTH, -2, 0, -0.5F, 1, 1.5F, 1.5F);
    	scanRange[3] = RayTraceHelper.createAABB(Direction.WEST, -2, 0, -0.5F, 1, 1.5F, 1.5F);
	}
	
	public static enum State {
		Idle, Using, Flushing, AfterUse;
		
		public final static ModelProperty<State> prop = new ModelProperty<>();
		
		public static State parse(int id) {
			if (id<State.values().length)
				return State.values()[id];
			return Idle;
		}
	}
}
