package mc.Mitchellbrine.wip.client.render;

import mc.Mitchellbrine.wip.tileentity.TileEntityConduit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Mitchellbrine on 2014.
 */
public class ConduitTESR extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity entity, double par2, double par3, double par4, float par5) {
        if (entity instanceof TileEntityConduit) {
            TileEntityConduit te = (TileEntityConduit) entity;
            if (te.getInventoryType() != null && te.getInventoryType().getTextureName() != null) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)par2 + 0.5F,(float)par3 + 1.5F,(float)par4 + 0.5F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(te.getInventoryType().getTextureName());
                GL11.glPopMatrix();
            }
        }
    }
}
