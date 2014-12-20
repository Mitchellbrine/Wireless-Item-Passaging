package mc.Mitchellbrine.wip.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import mc.Mitchellbrine.wip.container.ContainerConduit;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Mitchellbrine on 2014.
 */
public class GuiHandler implements IGuiHandler{

    public static class IDS {
        public static final int Conduit = 0;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x,y,z);
        if (entity != null) {
            switch (ID) {
                case IDS.Conduit:
                    return new ContainerConduit(player);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x,y,z);
        if (entity != null) {
            switch (ID) {
                case IDS.Conduit:
                    return new GuiConduit(player, (TileEntityConduit)entity);
            }
        }
        return null;
    }
}
