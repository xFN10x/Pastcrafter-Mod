package com.fn10.pastcrafter.menu;

import javax.annotation.Nonnull;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.component.PastCrafterComponents;
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
    private static final ResourceLocation ERROR_SPRITE = ResourceLocation.withDefaultNamespace("container/anvil/error");

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

    }

    @Override
    public void render(@SuppressWarnings("null") GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderErrorIcon(@Nonnull GuiGraphics p_282905_, int p_283237_, int p_282237_) {
        Float inputexp = this.menu.getSlot(0).getItem().get(PastCrafterComponents.PAST_EXP.get());
        if (inputexp == null)
            p_282905_.blitSprite(ERROR_SPRITE, p_283237_, p_282237_, 28, 21);
        else if (inputexp < this.menu.recipes.get(this.menu.targetRecipe.get()).pastExp) {
            p_282905_.blitSprite(ERROR_SPRITE, p_283237_, p_282237_, 28, 21);
        }
    } 
}
