package org.ztouhou.mcmod.decoration.blocks;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

import org.ztouhou.mcmod.decoration.Decoration;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityFireExtinguisherBox;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import rikka.librikka.Utils;
import rikka.librikka.Properties;
import rikka.librikka.RayTraceHelper;

public class BlockSign4 extends BlockSignBase implements ITileEntityProvider{
	public static String[] subNames = new String[] {"lcd", "urinals", "fireextinguisher", "exit"};
	
	public BlockSign4() {
		super("sign4", Material.ROCK);
		
        setHardness(5.0F);
        setResistance(10.0F);
	}
	
	@Override
	public void beforeRegister() {
		setCreativeTab(CreativeTabs.REDSTONE);
	}

	@Override
	public String[] getSubBlockUnlocalizedNames() {
		return subNames;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {return null;}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		int type = state.getValue(Properties.type2bit);
		if (type != 2)
			return null;
		
		return new TileEntityFireExtinguisherBox();
	}
	
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (player.isSneaking())
            return false;
        
		int type = state.getValue(Properties.type2bit);
		if (type != 2)
			return false;
        
        if (!world.isRemote)
        	Decoration.openGui(player, world, pos, facing);
        
        return true;
    }
    
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity te = world.getTileEntity(pos);
        
        if (te instanceof TileEntityFireExtinguisherBox) {
        	InventoryHelper.dropInventoryItems(world, pos, (TileEntityFireExtinguisherBox) te);
        }
        
        super.breakBlock(world, pos, state);
    }
	
    ///////////////////
    /// BoundingBox
    ///////////////////
	protected final static AxisAlignedBB[] boundingBoxes0, boundingBoxes1, boundingBoxes2;
	protected final static List<AxisAlignedBB>[] hitboxes = new List[4];
	
    static {
    	boundingBoxes0 = new AxisAlignedBB[4];
    	boundingBoxes0[0] = RayTraceHelper.createAABB(EnumFacing.SOUTH, 0.2F, 0, 0, 0.8F, 0.1F, 1);
    	boundingBoxes0[1] = RayTraceHelper.createAABB(EnumFacing.EAST, 0.2F, 0, 0, 0.8F, 0.1F, 1);
    	boundingBoxes0[2] = RayTraceHelper.createAABB(EnumFacing.NORTH, 0.2F, 0, 0, 0.8F, 0.1F, 1);
    	boundingBoxes0[3] = RayTraceHelper.createAABB(EnumFacing.WEST, 0.2F, 0, 0, 0.8F, 0.1F, 1);
    	
    	boundingBoxes1 = new AxisAlignedBB[4];
    	boundingBoxes1[0] = RayTraceHelper.createAABB(EnumFacing.SOUTH, 0, 0, 0.05F, 1, 0.65F, 0.95F);
    	boundingBoxes1[1] = RayTraceHelper.createAABB(EnumFacing.EAST, 0, 0, 0.05F, 1, 0.65F, 0.95F);
    	boundingBoxes1[2] = RayTraceHelper.createAABB(EnumFacing.NORTH, 0, 0, 0.05F, 1, 0.65F, 0.95F);
    	boundingBoxes1[3] = RayTraceHelper.createAABB(EnumFacing.WEST, 0, 0, 0.05F, 1, 0.65F, 0.95F);
    	
    	boundingBoxes2 = new AxisAlignedBB[4];
    	boundingBoxes2[0] = RayTraceHelper.createAABB(EnumFacing.SOUTH, 0, 0, 0.05F, 0.85F, 0.55F, 0.95F);
    	boundingBoxes2[1] = RayTraceHelper.createAABB(EnumFacing.EAST, 0, 0, 0.05F, 0.85F, 0.55F, 0.95F);
    	boundingBoxes2[2] = RayTraceHelper.createAABB(EnumFacing.NORTH, 0, 0, 0.05F, 0.85F, 0.55F, 0.95F);
    	boundingBoxes2[3] = RayTraceHelper.createAABB(EnumFacing.WEST, 0, 0, 0.05F, 0.85F, 0.55F, 0.95F);
    	
    	for (int i=0; i<4; i++) {
    		EnumFacing side = Utils.horizontalInverted[i];
    		List<AxisAlignedBB> hitbox = new LinkedList();
    		hitbox.add(RayTraceHelper.createAABB(side, 0, 0, 0.055F, 1, 0.1F, 0.945F));
    		hitbox.add(RayTraceHelper.createAABB(side, 0.8F, 0, 0.055F, 1, 0.25F, 0.945F));
    		hitbox.add(RayTraceHelper.createAABB(side, 0, 0, 0.055F, 1, 0.25F, 0.125F));
    		hitbox.add(RayTraceHelper.createAABB(side, 0, 0, 0.055F, 0.33F, 0.65F, 0.125F));
    		hitbox.add(RayTraceHelper.createAABB(side, 0, 0, 0.875F, 1, 0.25F, 0.945F));
    		hitbox.add(RayTraceHelper.createAABB(side, 0, 0, 0.875F, 0.33F, 0.65F, 0.945F));
    		hitbox.add(RayTraceHelper.createAABB(side, 0F, 0.55F, 0.055F, 0.33F, 0.65F, 0.945F));
    		hitbox.add(RayTraceHelper.createAABB(side, 0F, 0, 0.055F, 0.25F, 0.65F, 0.945F));
    		hitboxes[i] = hitbox;
    	}
    }
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    	int type = state.getValue(Properties.type2bit);
    	int rotation = state.getValue(Properties.facing2bit);
    	
    	if (type == 0)
    		return boundingBoxes0[rotation];
    	if (type == 1)
    		return boundingBoxes1[rotation];
    	if (type == 2)
    		return boundingBoxes2[rotation];

    	return boundingBoxes[rotation];
    }
    
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean magicBool) {   	
    	int type = state.getValue(Properties.type2bit);   	
    	if (type == 1) {
        	int facing = state.getValue(Properties.facing2bit);
        	for (AxisAlignedBB hitbox: hitboxes[facing]) {
        		addCollisionBoxToList(pos, entityBox, collidingBoxes, hitbox);
        	}
    	}else {
    		super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, magicBool);
    	}
    }
}
