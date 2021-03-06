package mc.Mitchellbrine.wip.item;

import cpw.mods.fml.common.registry.GameRegistry;
import mc.Mitchellbrine.wip.WirelessItemPassaging;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.item.Item;

import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ItemRegistry {

    public static ArrayList<Item> items = new ArrayList<Item>();

    public static Item gps;
    public static Item wirelessTransportCore;

    public static Item blankModule;
    public static Item moduleFilter;
    //public static Item moduleSecurity;

    public static void init() {

        gps = new ItemGPS();
        wirelessTransportCore = new ItemShiny().setMaxStackSize(1).setUnlocalizedName("wipCore").setTextureName(References.RESOURCEPREFIX + "wipCore");

        blankModule = new ItemNP().setMaxStackSize(1).setUnlocalizedName("wipModule").setTextureName(References.RESOURCEPREFIX + "wipModule");
        moduleFilter = new ItemNP().setMaxStackSize(1).setUnlocalizedName("wipModuleFilter").setTextureName(References.RESOURCEPREFIX + "wipModuleFilter");


        for (Item item : items) {
            GameRegistry.registerItem(item,item.getUnlocalizedName().substring(5));
            WirelessItemPassaging.logger.info("Registed item " + item.getUnlocalizedName().substring(5));
        }

    }

}
