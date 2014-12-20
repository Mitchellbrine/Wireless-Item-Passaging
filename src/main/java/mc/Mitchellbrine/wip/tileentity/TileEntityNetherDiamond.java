package mc.Mitchellbrine.wip.tileentity;

import mc.Mitchellbrine.wip.item.ItemRegistry;
import mc.Mitchellbrine.wip.util.ItemHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Mitchellbrine on 2014.
 */
public class TileEntityNetherDiamond extends TileEntity {

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (checkRow1() && checkRow2() && checkRow3()) {
            craftItem();
        }
    }

    private boolean checkRow1() {
        int y = yCoord + 1;
        if (worldObj.getBlock(xCoord - 1,y,zCoord-1) != Blocks.obsidian) return false;
        if (worldObj.getBlock(xCoord + 1,y,zCoord + 1) != Blocks.obsidian) return false;
        if (worldObj.getBlock(xCoord + 1, y, zCoord - 1) != Blocks.obsidian) return false;
        if (worldObj.getBlock(xCoord - 1, y, zCoord + 1) != Blocks.obsidian) return false;

        if (worldObj.getBlock(xCoord + 1,y,zCoord) != Blocks.nether_brick) return false;
        if (worldObj.getBlock(xCoord - 1,y,zCoord) != Blocks.nether_brick) return false;
        if (worldObj.getBlock(xCoord,y,zCoord + 1) != Blocks.nether_brick) return false;
        if (worldObj.getBlock(xCoord,y,zCoord - 1) != Blocks.nether_brick) return false;

        if (worldObj.getBlock(xCoord,y,zCoord) != Blocks.obsidian) return false;

        return true;
    }

    private boolean checkRow2() {
        for (int x = xCoord - 1; x < xCoord + 2;x++) {
            for (int z = zCoord - 1; z < zCoord + 2;z++) {
                if (x == xCoord && z == zCoord) continue;
                if (worldObj.getBlock(x,yCoord,z) != Blocks.end_stone) return false;
            }
        }
        return true;
    }

    private boolean checkRow3() {
        int y = yCoord - 1;
        if (worldObj.getBlock(xCoord - 1,y,zCoord-1) != Blocks.obsidian) return false;
        if (worldObj.getBlock(xCoord + 1,y,zCoord + 1) != Blocks.obsidian) return false;
        if (worldObj.getBlock(xCoord + 1, y, zCoord - 1) != Blocks.obsidian) return false;
        if (worldObj.getBlock(xCoord - 1, y, zCoord + 1) != Blocks.obsidian) return false;

        if (worldObj.getBlock(xCoord + 1,y,zCoord) != Blocks.nether_brick) return false;
        if (worldObj.getBlock(xCoord - 1,y,zCoord) != Blocks.nether_brick) return false;
        if (worldObj.getBlock(xCoord,y,zCoord + 1) != Blocks.nether_brick) return false;
        if (worldObj.getBlock(xCoord,y,zCoord - 1) != Blocks.nether_brick) return false;

        if (worldObj.getBlock(xCoord,y,zCoord) != Blocks.obsidian) return false;

        return true;
    }

    private void craftItem() {
        for (int x = xCoord - 1; x < xCoord + 2;x++) {
            for (int y = yCoord - 1; y < yCoord + 2;y++) {
                for (int z = zCoord - 1; z < zCoord + 2;z++) {
                    worldObj.func_147480_a(x, y, z, false);
                }
            }
        }
        if (!worldObj.isRemote) {
            ItemHelper.dropItemInWorld(new ItemStack(ItemRegistry.wirelessTransportCore), worldObj, xCoord, yCoord, zCoord);
        }
    }

}
