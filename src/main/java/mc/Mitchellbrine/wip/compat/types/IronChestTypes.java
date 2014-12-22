package mc.Mitchellbrine.wip.compat.types;

import cpw.mods.fml.common.Optional;
import mc.Mitchellbrine.wip.block.conduit.logic.InventoryType;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Mitchellbrine on 2014.
 */
public class IronChestTypes {

    public static InventoryType ironChest;
    public static InventoryType dirtChest;
    public static InventoryType goldChest;
    public static InventoryType diamondChest;

    public static InventoryType copperChest;
    public static InventoryType silverChest;

    @Optional.Method(modid="IronChest")
    public static void registerIronChestCompatibility() {
        ironChest = new InventoryType("ironchest.ironchest",54,new Block[]{cpw.mods.ironchest.IronChest.ironChestBlock,cpw.mods.ironchest.IronChest.ironChestBlock,cpw.mods.ironchest.IronChest.ironChestBlock,cpw.mods.ironchest.IronChest.ironChestBlock},new int[]{0,3,4,7},new ResourceLocation(References.RESOURCEPREFIX + "textures/types/compat/ironChest.png"),0,new int[54],new int[54]);
        goldChest = new InventoryType("ironchest.goldchest",81,new Block[]{cpw.mods.ironchest.IronChest.ironChestBlock},new int[]{1},new ResourceLocation(References.RESOURCEPREFIX + "textures/types/compat/goldChest.png"),0,new int[81],new int[81]);
        diamondChest = new InventoryType("ironchest.diamondchest",108,new Block[]{cpw.mods.ironchest.IronChest.ironChestBlock,cpw.mods.ironchest.IronChest.ironChestBlock,cpw.mods.ironchest.IronChest.ironChestBlock},new int[]{2,5,6},new ResourceLocation(References.RESOURCEPREFIX + "textures/types/compat/diamondChest.png"),0,new int[108],new int[108]);

    }

}
