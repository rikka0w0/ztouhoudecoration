package org.ztouhou.mcmod.decoration.client;

import org.ztouhou.mcmod.decoration.blocks.tileentity.TileEntityLED12864;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class LED12864Render extends TileEntityRenderer<TileEntityLED12864> {	
    public LED12864Render(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(TileEntityLED12864 te, float partialTicks, MatrixStack matrixStack,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (te == null || te.content == null)
			return;
		
    	matrixStack.push();
    	matrixStack.translate(0.5, 1.5, 0.5);
    	matrixStack.rotate(new Quaternion(new Vector3f(0, 0, 1), 180, true));
    	matrixStack.rotate(new Quaternion(new Vector3f(0, 1, 0), te.getRotation(), true));
        
        matrixStack.push();
        FontRenderer fr = this.renderDispatcher.getFontRenderer();
        matrixStack.translate(0.0F,1.0F,0.39F);
        matrixStack.scale(0.01F, 0.01F, 0.01F);
        for (int i=0; i<te.content.length; i++) {
        	String rowContent = te.content[i];
            int width = fr.getStringWidth(rowContent);
            fr.renderString(rowContent, -width/2, i*10-20, 0xFFFF0000, false, matrixStack.getLast().getMatrix(), bufferIn, false, 0, combinedLightIn);
        }
        matrixStack.pop();

        matrixStack.pop();
//SignTileEntityRenderer		
	}
}
