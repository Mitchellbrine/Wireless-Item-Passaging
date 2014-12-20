package mc.Mitchellbrine.wip.util;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ConfigUtils {

    public static int range;
    public static int delay;

    public static void init(Configuration config) {
        config.load();

        range = config.get("conduit","Conduit GPS Range",50,"Sets the max range for the GPS to set the coordinates for the Conduit").getInt(50);
        delay = config.get("conduit","Conduit Transport Delay",5,"Sets the delay of the conduit based on tick rate (20 = 1 second)").getInt(5);

        config.save();
    }

}