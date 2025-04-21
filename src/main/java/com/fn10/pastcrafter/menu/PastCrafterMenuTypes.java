package com.fn10.pastcrafter.menu;

import com.fn10.pastcrafter.PastCrafer;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PastCrafterMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, PastCrafer.MID);

    public static final RegistryObject<MenuType<PastExtracterMenu>> PAST_EXTRACTER_MENU = MENUS.register("past_extracter_menu",
            () -> IForgeMenuType.create(PastExtracterMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
