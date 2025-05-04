package com.fn10.pastcrafter.menu;

import com.fn10.pastcrafter.PastCrafer;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class PastExtracterScreen extends AbstractContainerScreen<PastExtracterMenu> {

    private static final ResourceLocation PROGRESS_BAR =
    ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, "past_extracter/past_extracter_progress");
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, "textures/gui/past_extracter_gui.png");

    public PastExtracterScreen(PastExtracterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(@SuppressWarnings("null") GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2; //i
        int y = (height - imageHeight) / 2; //j

        pGuiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        
        int k = this.menu.blockEntity.Timer;
        //System.out.println("Timer value: " + k);
        int l = Mth.floor(Mth.clamp((51f * k / 600f), 0f,51f));
        if (l > 0) {
            pGuiGraphics.blitSprite(PROGRESS_BAR, 28, 51, 0, 0, x + 76, y + 18, 28, l);
        }
    }

    @Override
    public void render(@SuppressWarnings("null") GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}