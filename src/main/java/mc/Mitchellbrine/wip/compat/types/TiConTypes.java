package mc.Mitchellbrine.wip.compat.types;

import cpw.mods.fml.common.Optional;
import mc.Mitchellbrine.wip.block.conduit.logic.InventoryType;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Mitchellbrine on 2014.
 */
public class TiConTypes {

    public static InventoryType craftingStation;
    public static InventoryType slabFurnace;
    public static InventoryType smeltery;

    @Optional.Method(modid="TConstruct")
    public static void registerTiConCompatibility() {
        craftingStation = new InventoryType("tiCon.craftingStation",9,new Block[]{tconstruct.tools.TinkerTools.craftingStationWood, tconstruct.tools.TinkerTools.craftingSlabWood},new ResourceLocation(References.RESOURCEPREFIX + "textures/types/compat/craftingStation.png"),1,new int[]{0,1,2,3,4,5,6,7,8},new int[]{0,1,2,3,4,5,6,7,8});
        slabFurnace = new InventoryType("tiCon.slabFurnace",3,new Block[]{tconstruct.tools.TinkerTools.furnaceSlab},new ResourceLocation(References.RESOURCEPREFIX + "textures/types/furnace.png"),1,new int[]{2},new int[]{0,1});
        smeltery = new InventoryType("tiCon.smeltery",9,new Block[]{tconstruct.smeltery.TinkerSmeltery.smeltery},new int[]{0},new ResourceLocation(References.RESOURCEPREFIX + "textures/types/compat/smeltery.png"),0,new int[]{0,1,2,3,4,5,6,7,8,9},new int[]{0,1,2,3,4,5,6,7,8,9});
    }

}
