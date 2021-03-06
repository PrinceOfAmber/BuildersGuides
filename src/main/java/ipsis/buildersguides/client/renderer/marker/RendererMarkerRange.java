package ipsis.buildersguides.client.renderer.marker;

import ipsis.buildersguides.tileentity.TileEntityMarker;

public class RendererMarkerRange extends RendererMarker {

    @Override
    public void doRenderMarkerType(TESRMarker tesrMarker, TileEntityMarker te, double relX, double relY, double relZ, float partialTicks) {

        renderLineToTargets(te, relX, relY, relZ);
        renderTargets(te, relX, relY, relZ);
        renderRangeToTargets(tesrMarker, te, relX, relY, relZ);
        /*
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        {
            // translate to center or te
            GlStateManager.translate(relX + 0.5F , relY + 0.5F, relZ + 0.5F);
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GlStateManager.color(te.getColor().getRed(), te.getColor().getGreen(), te.getColor().getBlue());
            FontRenderer fontrenderer = tesrMarker.getFontRenderer();

            // render target points
            for (EnumFacing f : EnumFacing.VALUES) {
                if (te.hasTarget(f) && te.getFaceData(f) > 0) {

                    int diff = te.getFaceData(f);
                    String s = Integer.toString(diff);

                    GlStateManager.pushMatrix();
                    {
                        GlStateManager.translate(f.getFrontOffsetX() * 0.5F, f.getFrontOffsetY() * 0.5F, f.getFrontOffsetZ() * 0.5F);

                        GlStateManager.translate(0.0F, 1.0F, 0.0F);

                        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                        GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);

                        float f1 = 0.016666668F * 1.6F;
                        GlStateManager.scale(-f1, -f1, f1);
                        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, ColorBG.BLACK.getV());

                    }
                    GlStateManager.popMatrix();
                }
            }
        }

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
                    */
    }
}
