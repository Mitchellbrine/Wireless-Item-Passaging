package mc.Mitchellbrine.wip.tileentity;

import mc.Mitchellbrine.wip.WirelessItemPassaging;
import mc.Mitchellbrine.wip.block.BlockRegistry;
import mc.Mitchellbrine.wip.block.conduit.logic.InventoryType;
import mc.Mitchellbrine.wip.block.conduit.logic.InventoryTypes;
import mc.Mitchellbrine.wip.network.NBTUpdatePacket;
import mc.Mitchellbrine.wip.network.PacketHandler;
import mc.Mitchellbrine.wip.util.ConfigUtils;
import mc.Mitchellbrine.wip.util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;


/**
 * Created by Mitchellbrine on 2014.
 */
public class TileEntityConduit extends TileEntity implements IInventory, ISidedInventory{

    private Vec3 takeFrom;
    private InventoryType type;
    private ItemStack[] items;

    public TileEntityConduit() {}

    public void updateEntity() {
        super.updateEntity();
        if (worldObj.getWorldTime() == 0) {
            sendPacketToClient();
        }
        calculateCurrentInventory();
        if (worldObj.getWorldTime() % ConfigUtils.delay == 0) {
            takeSlot();
        }
        outputToChest();
    }

    private void calculateCurrentInventory() {
        if (takeFrom != null) {
            Vec3 location = Vec3.createVectorHelper(xCoord, yCoord, zCoord);
            if (takeFrom.distanceTo(location) > ConfigUtils.range) return;
            Block takeFromBlock = worldObj.getBlock((int) takeFrom.xCoord, (int) takeFrom.yCoord, (int) takeFrom.zCoord);
            if (takeFromBlock != BlockRegistry.transportConduit) {
                for (InventoryType type : InventoryTypes.types) {
                    for (Block block : type.getBlocks()) {
                        if (block == takeFromBlock) {
                            if (this.type == type) break;
                            System.out.println("Now using the inventory type " + type.getUnlocalizedName() + " with " + type.getSlotAmount() + " slots!");
                            this.type = type;
                            if (items != null) {
                                for (ItemStack item : items) {
                                    if (item != null) {
                                        ItemHelper.dropItemInWorld(item, worldObj, xCoord, yCoord, zCoord);
                                    }
                                }
                            }
                            items = new ItemStack[type.getSlotAmount()];
                            break;
                        }
                    }
                }
            } else {
                TileEntityConduit te = (TileEntityConduit) worldObj.getTileEntity((int) takeFrom.xCoord, (int) takeFrom.yCoord, (int) takeFrom.zCoord);
                if (te != null) {
                    if (te.getInventoryType() != null) {
                        if (this.type == te.getInventoryType()) return;
                        this.type = te.getInventoryType();
                        System.out.println("Now using the inventory type " + type.getUnlocalizedName() + " with " + type.getSlotAmount() + " slots!");
                        if (items != null) {
                            for (ItemStack item : items) {
                                if (item != null) {
                                    ItemHelper.dropItemInWorld(item, worldObj, xCoord, yCoord, zCoord);
                                }
                            }
                        }
                        items = new ItemStack[type.getSlotAmount()];
                    }
                }
            }
        }
        }

