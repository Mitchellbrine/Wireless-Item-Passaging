package mc.Mitchellbrine.wip;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mc.Mitchellbrine.wip.block.BlockRegistry;
import mc.Mitchellbrine.wip.block.conduit.logic.InventoryTypes;
import mc.Mitchellbrine.wip.item.ItemRegistry;
import mc.Mitchellbrine.wip.proxy.CommonProxy;
import mc.Mitchellbrine.wip.util.References;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Mitchellbrine on 2014.
 */
@Mod(modid=References.MODID,name=References.NAME,version=References.VERSION)
public class WirelessItemPassaging {

    @Mod.Instance
    public static WirelessItemPassaging instance;

    @SidedProxy(clientSide = "mc.Mitchellbrine.wip.proxy.ClientProxy",serverSide = "mc.Mitchellbrine.wip.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger = LogManager.getLogger(References.MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        logger.info("Starting pre-initialization for " + References.NAME);

        BlockRegistry.init();
        ItemRegistry.init();
        InventoryTypes.registerVanillaTypes();

        proxy.registerStuff();

        logger.info("Finished pre-initialization for " + References.NAME);

    }

}
