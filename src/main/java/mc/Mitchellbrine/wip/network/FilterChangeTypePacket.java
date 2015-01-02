package mc.Mitchellbrine.wip.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraft.server.MinecraftServer;

/**
 * Created by Mitchellbrine on 2014.
 */
public class FilterChangeTypePacket  implements IMessage, IMessageHandler<FilterChangeTypePacket,IMessage> {

    private int x,y,z,type,dimension;

    public FilterChangeTypePacket(){}

    public FilterChangeTypePacket(TileEntityConduit te, int currentType,int dimension) {
        this.x = te.xCoord;
        this.y = te.yCoord;
        this.z = te.zCoord;
        this.type = currentType;
        this.dimension = dimension;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        type = buf.readInt();
        dimension = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(type);
        buf.writeInt(dimension);
    }

    @Override
    public IMessage onMessage(FilterChangeTypePacket message, MessageContext ctx) {
        TileEntityConduit te = (TileEntityConduit) MinecraftServer.getServer().worldServers[message.dimension].getTileEntity(message.x, message.y, message.z);
        if (te != null) {
            if (te.getFilterType() == 0) {
                te.setFilterType(1);
            } else {
                te.setFilterType(0);
            }
        }
        return null;
    }

}
