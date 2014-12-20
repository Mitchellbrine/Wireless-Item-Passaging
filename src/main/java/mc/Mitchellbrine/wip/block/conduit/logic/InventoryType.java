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
    private int slots;
    private Block[] blocks;
    private ResourceLocation textureName;

    public InventoryType(String name, int slots, Block[] acceptableBlocks,ResourceLocation texture) {
        this.unlocalizedName = "invtype." + name;
        this.slots = slots;
        this.blocks = acceptableBlocks;
        this.textureName = texture;
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

}
