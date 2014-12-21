package mc.Mitchellbrine.wip.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ItemHelper {

    public static void dropItemInWorld(ItemStack stack,World world, int x, int y, int z) {
        ItemStack itemstack = stack;

        if (itemstack != null)
        {
            float f = new Random().nextFloat() * 0.8F + 0.1F;
            float f1 = new Random().nextFloat() * 0.8F + 0.1F;
            EntityItem entityitem;

            for (float f2 = new Random().nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
            {
                int j1 = new Random().nextInt(21) + 10;

                if (j1 > itemstack.stackSize)
                {
                    j1 = itemstack.stackSize;
                }

                itemstack.stackSize -= j1;
                entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                float f3 = 0.05F;
                entityitem.motionX = (double) ((float) new Random().nextGaussian() * f3);
                entityitem.motionY = (double) ((float) new Random().nextGaussian() * f3 + 0.2F);
                entityitem.motionZ = (double) ((float) new Random().nextGaussian() * f3);

                if (itemstack.hasTagCompound())
                {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                }
            }
        }
    }

    public static int getFirstEmptySlot(IInventory inventory,ItemStack stack) {
        for (int i = 0; i < inventory.getSizeInventory();i++) {
            if (inventory.getStackInSlot(i) == null) return i;
            else
                if (inventory.getStackInSlot(i).getItem() == stack.getItem() && inventory.getStackInSlot(i).getItemDamage() == stack.getItemDamage() && inventory.getStackInSlot(i).getTagCompound() == stack.getTagCompound() && inventory.getStackInSlot(i).stackSize < inventory.getStackInSlot(i).getItem().getItemStackLimit(null) && inventory.getStackInSlot(i).stackSize < inventory.getInventoryStackLimit()) return i;
        }
        return -1;
    }

}
