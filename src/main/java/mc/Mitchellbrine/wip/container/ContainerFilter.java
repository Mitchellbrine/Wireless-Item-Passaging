package mc.Mitchellbrine.wip.container;

import mc.Mitchellbrine.wip.container.slot.FakeSlot;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ContainerFilter extends Container {

    private TileEntityConduit conduit;

    public ContainerFilter(TileEntityConduit conduit, EntityPlayer player) {
        this.conduit = conduit;
        bindInventoryPlayer(player.inventory);
        addFakeSlots(conduit);
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    private void addFakeSlots(TileEntityConduit conduit) {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new FakeSlot(conduit, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }
    }

    private void bindInventoryPlayer(InventoryPlayer inv) {
        for (int i = 0; i < 3;i++) {
            for (int j = 0; j < 9;j++) {
                addSlotToContainer(new Slot(inv,j+i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9;i++) {
            addSlotToContainer(new Slot(inv, i,8 + i * 18,142));
        }
    }

    @Override
    public boolean canDragIntoSlot(Slot slot) {
        return false;
    }

    @Override
    public ItemStack slotClick(int s, int mouse, int modifier, EntityPlayer player) {
        if (s > 0) {
            Slot slot = (Slot)this.inventorySlots.get(s);
            if (slot instanceof FakeSlot)
            return this.fakeSlotClick(slot,mouse,modifier,player);
        }
        return super.slotClick(s,mouse,modifier,player);
    }

    private ItemStack fakeSlotClick(Slot slot, int mouse, int modifier, EntityPlayer player) {
        ItemStack stackHeld = player.inventory.getItemStack();

        if (mouse == 0 || mouse == 1) {
            ItemStack fakeStack = slot.getStack();

            if (fakeStack == null) {
                if (stackHeld != null && slot.isItemValid(stackHeld)) {
                    ItemStack newStack = stackHeld.copy();
                    newStack.stackSize = 1;
                    slot.putStack(newStack);
                }
            } else {
                slot.putStack(null);
            }

        }

        return stackHeld;
    }

    public ItemStack transferStackInSlot(EntityPlayer p, int s) {
        ItemStack stack = null;
        Slot slot = (Slot)this.inventorySlots.get(s);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            stack = itemStackInSlot.copy();

            if (slot instanceof FakeSlot)
                return stack;

            if (!this.mergeItemStack(itemStackInSlot,0,this.conduit.getSizeInventory(),false))
                return null;

            if (itemStackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
            return stack;
    }

}
