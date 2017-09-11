package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.client.GuiLED12864ISP;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.AutoGuiHandler;

public class GuiHandler extends AutoGuiHandler{
	public static enum GuiType {
		LED12864ISP(7);

		private final int GuiID;
		GuiType(int GuiID){
			this.GuiID = GuiID;
		}
		
		public static GuiType parse(int i) {
			if (i >= GuiType.values().length)
				return null;
			return GuiType.values()[i];
		}
	}
	
	@Override
	protected Container getContainer(int ID, EntityPlayer player, World world, BlockPos pos) {
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected GuiScreen getGui(int ID, EntityPlayer player, World world, BlockPos pos) {
		switch (GuiType.parse(ID)) {
		case LED12864ISP:
			ItemStack itemStack = player.getHeldItemMainhand();
			if (itemStack.isEmpty())
				itemStack = player.getHeldItemOffhand();
			return new GuiLED12864ISP(itemStack);
		}
		return null;
	}
	
    public static void openGui(EntityPlayer player, World world, BlockPos pos, GuiType gui) {
    	player.openGui(Decoration.instance, gui.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
    }
	
    public static void openGui(EntityPlayer player, World world, BlockPos pos, EnumFacing facing) {
    	player.openGui(Decoration.instance, facing.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
    }
}