    private void sendPacketToClient() {
        if (takeFrom != null && type != null) {
            if (!worldObj.isRemote) {
                    if (worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 500) != null) {
                        EntityPlayer player = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 500);
                        if (player instanceof EntityPlayerMP) {
                            PacketHandler.INSTANCE.sendTo(new NBTUpdatePacket(xCoord, yCoord, zCoord, takeFrom, type,items), (EntityPlayerMP) player);
                        }
                    }
            }
        }
    }


    private void takeSlot() {
        if (takeFrom != null) {
            Vec3 location = Vec3.createVectorHelper(xCoord,yCoord,zCoord);
            if (takeFrom.distanceTo(location) > ConfigUtils.range) return;
            int x = (int)takeFrom.xCoord;
            int y = (int)takeFrom.yCoord;
            int z = (int)takeFrom.zCoord;
            if (this.getInventoryType() == InventoryTypes.furnace) {
                IInventory te = (IInventory)worldObj.getTileEntity(x,y,z);
                if (te != null) {
                    if (te.getStackInSlot(2) != null) {
                        if (this.getStackInSlot(2) != null) {
                            if (te.getStackInSlot(2).getItem() == this.getStackInSlot(2).getItem() && te.getStackInSlot(2).getItemDamage() == this.getStackInSlot(2).getItemDamage()) {
                                ItemStack stack = te.getStackInSlot(2);
                                this.setInventorySlotContents(2, new ItemStack(this.getStackInSlot(2).getItem(),stack.stackSize + this.getStackInSlot(2).stackSize,this.getStackInSlot(2).getItemDamage()));
                                te.setInventorySlotContents(2, null);
                                sendPacketToClient();
                            }
                        } else {
                            this.setInventorySlotContents(2, te.getStackInSlot(2));
                            te.setInventorySlotContents(2, null);
                            sendPacketToClient();
                        }
                    }
                }
            } else if (this.getInventoryType() == InventoryTypes.chest) {
                IInventory te = (IInventory)worldObj.getTileEntity(x,y,z);
                if (te != null) {
                    if (items != null) {
                        for (int i = 0; i < te.getSizeInventory(); i++) {
                                if (te.getStackInSlot(i) != null) {
                                    if (te.getStackInSlot(i).stackSize > 1) {
                                        for (int ii = 0; ii < te.getStackInSlot(i).stackSize; ii++) {
                                            int getSlot = ItemHelper.getFirstEmptySlot(this, te.getStackInSlot(i));
                                            if (getSlot == -1) continue;
                                            if (this.getStackInSlot(getSlot) != null && te.getStackInSlot(i).getItem() == this.getStackInSlot(getSlot).getItem() && te.getStackInSlot(i).getItemDamage() == this.getStackInSlot(getSlot).getItemDamage() && te.getStackInSlot(i).getTagCompound() == this.getStackInSlot(getSlot).getTagCompound() && this.getStackInSlot(getSlot).stackSize < this.getStackInSlot(getSlot).getItem().getItemStackLimit(null) && this.getStackInSlot(getSlot).stackSize < this.getInventoryStackLimit()) {
                                                ItemStack stack = new ItemStack(te.getStackInSlot(i).getItem(), this.getStackInSlot(getSlot).stackSize + 1, te.getStackInSlot(i).getItemDamage());
                                                stack.setTagCompound(te.getStackInSlot(i).getTagCompound());
                                                this.setInventorySlotContents(getSlot, stack);
                                                te.decrStackSize(i, 1);
                                                sendPacketToClient();
                                            } else {
                                                ItemStack stack = new ItemStack(te.getStackInSlot(i).getItem(), 1, te.getStackInSlot(i).getItemDamage());
                                                stack.setTagCompound(te.getStackInSlot(i).getTagCompound());
                                                this.setInventorySlotContents(getSlot, stack);
                                                te.decrStackSize(i, 1);
                                                sendPacketToClient();
                                            }
                                        }
                                    } else {
                                        int getSlot = ItemHelper.getFirstEmptySlot(this, te.getStackInSlot(i));
                                        if (getSlot == -1) return;
                                        if (this.getStackInSlot(getSlot) != null && te.getStackInSlot(i).getItem() == this.getStackInSlot(getSlot).getItem() && te.getStackInSlot(i).getItemDamage() == this.getStackInSlot(getSlot).getItemDamage() && te.getStackInSlot(i).getTagCompound() == this.getStackInSlot(getSlot).getTagCompound() && this.getStackInSlot(getSlot).stackSize < this.getStackInSlot(getSlot).getItem().getItemStackLimit(null) && this.getStackInSlot(getSlot).stackSize < this.getInventoryStackLimit()) {
                                            ItemStack stack = new ItemStack(te.getStackInSlot(i).getItem(), this.getStackInSlot(getSlot).stackSize + 1, te.getStackInSlot(i).getItemDamage());
                                            stack.setTagCompound(te.getStackInSlot(i).getTagCompound());
                                            this.setInventorySlotContents(getSlot, stack);
                                            te.decrStackSize(i, 1);
                                            sendPacketToClient();

                                        } else {
                                            ItemStack stack = new ItemStack(te.getStackInSlot(i).getItem(), 1, te.getStackInSlot(i).getItemDamage());
                                            stack.setTagCompound(te.getStackInSlot(i).getTagCompound());
                                            this.setInventorySlotContents(getSlot, stack);
                                            te.decrStackSize(i, 1);
                                            sendPacketToClient();
                                        }
                                    }
                                }
                        }
                    }
                }
            } else if (this.getInventoryType() == InventoryTypes.hopper) {
                IInventory te = (IInventory)worldObj.getTileEntity(x,y,z);
                if (te != null) {
                    if (items != null) {
                        for (int i = 0; i < te.getSizeInventory(); i++) {
                            if (te.getStackInSlot(i) != null) {
                                if (this.getStackInSlot(i) != null) {
                                    if (te.getStackInSlot(i).getItem() == this.getStackInSlot(i).getItem() && te.getStackInSlot(i).getItemDamage() == this.getStackInSlot(i).getItemDamage()) {
                                        ItemStack stack = te.getStackInSlot(i);
                                        this.setInventorySlotContents(i, new ItemStack(this.getStackInSlot(i).getItem(), stack.stackSize + this.getStackInSlot(i).stackSize, this.getStackInSlot(i).getItemDamage()));
                                        te.setInventorySlotContents(i, null);
                                        sendPacketToClient();
                                    }
                                } else {
                                    this.setInventorySlotContents(i, te.getStackInSlot(i));
                                    te.setInventorySlotContents(i, null);
                                    sendPacketToClient();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void outputToChest() {
        TileEntity tileEntity = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
        if (tileEntity != null && tileEntity instanceof IInventory) {
            IInventory te = (IInventory) tileEntity;
            if (items != null) {
                if (items.length <= te.getSizeInventory()) {
                    for (int i = 0; i < this.items.length; i++) {
                        if (this.items[i] != null) {
                            if (ItemHelper.getFirstEmptySlot(te,this.items[i]) != -1) {
                                if (te.getStackInSlot(ItemHelper.getFirstEmptySlot(te, this.items[i])) == null) {
                                    te.setInventorySlotContents(ItemHelper.getFirstEmptySlot(te, this.items[i]), this.items[i]);
                                    this.setInventorySlotContents(i, null);
                                    sendPacketToClient();
                                } else {
                                    ItemStack stack = this.items[i];
                                    te.setInventorySlotContents(ItemHelper.getFirstEmptySlot(te, this.items[i]), new ItemStack(te.getStackInSlot(ItemHelper.getFirstEmptySlot(te,this.items[i])).getItem(), te.getStackInSlot(ItemHelper.getFirstEmptySlot(te,this.items[i])).stackSize + stack.stackSize, te.getStackInSlot(ItemHelper.getFirstEmptySlot(te,this.items[i])).getItemDamage()));
                                    this.setInventorySlotContents(i, null);
                                    sendPacketToClient();
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < te.getSizeInventory(); i++) {
                        if (this.items[i] != null) {
                            if (te.getStackInSlot(i) == null) {
                                te.setInventorySlotContents(i, this.items[i]);
                                this.setInventorySlotContents(i, null);
                                sendPacketToClient();
                            } else {
                                if (te.getStackInSlot(i).getItem() == this.items[i].getItem() && te.getStackInSlot(i).getItemDamage() == this.items[i].getItemDamage() && te.getStackInSlot(i).getTagCompound() == this.items[i].getTagCompound()) {
                                    ItemStack stack = this.items[i];
                                    te.setInventorySlotContents(i, new ItemStack(te.getStackInSlot(i).getItem(), te.getStackInSlot(i).stackSize + stack.stackSize, te.getStackInSlot(i).getItemDamage()));
                                    this.setInventorySlotContents(i, null);
                                    sendPacketToClient();
                                }
                            }
                        }
                    }
                }
            }
    }
    }

    public Vec3 getPullLoc() {
        return this.takeFrom;
    }

    public void setInventory(ItemStack[] stacks) {
        this.items = stacks;
    }

    public ItemStack[] getInventory() { return this.items; }

    public void setGPSLoc(EntityPlayer player, Vec3 vector) {
        Vec3 location = Vec3.createVectorHelper(xCoord,yCoord,zCoord);
        if (vector.distanceTo(location) > ConfigUtils.range) {
            if (player.worldObj.isRemote) {
                player.addChatComponentMessage(new ChatComponentTranslation("conduit.coords.far", (int)vector.distanceTo(location),ConfigUtils.range));
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

    public InventoryType getInventoryType() {
        return this.type;
    }

    public void setTakeFrom(Vec3 vec3) {
        this.takeFrom = vec3;
    }

    public void setType(InventoryType type) {
        this.type = type;
    }

    public int getNonNullStacks() {
        if (items != null) {
            int amount = 0;
            for (int i = 0; i < this.items.length;i++) {
                if (this.items[i] != null) amount++;
            }
            return amount;
        } else {
        }
        return 0;
    }

    public int getItemCount() {
        if (items != null) {
            int amount = 0;
            for (int i = 0; i < this.items.length;i++) {
                if (items[i] != null) amount += items[i].stackSize;
            }
            return amount;
        }
        return 0;
    }

    public int getStackedCount() {
        if (items != null) {
            int amount = 0;
            for (int i = 0; i < this.items.length;i++) {
                if (items[i] != null) {
                    if (items[i].stackSize == items[i].getItem().getItemStackLimit(null)) amount += this.getInventoryStackLimit(); else amount += items[i].stackSize;
                }
            }
            return amount;
        }
        return 0;
    }

    @Override
    public int getSizeInventory() {
        if (this.items != null) {
            return this.items.length;
        } else {
            return 0;
        }
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (items != null) {
            return this.items[slot];
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (this.items[slot] != null) {
            ItemStack stack;
            if (this.items[slot].stackSize <= amount) {
                stack = this.items[slot].splitStack(amount);
                this.items[slot] = null;
                this.markDirty();
                sendPacketToClient();
                return stack;
            } else {
                stack = this.items[slot].splitStack(amount);
                if (this.items[slot].stackSize == 0) {
                    this.items[slot] = null;
                }
                this.markDirty();
                sendPacketToClient();
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (this.items[slot] != null) {
            return this.items[slot];
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (this.items != null) {
            this.items[slot] = stack;
            if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
                stack.stackSize = this.getInventoryStackLimit();
            }
            this.markDirty();
        }
    }

    @Override
    public String getInventoryName() {
        return "Item Conduit";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (this.getPullLoc() != null) {
            nbt.setInteger("takeFromX", (int)this.getPullLoc().xCoord);
            nbt.setInteger("takeFromY", (int)this.getPullLoc().yCoord);
            nbt.setInteger("takeFromZ", (int) this.getPullLoc().zCoord);
        }

        if (this.getInventoryType() != null) {
            nbt.setString("invType",this.getInventoryType().getUnlocalizedName());
            nbt.setInteger("invSize",this.getInventoryType().getSlotAmount());
        }


        if (items != null) {
            NBTTagList items = new NBTTagList();

            for (int i = 0; i < this.items.length; i++) {
                if (this.items[i] != null) {
                    NBTTagCompound itemTag = new NBTTagCompound();
                    itemTag.setByte("Slot",(byte)i);
                    this.items[i].writeToNBT(itemTag);
                    items.appendTag(itemTag);
                }
            }
            nbt.setTag("Items",items);
        }

    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("takeFromX") && nbt.hasKey("takeFromY") && nbt.hasKey("takeFromZ")) {
            this.takeFrom = Vec3.createVectorHelper(nbt.getInteger("takeFromX"), nbt.getInteger("takeFromY"), nbt.getInteger("takeFromZ"));
            WirelessItemPassaging.logger.debug(this.takeFrom);
        }
        if (nbt.hasKey("invType")) {
            InventoryType newType = null;
            for (InventoryType type : InventoryTypes.types) {
                if (type.getUnlocalizedName().equalsIgnoreCase(nbt.getString("invType"))) {
                    newType = type;
                    WirelessItemPassaging.logger.debug(newType);
                    break;
                }
            }
            this.type = newType;
        }

        if (nbt.hasKey("invSize")) {
            NBTTagList items = nbt.getTagList("Items", 10);
            this.items = new ItemStack[nbt.getInteger("invSize")];

            for (int i = 0; i < items.tagCount(); i++) {
                NBTTagCompound itemTag = items.getCompoundTagAt(i);
                byte slot = itemTag.getByte("Slot");

                if (slot >= 0 && slot < this.items.length) {
                    this.items[slot] = ItemStack.loadItemStackFromNBT(itemTag);
                }

            }
        }

    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (side == ForgeDirection.DOWN.ordinal()) {
            if (type != null) {
                return type.getSlots();
            }
        } else {
            if (type != null) {
                return type.getSlotsToImport();
            }
        }
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack item, int side) {
        return side != ForgeDirection.DOWN.ordinal() && items != null;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack item, int side) {
        return side == ForgeDirection.DOWN.ordinal() && items != null;
    }
}
