package mc.Mitchellbrine.wip.client.gui;

import mc.Mitchellbrine.wip.container.ContainerFilter;
import mc.Mitchellbrine.wip.network.FilterChangeTypePacket;
import mc.Mitchellbrine.wip.network.PacketHandler;
import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import mc.Mitchellbrine.wip.util.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by Mitchellbrine on 2014.
 */
public class GuiFilter extends GuiContainer{

    private ResourceLocation texture = new ResourceLocation(References.MODID.toLowerCase(),"textures/guis/filter.png");

    private TileEntityConduit te;

    public GuiFilter(EntityPlayer player, TileEntityConduit te) {
        super(new ContainerFilter(te, player));
        this.te = te;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, guiLeft - 77, guiTop, 75, 15, "Filter State"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch(button.id) {
            case 1:
                PacketHandler.INSTANCE.sendToServer(new FilterChangeTypePacket(te,te.getFilterType()));
                break;
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par2, int par3) {
        fontRendererObj.drawString(StatCollector.translateToLocal("gui.conduit.name"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("gui.conduit.name")) / 2, 4, 0xFFFFFF);
        if (te.getFilterType() == 0) {
            fontRendererObj.drawStringWithShadow(StatCollector.translateToLocal("gui.filter.black"),8,ySize - 102,0x000000);
        } else {
            fontRendererObj.drawStringWithShadow(StatCollector.translateToLocal("gui.filter.white"),8,ySize - 102,0xFFFFFF);
        }
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 92, 0xFFFFFF);
    }

        @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1F,1F,1F,1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);
    }
}
