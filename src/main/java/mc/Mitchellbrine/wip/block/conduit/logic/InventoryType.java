package mc.Mitchellbrine.wip.block.conduit.logic;

import mc.Mitchellbrine.wip.WirelessItemPassaging;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

/**
 * Created by Mitchellbrine on 2014.
 */
public class InventoryType {

    private String unlocalizedName;
    private int invType,slots;
    private Block[] blocks;
    private ResourceLocation textureName;
    private int[] slotsToTakeFrom,slotsToImport;
    private int[] metadata;

    /**
     * The constructor for a new InventoryType. No overriding is needed \o/!
     * @param name - The unlocalized name (w/o "invtype.") of the inventory type.
     * @param slots - The amount of slots in the inventory type
     * @param acceptableBlocks - What blocks trigger the inventory type to be set in a conduit
     * @param texture - Sets the texture to appear in the top-left of the conduit's UI
     * @param invType - The behavior of the inventory type (0 = Full Inventory Seizure of Items, 1 = Take only from slotsArray, 2 = Use custom event to handle inventory behavior)
     * @param slotsArray - The slots the conduit can pull from (used only if invType is 1)
     * @param slotsToImport - The slots the conduit can receive items in using a hopper or import solution
     */
    public InventoryType(String name, int slots, Block[] acceptableBlocks, ResourceLocation texture,int invType, int[] slotsArray,int[] slotsToImport) {
        this.unlocalizedName = "invtype." + name;
        this.slots = slots;
        this.blocks = acceptableBlocks;
        this.textureName = texture;
        this.invType = invType;
        this.slotsToTakeFrom = slotsArray;
        this.slotsToImport = slotsToImport;
        WirelessItemPassaging.logger.info("Registered inventory type " + this.getUnlocalizedName() + " with " + this.getSlotAmount());
        InventoryTypes.types.add(this);
    }

    /**
     * The METADATA-SENSITIVE version of the constructor for a new InventoryType. No overriding is needed \o/!
     * @param name - The unlocalized name (w/o "invtype.") of the inventory type.
     * @param slots - The amount of slots in the inventory type
     * @param acceptableBlocks - What blocks trigger the inventory type to be set in a conduit
     * @param texture - Sets the texture to appear in the top-left of the conduit's UI
     * @param invType - The behavior of the inventory type (0 = Full Inventory Seizure of Items, 1 = Take only from slotsArray, 2 = Use custom event to handle inventory behavior)
     * @param slotsArray - The slots the conduit can pull from (used only if invType is 1)
     * @param slotsToImport - The slots the conduit can receive items in using a hopper or import solution
     */
    public InventoryType(String name, int slots, Block[] acceptableBlocks, int[] metadata, ResourceLocation texture,int invType, int[] slotsArray,int[] slotsToImport) {
        this.unlocalizedName = "invtype." + name;
        this.slots = slots;
        this.blocks = acceptableBlocks;
        this.metadata = metadata;
        this.textureName = texture;
        this.invType = invType;
        this.slotsToTakeFrom = slotsArray;
        this.slotsToImport = slotsToImport;
        WirelessItemPassaging.logger.info("Registered inventory type " + this.getUnlocalizedName() + " with " + this.getSlotAmount());
        InventoryTypes.types.add(this);
    }

    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal(this.unlocalizedName);
    }

    public int getSlotAmount() {
        return this.slots;
    }

    public Block[] getBlocks() {
        return this.blocks;
    }

    public ResourceLocation getTextureName() {
        return this.textureName;
    }

    public int[] getSlots() {
        return this.slotsToTakeFrom;
    }

    public int[] getSlotsToImport() {
        return this.slotsToImport;
    }

    public int getTransferType() {
        return this.invType;
    }

    public int[] getMetadata() { return this.metadata; }

}
