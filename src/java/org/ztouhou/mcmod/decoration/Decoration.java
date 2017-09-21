package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.network.MessageLED12864;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Decoration.MODID, name = Decoration.NAME, version = Decoration.VERSION)
public class Decoration {
    public static final String MODID = "ztouhoudecoration";
    public static final String NAME = "zTouhouDecoration";
    public static final String VERSION = "0.2";
    
    @SidedProxy(clientSide = "org.ztouhou.mcmod.decoration.ClientProxy", serverSide = "org.ztouhou.mcmod.decoration.CommonProxy")
    public static CommonProxy proxy;
    
    @Mod.Instance(Decoration.MODID)
    public static Decoration instance;
    
    public SimpleNetworkWrapper networkChannel;
    
    /**
     * PreInitialize
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        networkChannel = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        //Register network channels
        networkChannel.registerMessage(MessageLED12864.Handler.class, MessageLED12864.class, 0, Side.SERVER);
    	proxy.preInit();
    }
    
    @Mod.EventBusSubscriber(modid = Decoration.MODID)
    public static class RegistrationHandler {
    	@SubscribeEvent
    	public static void newRegistry(RegistryEvent.NewRegistry event) {
    		new CreativeTab();
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
    }
    
    /**
     * Initialize
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        BlockRegistry.registerTileEntities();

        proxy.init();

        //Register GUI handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }
    
    /**
     * PostInitialize
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit();
    }
}
