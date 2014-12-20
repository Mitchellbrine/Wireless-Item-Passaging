package mc.Mitchellbrine.wip.block;

import mc.Mitchellbrine.wip.block.base.WIPTEBlock;
import mc.Mitchellbrine.wip.tileentity.TileEntityNetherDiamond;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Mitchellbrine on 2014.
 */
public class BlockNetherDiamond extends WIPTEBlock {

    public BlockNetherDiamond() {
        super(Material.iron);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setBlockName("blockNetherDiamond");
        this.setBlockTextureName(References.RESOURCEPREFIX + "netherdiamond");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityNetherDiamond();
    }

    @Override
    public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityNetherDiamond();
    }
}
