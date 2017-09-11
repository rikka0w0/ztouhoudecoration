package org.ztouhou.mcmod.decoration.network;

import java.io.UnsupportedEncodingException;

import org.ztouhou.mcmod.decoration.Decoration;
import org.ztouhou.mcmod.decoration.item.ItemLED12864ISP;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Client to Server packet
 * @author Rikka0_0
 */
public class MessageLED12864 implements IMessage {
	@SideOnly(Side.CLIENT)
	public static void sendISPModificationPacket(String[] content) {
		Decoration.instance.networkChannel.sendToServer(new MessageLED12864(content));
	}
	
	private String[] content;
	
	public MessageLED12864(){}
	
	@SideOnly(Side.CLIENT)
	private MessageLED12864(String[] content) {
		this.content = content;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int numOfRows = buf.readByte();
		int[] rowLength = new int[numOfRows];
		this.content = new String[numOfRows];
		
		for (int i=0; i<numOfRows; i++)
			rowLength[i] = buf.readByte();
		
		try {
			for (int i=0; i<numOfRows; i++) {
				ByteBuf rowBuf = buf.readBytes(rowLength[i]);
				
				this.content[i] = new String(rowBuf.array(),"UTF8");
			}
			
        } catch (Exception e) {
            e.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(content.length);
		
		byte byteBuf[][] = new byte[content.length][];
		
        try {
        	for (int i=0; i<content.length; i++)
        		byteBuf[i] = content[i].getBytes("UTF8");

        	for (int i=0; i<byteBuf.length; i++)
        		buf.writeByte(byteBuf[i].length);
        	
        	for (int i=0; i<byteBuf.length; i++)
        		buf.writeBytes(byteBuf[i]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	}

	public static class Handler implements IMessageHandler<MessageLED12864, IMessage> {
		/**
		 * Processed on server side only!
		 */
		@Override
		public IMessage onMessage(MessageLED12864 message, MessageContext ctx) {
			if (ctx.side == Side.SERVER) {
				EntityPlayerMP player = ctx.getServerHandler().player;
				MinecraftServer server = player.mcServer;
				
                //Make sure the actual modification is done on the server-thread.
                server.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                    	ItemStack itemStack = player.getHeldItemMainhand();
                    	
                    	ItemLED12864ISP.setContent(itemStack, message.content);
	                }//run()
	            });
			}
			return null;
		}
		
	}
}
