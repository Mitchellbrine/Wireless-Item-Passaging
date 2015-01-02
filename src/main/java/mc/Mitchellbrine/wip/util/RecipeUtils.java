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

        switch (ConfigUtils.dBlocksRequired) {
            case 1:
                GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XY",'X',Blocks.diamond_block,'Y',Items.nether_star);
                break;
            case 2:
                GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XXY",'X',Blocks.diamond_block,'Y',Items.nether_star);
                break;
            case 3:
                GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XXX"," Y ",'X',Blocks.diamond_block,'Y',Items.nether_star);
                break;
            case 4:
                GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XXX","XY ",'X',Blocks.diamond_block,'Y',Items.nether_star);
                break;
            case 5:
                GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XXX","XYX",'X',Blocks.diamond_block,'Y',Items.nether_star);
                break;
            case 6:
                GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XXX","XYX","X  ",'X',Blocks.diamond_block,'Y',Items.nether_star);
                break;
            case 7:
                GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XXX","XYX","X X",'X',Blocks.diamond_block,'Y',Items.nether_star);
                break;
            case 8:
                GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XXX","XYX","XXX",'X',Blocks.diamond_block,'Y',Items.nether_star);
                break;
            default:
                GameRegistry.addRecipe(new ItemStack(BlockRegistry.netherDiamond),"XXX","XYX","XXX",'X',Blocks.diamond_block,'Y',Items.nether_star);
                break;
        }

        GameRegistry.addRecipe(new ItemStack(ItemRegistry.gps),"LS ","RRR","  R",'L',Blocks.redstone_torch,'S',Items.redstone,'R',new ItemStack(Blocks.wool,1,14));

        if (ConfigUtils.useHardcoreRecipes) {
            GameRegistry.addRecipe(new ItemStack(ItemRegistry.blankModule),"SPS","IHI","SPS",'I',Blocks.iron_block,'P',Items.paper,'S',Items.string,'H',Blocks.hopper);
        } else {
            GameRegistry.addRecipe(new ItemStack(ItemRegistry.blankModule),"SPS","IHI","SPS",'I',Items.iron_ingot,'P',Items.paper,'S',Items.string,'H',Blocks.hopper);
        }

        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.moduleFilter), ItemRegistry.blankModule, Items.fishing_rod);

    }

}
