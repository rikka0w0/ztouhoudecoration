package org.ztouhou.mcmod.decoration.network;

import java.util.function.Supplier;

import org.ztouhou.mcmod.decoration.Decoration;
import org.ztouhou.mcmod.decoration.item.ItemLED12864ISP;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import rikka.librikka.ByteSerializer;

/**
 * Client to Server packet
 * @author Rikka0_0
 */
public class MessageLED12864 {
	@OnlyIn(Dist.CLIENT)
	public static void sendISPModificationPacket(String[] content) {
		Decoration.instance.networkChannel.sendToServer(new MessageLED12864(content));
	}
	
	private String[] content;
		
	private MessageLED12864(String[] content) {
		this.content = content;
	}
	
	public static MessageLED12864 fromBytes(ByteBuf buf) {
		int numOfRows = buf.readByte();
		
		String[] content = new String[numOfRows];
				
		for (int i=0; i<numOfRows; i++)
			content[i] = (String) ByteSerializer.instance.unpackData(buf);
		
		return new MessageLED12864(content);
	}

	public static void toBytes(MessageLED12864 msg, ByteBuf buf) {
		buf.writeByte(msg.content.length);
		
		for (int i=0; i<msg.content.length; i++)
			ByteSerializer.instance.packData(buf, msg.content[i]);
	}

	/**
	 * Processed on server side only!
	 */
	public static void handler(final MessageLED12864 message, Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			final ServerPlayerEntity player = ctx.get().getSender();
			
            //Make sure the actual modification is done on the server-thread.
            ctx.get().enqueueWork(() -> ItemLED12864ISP.setContent(player.getHeldItemMainhand(), message.content));
            ctx.get().setPacketHandled(true);
		}
	}
}
