package org.ztouhou.mcmod.decoration.client;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.ztouhou.mcmod.decoration.item.ItemLED12864ISP;
import org.ztouhou.mcmod.decoration.network.MessageLED12864;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLED12864ISP extends GuiScreen {
	private final static ResourceLocation background = new ResourceLocation("ztouhoudecoration:textures/gui/led12864isp.png");
    private GuiTextField[] textFields;
    private final String[] initText;


    public GuiLED12864ISP(ItemStack stack){
    	this.initText = ItemLED12864ISP.getContent(stack);
    }

    @Override
    public void initGui()
    {
        int xSize = 176,ySize = 133;
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        Keyboard.enableRepeatEvents(true);
        this.textFields = new GuiTextField[] {
                new GuiTextField(0, fontRenderer, x+32,y+20 , 123, 10),
                new GuiTextField(1, fontRenderer, x+32,y+31 , 123, 10),
                new GuiTextField(2, fontRenderer, x+32,y+42 , 123, 10),
                new GuiTextField(3, fontRenderer, x+32,y+53 , 123, 10)
        };
        
        for (int i=0; i<initText.length; i++) {
        	GuiTextField textField = this.textFields[i];
        	textField.setCanLoseFocus(true);
        	textField.setText(this.initText[i]);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        ResourceLocation textures = (background);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        int xSize = 176,ySize = 133;
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        super.drawScreen(mouseX, mouseY, partialTicks);

        for(GuiTextField i : this.textFields){
            i.drawTextBox();
        }
        
        drawCenteredString(fontRenderer, I18n.translateToLocal("gui.ztouhoudecoration:led12864isp.title"), width/2, (int)(height*0.05), 0xFFFF00);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException{
        for(GuiTextField i : textFields){
            if(i.isFocused()){
                if(i.textboxKeyTyped(typedChar, keyCode))
                    return;
            }
        }
		super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)  throws IOException{
        for(GuiTextField i : textFields){
            i.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        String str[] = new String[] {
                textFields[0].getText(),
                textFields[1].getText(),
                textFields[2].getText(),
                textFields[3].getText(),
        };
        
        MessageLED12864.sendISPModificationPacket(str);
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
