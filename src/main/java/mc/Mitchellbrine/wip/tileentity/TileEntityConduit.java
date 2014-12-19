package mc.Mitchellbrine.wip.tileentity;

import mc.Mitchellbrine.wip.block.conduit.logic.InventoryType;
import mc.Mitchellbrine.wip.block.conduit.logic.InventoryTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.Vec3;

/**
 * Created by Mitchellbrine on 2014.
 */
public class TileEntityConduit extends TileEntity{

    private Vec3 takeFrom;
    private InventoryType type;
    private ItemStack[] items;

    public TileEntityConduit() {}

    public void updateEntity() {
        if (takeFrom != null) {
            Block takeFromBlock = worldObj.getBlock((int)takeFrom.xCoord,(int)takeFrom.yCoord,(int)takeFrom.zCoord);
            for (InventoryType type : InventoryTypes.types) {
                for (Block block : type.getBlocks()) {
                    if (block == takeFromBlock) {
                        if (this.type == type) break;
                        System.out.println("Now using the inventory type " + type.getUnlocalizedName() + " with " + type.getSlotAmount() + " slots!");
                        this.type = type;
                        if (items != null && items.length < type.getSlotAmount()) {
                            for (int i = items.length - 1;i < type.getSlotAmount();i++) {
                                if (items[i] != null) {
                                    // Drop item on ground
                                }
                            }
                        }
                        items = new ItemStack[type.getSlotAmount()];
                        break;
                    }
                }
            }

        }

    }

    public Vec3 getPullLoc() {
        return this.takeFrom;
    }

    public void setGPSLoc(EntityPlayer player, Vec3 vector) {
        Vec3 location = Vec3.createVectorHelper(xCoord,yCoord,zCoord);
        if (vector.distanceTo(location) > 50) {
            if (player.worldObj.isRemote) {
                player.addChatComponentMessage(new ChatComponentTranslation("conduit.coords.far", (int)vector.distanceTo(location)));
            }
            return;
        } else if (vector.distanceTo(location) == 0.0) {
            if (player.worldObj.isRemote) {
                player.addChatComponentMessage(new ChatComponentTranslation("conduit.coords.exact"));
            }
            return;
        }
        takeFrom = vector;
    }


}
