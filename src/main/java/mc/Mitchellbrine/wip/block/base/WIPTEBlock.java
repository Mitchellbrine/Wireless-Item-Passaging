package mc.Mitchellbrine.wip.block.base;

import mc.Mitchellbrine.wip.block.BlockRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * Created by Mitchellbrine on 2014.
 */
public abstract class WIPTEBlock extends BlockContainer {

    public WIPTEBlock(Material material) {
        super(material);
        BlockRegistry.blocks.add(this);
    }

}
