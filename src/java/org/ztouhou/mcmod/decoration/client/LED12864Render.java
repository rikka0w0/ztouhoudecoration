package org.ztouhou.mcmod.decoration.client;

import org.lwjgl.opengl.GL11;
import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class LED12864Render extends TileEntitySpecialRenderer<TileEntityLED12864> {	
    @Override
    public void renderTileEntityAt(TileEntityLED12864 te, double x, double y, double z, float partialTicks, int destroyStage) {
        if (te.content == null)
        	return;
    	
    	GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(te.getRotation(), 0, 1, 0);
        
        GL11.glPushMatrix();
        FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
        GL11.glTranslatef(0.0F,1.0F,0.39F);
        GL11.glScalef(0.01F, 0.01F, 0.01F);
        GL11.glNormal3f(0.0F, 0.0F, -0.01F);
        GL11.glDepthMask(false);
        for (int i=0; i<te.content.length; i++) {
        	String rowContent = te.content[i];
            int width = fr.getStringWidth(rowContent);
            fr.drawString(rowContent,-width/2, i*10-20, 0xFFFF0000);
        }
        GL11.glDepthMask(true);
        GL11.glPopMatrix();

        GL11.glPopMatrix();

    }
}
