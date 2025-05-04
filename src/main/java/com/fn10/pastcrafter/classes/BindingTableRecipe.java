package com.fn10.pastcrafter.classes;

import net.minecraft.world.item.Item;

public class BindingTableRecipe {
    public Float pastExp;
    public Item input;
    public Item output;

    public BindingTableRecipe(Item in, Item out, Float exp) {
        this.pastExp = exp;
        this.input = in;
        this.output = out;
    }
}
