package org.ztouhou.mcmod.decoration.client;

import org.lwjgl.opengl.GL11;
import org.ztouhou.mcmod.decoration.blocks.tileentity.ContainerFireExtinguisherBox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiFireExtinguisherBox extends ContainerScreen<ContainerFireExtinguisherBox> {
	public GuiFireExtinguisherBox(ContainerFireExtinguisherBox screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().textureManager.bindTexture(new ResourceLocation("ztouhoudecoration:textures/gui/fireextinguisherbox.png"));
        blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
