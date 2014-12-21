package mc.Mitchellbrine.wip.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import mc.Mitchellbrine.wip.block.conduit.logic.InventoryType;
import mc.Mitchellbrine.wip.block.conduit.logic.InventoryTypes;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2014.
 */
public class NBTUpdatePacket implements IMessage, IMessageHandler<NBTUpdatePacket,IMessage>{

    private Vec3 takeFrom;
    private InventoryType type;
    private int x, y, z;
    private ItemStack[] items;

    public NBTUpdatePacket(){}

    public NBTUpdatePacket(int x, int y, int z, Vec3 takeFrom,InventoryType type,ItemStack[] items) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.takeFrom = takeFrom;
        this.type = type;
        this.items = items;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
            x = buf.readInt();
            y = buf.readInt();
            z = buf.readInt();
            if (buf.readBoolean()) {
                int tfX, tfY, tfZ;
                tfX = buf.readInt();
                tfY = buf.readInt();
                tfZ = buf.readInt();
                takeFrom = Vec3.createVectorHelper(tfX, tfY, tfZ);
            }
            if (buf.readBoolean()) {
                type = InventoryTypes.fromInteger(buf.readInt());
            }
            if (buf.readBoolean()) {
                ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
                for (int i = 0; i < type.getSlotAmount();i++) {
                    itemStacks.add(ByteBufUtils.readItemStack(buf));
                }
                items = new ItemStack[type.getSlotAmount()];
                items = itemStacks.toArray(items);
            }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        if (takeFrom != null) {
            buf.writeBoolean(true);
            buf.writeInt((int) takeFrom.xCoord);
            buf.writeInt((int) takeFrom.yCoord);
            buf.writeInt((int) takeFrom.zCoord);
        } else {
          buf.writeBoolean(false);
        }
        if (type != null) {
            buf.writeBoolean(true);
            buf.writeInt(Block.getIdFromBlock(type.getBlocks()[0]));
        } else {
            buf.writeBoolean(false);
        }
        if (items != null) {
            buf.writeBoolean(true);
            for (ItemStack stack : items) {
                ByteBufUtils.writeItemStack(buf,stack);
            }
        } else {
            buf.writeBoolean(false);
        }
    }

    @Override
    public IMessage onMessage(NBTUpdatePacket message, MessageContext ctx) {
        TileEntityConduit te = (TileEntityConduit) Minecraft.getMinecraft().theWorld.getTileEntity(message.x,message.y,message.z);
        te.setTakeFrom(message.takeFrom);
        te.setType(message.type);
        te.setInventory(message.items);
        return null;
    }
}
