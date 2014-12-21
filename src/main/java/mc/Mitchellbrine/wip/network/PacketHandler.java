package mc.Mitchellbrine.wip.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import mc.Mitchellbrine.wip.util.References;

/**
 * Created by Mitchellbrine on 2014.
 */
public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("WIP");

    public static void init() {
        INSTANCE.registerMessage(NBTUpdatePacket.class, NBTUpdatePacket.class, 0, Side.CLIENT);
    }

}
