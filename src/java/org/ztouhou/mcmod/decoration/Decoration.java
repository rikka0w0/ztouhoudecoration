package org.ztouhou.mcmod.decoration;

import org.ztouhou.mcmod.decoration.network.MessageLED12864;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
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
    	new CreativeTab();
    	
        ItemRegistry.registerItems();
        BlockRegistry.registerBlocks();
        
        networkChannel = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        //Register network channels
        networkChannel.registerMessage(MessageLED12864.Handler.class, MessageLED12864.class, 0, Side.SERVER);
    	proxy.preInit();
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
