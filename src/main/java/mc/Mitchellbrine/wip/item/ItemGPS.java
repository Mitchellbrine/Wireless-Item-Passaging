package mc.Mitchellbrine.wip.item;

import mc.Mitchellbrine.wip.item.base.WIPItem;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ItemGPS extends WIPItem {

    public ItemGPS() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName("wipGPS");
        this.setTextureName(References.RESOURCEPREFIX + "gps");
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int meta, boolean par5) {
        if (!stack.hasTagCompound()) {
            stack.stackTagCompound = new NBTTagCompound();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List lore, boolean par4) {
        if (stack.hasTagCompound()) {
            String coords = "Coords: ";
            if (stack.stackTagCompound.hasKey("x")) {
                coords = coords + stack.getTagCompound().getInteger("x") + ", ";
            } else {
                coords = coords + "-, ";
            }
            if (stack.stackTagCompound.hasKey("y")) {
                coords = coords + stack.getTagCompound().getInteger("y") + ", ";
            } else {
                coords = coords + "-, ";
            }
            if (stack.stackTagCompound.hasKey("z")) {
                coords = coords + stack.getTagCompound().getInteger("z");
            } else {
                coords = coords + "-";
            }
            lore.add(coords);
        }
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (world.getBlock(x,y,z) != Blocks.air) {
            stack.getTagCompound().setInteger("x", x);
            stack.getTagCompound().setInteger("y", y);
            stack.getTagCompound().setInteger("z", z);
            return true;
        }
        return false;
    }


}
