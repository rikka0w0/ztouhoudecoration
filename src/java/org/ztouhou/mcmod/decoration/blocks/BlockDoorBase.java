package org.ztouhou.mcmod.decoration.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import rikka.librikka.item.ISimpleTexture;

public abstract class BlockDoorBase extends BlockDoor{
	public static class Door1 extends BlockDoorBase {
		public Door1() {
			super("door1", Material.WOOD);
			setHardness(3.0F);
			setSoundType(SoundType.WOOD);
		}
		
		@Override
	    @SideOnly(Side.CLIENT)
	    public BlockRenderLayer getBlockLayer()
	    {
	        return BlockRenderLayer.TRANSLUCENT;
	    }
	}
	
	public static class Door2 extends BlockDoorBase {
		public Door2() {
			super("door2", Material.WOOD);

			setHardness(3.0F);
			setSoundType(SoundType.WOOD);
		}
	}
	
	public static class Door3 extends BlockDoorBase {
		public Door3() {
			super("door3", Material.WOOD);

			setHardness(3.0F);
			setSoundType(SoundType.WOOD);
		}
		
		@Override
	    @SideOnly(Side.CLIENT)
	    public BlockRenderLayer getBlockLayer()
	    {
	        return BlockRenderLayer.TRANSLUCENT;
	    }
	}
	
	public static class Door4 extends BlockDoorBase {
		public Door4() {
			super("door4", Material.WOOD);

			setHardness(3.0F);
			setSoundType(SoundType.WOOD);
		}
	}
	
	public static class ItemBlockDoor extends ItemBlock implements ISimpleTexture{
		private final String textureName;
		
		public ItemBlockDoor(BlockDoorBase blockDoor, String textureName) {
			super(blockDoor);
			this.textureName = textureName;
		}
		
	    /**
	     * Called when a Block is right-clicked with this Item
	     */
		@Override
	    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	        if (facing != EnumFacing.UP)
	        {
	            return EnumActionResult.FAIL;
	        }
	        else
	        {
	            IBlockState iblockstate = worldIn.getBlockState(pos);
	            Block block = iblockstate.getBlock();

	            if (!block.isReplaceable(worldIn, pos))
	            {
	                pos = pos.offset(facing);
	            }

	            ItemStack itemstack = player.getHeldItem(hand);

	            if (player.canPlayerEdit(pos, facing, itemstack) && this.block.canPlaceBlockAt(worldIn, pos))
	            {
	                EnumFacing enumfacing = EnumFacing.fromAngle((double)player.rotationYaw);
	                int i = enumfacing.getFrontOffsetX();
	                int j = enumfacing.getFrontOffsetZ();
	                boolean flag = i < 0 && hitZ < 0.5F || i > 0 && hitZ > 0.5F || j < 0 && hitX > 0.5F || j > 0 && hitX < 0.5F;
	                ItemDoor.placeDoor(worldIn, pos, enumfacing, this.block, flag);
	                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
	                worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
	                itemstack.shrink(1);
	                return EnumActionResult.SUCCESS;
	            }
	            else
	            {
	                return EnumActionResult.FAIL;
	            }
	        }
	    }

		@Override
		public String getIconName(int damage) {
			return textureName;
		}
	}
	
	public final ItemBlockDoor itemBlock;
	
	public BlockDoorBase(String unlocalizedName, Material material) {
		super(material);
		this.itemBlock = new ItemBlockDoor(this, unlocalizedName);
		
        setUnlocalizedName(unlocalizedName);
        setRegistryName(unlocalizedName);                //Key!
        setCreativeTab(CreativeTabs.REDSTONE);
        
        GameRegistry.register(this);
        GameRegistry.register(itemBlock, getRegistryName());
	}

	@Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(itemBlock);
    }
}
