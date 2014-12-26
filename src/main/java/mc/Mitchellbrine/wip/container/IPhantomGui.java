package mc.Mitchellbrine.wip.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by Mitchellbrine on 2014.
 */
public interface IPhantomGui extends IInventory{

    ItemStack getFakeStack(int i);

    ItemStack decrFakeStack(int s, int a);

    ItemStack getFakeStackSlotOnClosing(int s);

    void setFakeSlotContents(int s, ItemStack i);

}
