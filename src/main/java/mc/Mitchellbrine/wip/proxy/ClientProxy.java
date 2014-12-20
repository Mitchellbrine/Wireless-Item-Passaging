package mc.Mitchellbrine.wip.proxy;


import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import mc.Mitchellbrine.wip.WirelessItemPassaging;
import mc.Mitchellbrine.wip.client.gui.GuiHandler;
import mc.Mitchellbrine.wip.client.render.ConduitTESR;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerStuff() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityConduit.class, new ConduitTESR());
        NetworkRegistry.INSTANCE.registerGuiHandler(WirelessItemPassaging.instance,new GuiHandler());
    }

}
