package mc.Mitchellbrine.wip.item;

import mc.Mitchellbrine.wip.block.BlockRegistry;
import mc.Mitchellbrine.wip.item.base.WIPItem;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ItemGPS extends WIPItem {

    public ItemGPS() {
        super();
        this.setMaxStackSize(1);
        this.setFull3D();
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
            if (player.isSneaking()) {
                stack.getTagCompound().setInteger("x", x);
                stack.getTagCompound().setInteger("y", y);
                stack.getTagCompound().setInteger("z", z);
                if (world.isRemote)
                player.addChatComponentMessage(new ChatComponentTranslation("gps.coords.set", x, y, z));
            } else {
                if (world.getBlock(x,y,z) == BlockRegistry.transportConduit) {

                    if (world.getTileEntity(x,y,z) instanceof TileEntityConduit) {
                        TileEntityConduit te = (TileEntityConduit) world.getTileEntity(x,y,z);
                        if (stack.getTagCompound().hasKey("x") && stack.getTagCompound().hasKey("y") && stack.getTagCompound().hasKey("z")) {
                            te.setGPSLoc(player, Vec3.createVectorHelper(stack.getTagCompound().getInteger("x"), stack.getTagCompound().getInteger("y"), stack.getTagCompound().getInteger("z")));
                        }
                    }

                } else {
                    stack.getTagCompound().setInteger("x", x);
                    stack.getTagCompound().setInteger("y", y);
                    stack.getTagCompound().setInteger("z", z);
                    if (world.isRemote)
                    player.addChatComponentMessage(new ChatComponentTranslation("gps.coords.set",x,y,z));
                }
            }
            return true;
        }
        return false;
    }


}
