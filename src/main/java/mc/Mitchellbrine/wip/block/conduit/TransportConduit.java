package mc.Mitchellbrine.wip.block.conduit;

import mc.Mitchellbrine.wip.block.base.WIPTEBlock;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Mitchellbrine on 2014.
 */
public class TransportConduit extends WIPTEBlock {

    public TransportConduit() {
        super(Material.rock);
        this.setBlockName("itemTransportConduit");
        this.setBlockTextureName(References.RESOURCEPREFIX + "transportConduit");
    }

    public TileEntity createTileEntity(World world, int meta) {
        return new TileEntityConduit();
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityConduit();
    }

}
