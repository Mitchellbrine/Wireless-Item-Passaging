package mc.Mitchellbrine.wip.block.conduit.logic;

import cpw.mods.fml.common.eventhandler.Event;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2015.
 */
public class InventoryTypeInventoryEvent extends Event {

    /**
     * The conduit from which the event is firing from (you can handle inventory manipulation with this)
     */
    public TileEntityConduit tileEntityConduit;

    /**
     * The inventory type in which the event is being fired from
     */
    public InventoryType inventoryType;

    /**
     * The x, y, and z coords for the block the conduit is taking from. Can be replaced with (int)tileEntityConduit.getPullLoc().xCoord, (int)tileEntityConduit.getPullLoc().yCoord, (int)tileEntityConduit.getPullLoc().zCoord
     */
    public int x, y, z;

    public ArrayList<ItemStack> stacks;

    public InventoryTypeInventoryEvent(TileEntityConduit conduit, InventoryType type, int x, int y, int z) {
        this.tileEntityConduit = conduit;
        this.inventoryType = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * This is what is used in TileEntityConduit.getItemStacksToPull()
     * @return the ItemStacks which fit the filter correctly
     */
    public ArrayList<ItemStack> getItemsToPull() {
        return stacks;
    }

}
