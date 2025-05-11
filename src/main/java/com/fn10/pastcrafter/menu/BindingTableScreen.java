package com.fn10.pastcrafter.menu;

import javax.annotation.Nonnull;

import com.fn10.pastcrafter.PastCrafer;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BindingTableScreen extends ItemCombinerScreen<BindingTableMenu> {

    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(PastCrafer.MID,
            "textures/gui/binding_table_gui.png");

    public BindingTableScreen(BindingTableMenu menu, Inventory pPlayerInventory, Component pTitle) {
        super(menu, pPlayerInventory, pTitle, GUI_TEXTURE);
    }

    @Override
    protected void renderBg(@SuppressWarnings("null") GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX,
            int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2; // i
        int y = (height - imageHeight) / 2; // j

        pGuiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        this.renderErrorIcon(pGuiGraphics, this.leftPos, this.topPos);
    }

    @Override
    protected void renderLabels(@Nonnull GuiGraphics p_281442_, int p_282417_, int p_283022_) {
        super.renderLabels(p_281442_, p_282417_, p_283022_);
        if (this.menu.error) {
            String error = this.menu.currentErrorString;
            int k = this.imageWidth - 8 - this.font.width(error) - 2;
            p_281442_.fill(k - 2, 67, this.imageWidth - 8, 79, 1325400064);
            p_281442_.drawString(this.font, error, k, 69, 16736352);
        }
    }

    @Override
    public void render(@SuppressWarnings("null") GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderErrorIcon(@Nonnull GuiGraphics p_281990_, int p_266822_, int p_267045_) {
        return;
    }
}
