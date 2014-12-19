package mc.Mitchellbrine.wip.block;

import cpw.mods.fml.common.registry.GameRegistry;
import mc.Mitchellbrine.wip.WirelessItemPassaging;
import mc.Mitchellbrine.wip.block.conduit.TransportConduit;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;

import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2014.
 */
public class BlockRegistry {

    public static ArrayList<Block> blocks = new ArrayList<Block>();

    public static Block transportConduit;

    public static void init() {

        transportConduit = new TransportConduit();

        for (Block block : blocks) {
            GameRegistry.registerBlock(block,block.getUnlocalizedName().substring(5));
            WirelessItemPassaging.logger.info("Registed block " + block.getUnlocalizedName().substring(5));
            if (block instanceof ITileEntityProvider) {
                GameRegistry.registerTileEntity(block.createTileEntity(null,0).getClass(), References.MODID + ":TE" + block.getUnlocalizedName().substring(5));
                WirelessItemPassaging.logger.info("Registed TE TE" + block.getUnlocalizedName().substring(5));
            }
        }

    }

}
