package mc.Mitchellbrine.wip.block.conduit.logic;


import cpw.mods.fml.common.eventhandler.Event;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2014.
 */
public class InventoryTypeChangeEvent extends Event {

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


    /**
     * The ItemStacks being pulled by conduit. This is what is seen after the filter has taken effect. Use the InventoryTypeInventoryEvent for altering filtering.
     */
    public ArrayList<ItemStack> stacks;

     public InventoryTypeChangeEvent(TileEntityConduit conduit, InventoryType type, int x, int y, int z, ArrayList<ItemStack> stacks) {
        this.tileEntityConduit = conduit;
        this.inventoryType = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.stacks = stacks;
    }


}
