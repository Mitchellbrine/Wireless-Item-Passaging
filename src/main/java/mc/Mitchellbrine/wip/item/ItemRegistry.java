package mc.Mitchellbrine.wip.item;

import cpw.mods.fml.common.registry.GameRegistry;
import mc.Mitchellbrine.wip.WirelessItemPassaging;
import net.minecraft.item.Item;

import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ItemRegistry {

    public static ArrayList<Item> items = new ArrayList<Item>();

    public static Item gps;

    public static void init() {

        gps = new ItemGPS();

        for (Item item : items) {
            GameRegistry.registerItem(item,item.getUnlocalizedName().substring(5));
            WirelessItemPassaging.logger.info("Registed item " + item.getUnlocalizedName().substring(5));
        }

    }

}
