package mc.Mitchellbrine.wip.container.slot;

import mc.Mitchellbrine.wip.container.IPhantomGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Mitchellbrine on 2014.
 */
public class FakeSlot extends Slot{

    private IPhantomGui gui;

    public FakeSlot(IPhantomGui gui, int id, int x, int y) {
        super(gui, id, x, y);
        this.gui = gui;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return false;
    }

    public ItemStack getStack() {
        return this.gui.getFakeStack(this.getSlotIndex());
    }

    public void putStack(ItemStack stack) {
        this.gui.setFakeSlotContents(this.getSlotIndex(),stack);
        this.onSlotChanged();
    }

    public int getSlotStackLimit() {
        return 1;
    }

}
