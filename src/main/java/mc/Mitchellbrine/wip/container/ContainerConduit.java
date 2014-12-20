package mc.Mitchellbrine.wip.container;

import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ContainerConduit extends Container {

    public ContainerConduit(EntityPlayer player) {
        bindInventoryPlayer(player.inventory);
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
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
}
