package mc.Mitchellbrine.wip.util;

import cpw.mods.fml.common.registry.GameRegistry;
import mc.Mitchellbrine.wip.block.BlockRegistry;
import mc.Mitchellbrine.wip.item.ItemRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Mitchellbrine on 2014.
 */
public class RecipeUtils {

    public static void init() {
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.transportConduit,4),"IiI","iCi","IiI",'I', Blocks.iron_block,'i',Blocks.iron_bars,'C', ItemRegistry.wirelessTransportCore);

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XXX","XYX","XXX",'X',Blocks.diamond_block,'Y',Items.nether_star);

        GameRegistry.addRecipe(new ItemStack(ItemRegistry.gps),"LS ","RRR","  R",'L',Blocks.redstone_torch,'S',Items.redstone,'R',new ItemStack(Blocks.wool,1,14));

    }

}
