package mc.Mitchellbrine.wip.block.conduit.logic;

import cpw.mods.fml.common.registry.GameRegistry;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2014.
 */
public class InventoryTypes {

    public static ArrayList<InventoryType> types = new ArrayList<InventoryType>();

    public static InventoryType furnace;
    public static InventoryType chest;
    public static InventoryType hopper;
    public static InventoryType brewingStand;
    public static InventoryType dispenser;

    public static void registerVanillaTypes() {
        furnace = new InventoryType("furnace",3,new Block[]{Blocks.furnace,Blocks.lit_furnace}, new ResourceLocation(References.RESOURCEPREFIX + "textures/types/furnace.png"),1,new int[]{2},new int[]{0,1});
        chest = new InventoryType("chest",27,new Block[]{Blocks.chest,Blocks.trapped_chest}, new ResourceLocation(References.RESOURCEPREFIX + "textures/types/chest.png"),0,new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26},new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
        hopper = new InventoryType("hopper",5,new Block[]{Blocks.hopper}, new ResourceLocation(References.RESOURCEPREFIX + "textures/types/hopper.png"),0,new int[]{0,1,2,3,4},new int[]{0,1,2,3,4});
        brewingStand = new InventoryType("brewingStand",4,new Block[]{Blocks.brewing_stand}, new ResourceLocation(References.RESOURCEPREFIX + "textures/types/brewingStand.png"),1,new int[]{0,1,2},new int[]{3});
        dispenser = new InventoryType("dispenser",9,new Block[]{Blocks.dispenser,Blocks.dropper}, new ResourceLocation(References.RESOURCEPREFIX + "textures/types/dispenser.png"),0,new int[]{0,1,2,3,4,5,6,7,8},new int[]{0,1,2,3,4,5,6,7,8});
    }

    public static InventoryType fromString(String name) {
        for (InventoryType type : types) {
            if (type.getUnlocalizedName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public static InventoryType fromInteger(int id) {
        if (Block.getBlockById(id) != null) {
            Block block = Block.getBlockById(id);
            for (InventoryType type : types) {
                for (Block block1 : type.getBlocks()) {
                    if (block1 == block) return type;
                }
            }
        } else {
            throw new IllegalArgumentException("Object id does not exist!");
        }
        return null;
    }

}
