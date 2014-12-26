package mc.Mitchellbrine.wip.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import mc.Mitchellbrine.wip.block.conduit.logic.InventoryTypes;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2014.
 */
public class FilterChangeTypePacket  implements IMessage, IMessageHandler<FilterChangeTypePacket,IMessage> {

    private int x,y,z,type;

    public FilterChangeTypePacket(){}

    public FilterChangeTypePacket(TileEntityConduit te, int currentType) {
        this.x = te.xCoord;
        this.y = te.yCoord;
        this.z = te.zCoord;
        this.type = currentType;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        type = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(type);
    }

    @Override
    public IMessage onMessage(FilterChangeTypePacket message, MessageContext ctx) {
        TileEntityConduit te = (TileEntityConduit) Minecraft.getMinecraft().theWorld.getTileEntity(message.x,message.y,message.z);
        if (te != null) {
            switch(message.type) {
                case 0:
                    te.setFilterType(1);
                    break;
                case 1:
                    te.setFilterType(0);
                    break;
            }
            System.out.println(te.getFilterType());
        }
        return null;
    }

}
