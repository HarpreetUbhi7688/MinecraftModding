package com.harpreet.erdreamcmod.item;

import com.harpreet.erdreamcmod.ErdreaMCMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    /// A list that keeps track of all items, blocks, and other things that we create in the mod.
    // This is so that Forge can keep track of it and register it at the correct time.
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ErdreaMCMod.MOD_ID);

    // Sample/Test/Example First Item.
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties().tab(ModItemGroup.ERDREA_GROUP)));


    // This register also needs to be registered and can be done with the code below.
    // This is a register for the mod items
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
