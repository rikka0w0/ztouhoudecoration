package org.ztouhou.mcmod.decoration;

import java.util.Optional;

import org.ztouhou.mcmod.decoration.network.MessageLED12864;


import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(Decoration.MODID)
public class Decoration {
    public static final String MODID = "ztouhoudecoration";

    public static CommonProxy proxy = DistExecutor.runForDist(()->()->new ClientProxy(), ()->()->new CommonProxy());

    public static Decoration instance;
    
    public static ItemGroup itemGroup;

    private static final String PROTOCOL_VERSION = "1";
    public SimpleChannel networkChannel;
    
    public Decoration() {
        if (instance == null)
            instance = this;
        else
            throw new RuntimeException("Duplicated Class Instantiation: SimElectricity");
    }
    
    @Mod.EventBusSubscriber(modid = Decoration.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
    	@SubscribeEvent
    	public static void newRegistry(RegistryEvent.NewRegistry event) {
            // Register creative tabs
    		itemGroup = new ItemGroup(Decoration.MODID) {
                @Override
                @OnlyIn(Dist.CLIENT)
                public ItemStack createIcon() {
                    return new ItemStack(BlockRegistry.blockMisc[2]);
                }
            };
    	}
    	
    	@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
    		IForgeRegistry registry = event.getRegistry();
    		BlockRegistry.initBlocks();
    		BlockRegistry.registerBlocks(registry, false);
    	}
    	
    	@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
    		IForgeRegistry registry = event.getRegistry();
    		ItemRegistry.initItems();
    		BlockRegistry.registerBlocks(registry, true);
    		ItemRegistry.registerItems(registry);
    	}
    	
    	@SubscribeEvent
    	public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {
    		BlockRegistry.registerTileEntities(event.getRegistry());
    	}
    	
    	@SubscribeEvent
    	public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
    		BlockRegistry.registerContainers(event.getRegistry());
    	}
    	
    	@SubscribeEvent
    	public static void onCommonSetup(FMLCommonSetupEvent event) {
    		Decoration.instance.networkChannel = NetworkRegistry.newSimpleChannel(
    			    new ResourceLocation(MODID, "network_channel"),
    			    () -> PROTOCOL_VERSION,
    			    PROTOCOL_VERSION::equals,
    			    PROTOCOL_VERSION::equals
    			);
            
            Decoration.instance.networkChannel.registerMessage(0, 
            		MessageLED12864.class, MessageLED12864::toBytes, MessageLED12864::fromBytes, 
            		MessageLED12864::handler, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    	}
    }
}
