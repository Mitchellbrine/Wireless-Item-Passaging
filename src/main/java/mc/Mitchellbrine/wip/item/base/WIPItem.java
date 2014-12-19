package mc.Mitchellbrine.wip.item.base;

import mc.Mitchellbrine.wip.item.ItemRegistry;
import net.minecraft.item.Item;

/**
 * Created by Mitchellbrine on 2014.
 */
public class WIPItem extends Item {

    public WIPItem() {
        super();
        ItemRegistry.items.add(this);
    }

}
