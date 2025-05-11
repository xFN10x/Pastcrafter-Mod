package com.fn10.pastcrafter.classes;

import javax.annotation.Nullable;

import com.fn10.pastcrafter.classes.enums.BindingTableError;

import net.minecraft.world.item.ItemStack;

public class BindingTableErrorHolder {

    public String getFullErrorString(@Nullable BindingTableError error ,@Nullable ItemStack inputItem, @Nullable BindingTableRecipe recipe) {
        if (error == null) return "Error";

        if (error == BindingTableError.NORECIPE) {
            if (inputItem == null) return "No Binding Recipe for item.";
            return "No Binding Recipe for item " + inputItem.getDisplayName().getString() + "!";
        } else if (error == BindingTableError.NOTENOUGHEXP) {
            if (recipe == null) return "Not enough Past EXP.";
            return "Past EXP Required: " + recipe.pastExp;
        } else {
            return "Error";
        }
    }

}
