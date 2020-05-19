package org.ztouhou.mcmod.decoration.client;

import org.lwjgl.opengl.GL11;
import org.ztouhou.mcmod.decoration.item.ItemLED12864ISP;
import org.ztouhou.mcmod.decoration.network.MessageLED12864;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiLED12864ISP extends Screen {
	private final static ResourceLocation background = new ResourceLocation("ztouhoudecoration:textures/gui/led12864isp.png");
    private TextFieldWidget[] textFields;
    private final String[] initText;


    public GuiLED12864ISP(ItemStack stack){
    	super(new TranslationTextComponent("gui.ztouhoudecoration.led12864isp.title"));
    	this.initText = ItemLED12864ISP.getContent(stack);
    }

    @Override
    public void init() {
        int xSize = 176,ySize = 133;
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        this.minecraft.keyboardListener.enableRepeatEvents(true);
        this.textFields = new TextFieldWidget[] {
                new TextFieldWidget(this.font, x+32,y+20 , 123, 10, "cyka"),
                new TextFieldWidget(this.font, x+32,y+31 , 123, 10, ""),
                new TextFieldWidget(this.font, x+32,y+42 , 123, 10, ""),
                new TextFieldWidget(this.font, x+32,y+53 , 123, 10, "")
        };
        
        
        
        for (int i=0; i<this.textFields.length; i++) {
        	TextFieldWidget textField = this.textFields[i];
        	textField.setMaxStringLength(256);
        	textField.setText(this.initText[i]);
        	this.children.add(textField);
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
//        drawDefaultBackground();
        ResourceLocation textures = (background);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().textureManager.bindTexture(textures);
        int xSize = 176,ySize = 133;
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        blit(x, y, 0, 0, xSize, ySize);

        super.render(mouseX, mouseY, partialTicks);
        for (TextFieldWidget textField: textFields)
        	textField.render(mouseX, mouseY, partialTicks);

        drawCenteredString(font, this.title.getFormattedText(), width/2, (int)(height*0.05), 0xFFFF00);
    }

    @Override
    public void onClose() {
    	this.minecraft.keyboardListener.enableRepeatEvents(false);
        String str[] = new String[] {
                textFields[0].getText(),
                textFields[1].getText(),
                textFields[2].getText(),
                textFields[3].getText(),
        };
        
        MessageLED12864.sendISPModificationPacket(str);
        
        super.onClose();
    }
    
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
