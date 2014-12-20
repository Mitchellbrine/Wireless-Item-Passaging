package mc.Mitchellbrine.wip.client.gui;

import mc.Mitchellbrine.wip.block.conduit.logic.InventoryType;
import mc.Mitchellbrine.wip.container.ContainerConduit;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by Mitchellbrine on 2014.
 */
@SuppressWarnings("unused")
public class GuiConduit extends GuiContainer {

    public static ResourceLocation background = new ResourceLocation(References.MODID.toLowerCase(),"textures/guis/conduit.png");
    private ContainerConduit container;
    private TileEntityConduit te;

    public GuiConduit(EntityPlayer player, TileEntityConduit te) {
        super(new ContainerConduit(player));
        this.container = (ContainerConduit) this.inventorySlots;
        this.te = te;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par2, int par3) {
        fontRendererObj.drawString(StatCollector.translateToLocal("gui.conduit.name"),xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("gui.conduit.name")) / 2, 4, 0xFFFFFF);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"),8,ySize - 92,0xFFFFFF);

        int yStart = 14;

        if (te.getPullLoc() != null) {
            fontRendererObj.drawStringWithShadow("Coords: " + (int)te.getPullLoc().xCoord + ", " + (int)te.getPullLoc().yCoord + ", " + (int)te.getPullLoc().zCoord,6,yStart,0xFFFFFF);
            yStart += 10;
        } else {
            fontRendererObj.drawStringWithShadow("Coords: -, -, -",6,14,0xFF0000);
            yStart += 8;
            fontRendererObj.drawStringWithShadow(StatCollector.translateToLocal("conduit.coords.null"),6,22,0xFF0000);
            yStart += 10;
        }

        if (te.getInventoryType() != null) {
            InventoryType type = te.getInventoryType();
            fontRendererObj.drawStringWithShadow(StatCollector.translateToLocal("conduit.inventory.type") + ": " + te.getInventoryType().getLocalizedName() + " | " + te.getInventoryType().getSlotAmount() + " slots",6,yStart,0xFFFFFF);
        } else {
            fontRendererObj.drawStringWithShadow(StatCollector.translateToLocal("conduit.inventory.type") + ": Not assigned",6,yStart,0xFFFFFF);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1F,1F,1F,1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);

    }
}
