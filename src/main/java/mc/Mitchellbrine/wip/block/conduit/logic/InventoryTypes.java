package mc.Mitchellbrine.wip.block.conduit.logic;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2014.
 */
public class InventoryTypes {

    public static ArrayList<InventoryType> types = new ArrayList<InventoryType>();

    public static InventoryType furnace;
    public static InventoryType chest;

    public static void registerVanillaTypes() {
        furnace = new InventoryType("furnace",3,new Block[]{Blocks.furnace,Blocks.lit_furnace});
        chest = new InventoryType("chest",27,new Block[]{Blocks.chest,Blocks.trapped_chest});
    }

}