package mc.Mitchellbrine.wip.block.conduit;

import mc.Mitchellbrine.wip.WirelessItemPassaging;
import mc.Mitchellbrine.wip.block.base.WIPTEBlock;
import mc.Mitchellbrine.wip.client.gui.GuiHandler;
import mc.Mitchellbrine.wip.item.ItemRegistry;
import mc.Mitchellbrine.wip.network.NBTUpdatePacket;
import mc.Mitchellbrine.wip.network.PacketHandler;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import mc.Mitchellbrine.wip.util.ItemHelper;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * Created by Mitchellbrine on 2014.
 */
public class TransportConduit extends WIPTEBlock {

    public TransportConduit() {
        super(Material.rock);
        this.setHardness(5.0F);
        this.setResistance(20.0F);
        this.setBlockName("itemTransportConduit");
        this.setBlockTextureName(References.RESOURCEPREFIX + "transportConduit");
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!player.isSneaking()) {
            if (player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() != ItemRegistry.gps || player.getCurrentEquippedItem().getItem() != ItemRegistry.moduleFilter) {

                if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == ItemRegistry.moduleFilter) {
                    TileEntityConduit te = (TileEntityConduit) world.getTileEntity(x,y,z);
                    if (te != null) {
                        if (!te.isFilterInstalled()) {
                            te.setFilterInstalled(true);
                            if (!player.capabilities.isCreativeMode) {
                                player.setCurrentItemOrArmor(0, null);
                            }
                            if (world.isRemote) {
                                player.addChatComponentMessage(new ChatComponentTranslation("conduit.module.installed.filter").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(true)));
                            }
                            return true;
                        }
                    }
                }

                if (!world.isRemote) {
                    if (player instanceof EntityPlayerMP) {
                        TileEntityConduit te = (TileEntityConduit) world.getTileEntity(x,y,z);
                        if (te != null) {
                            PacketHandler.INSTANCE.sendTo(new NBTUpdatePacket(x, y, z, te.getPullLoc(), te.getInventoryType(),te.getInventory()), (EntityPlayerMP) player);
                        }
                    }
                }
                player.openGui(WirelessItemPassaging.instance, GuiHandler.IDS.Conduit, world, x, y, z);
                return true;
            }
        } else {
            if (player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() != ItemRegistry.gps || player.getCurrentEquippedItem().getItem() != ItemRegistry.moduleFilter) {
                /*if (!world.isRemote) {
                } */




                TileEntityConduit te = (TileEntityConduit) world.getTileEntity(x,y,z);
                if (te != null) {
                    if (te.isFilterInstalled()) {
                        player.openGui(WirelessItemPassaging.instance, GuiHandler.IDS.ItemFilter, world, x, y, z);
                    } else {
                        if (!world.isRemote) {
                            player.addChatComponentMessage(new ChatComponentTranslation("conduit.module.nonExist").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(true)));
                        }
                    }
                }
            }
        }
        return false;
    }

    public TileEntity createTileEntity(World world, int meta) {
        return new TileEntityConduit();
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
            TileEntityConduit te = (TileEntityConduit) world.getTileEntity(x, y, z);

            if (te != null)
            {
                for (int i1 = 0; i1 < te.getSizeInventory(); ++i1)
                {
                    ItemHelper.dropItemInWorld(te.getStackInSlot(i1),world,x,y,z);
                }
                world.func_147453_f(x, y, z, block);
            }

            super.breakBlock(world, x, y, z, block, meta);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityConduit();
    }

}
