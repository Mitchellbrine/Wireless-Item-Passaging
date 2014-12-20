package mc.Mitchellbrine.wip.item;

import mc.Mitchellbrine.wip.item.base.WIPItem;
import net.minecraft.item.ItemStack;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ItemShiny extends WIPItem {

    public ItemShiny() {
        super();
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }
}
