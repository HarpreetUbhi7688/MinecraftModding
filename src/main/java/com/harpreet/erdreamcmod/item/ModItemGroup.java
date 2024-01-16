package com.harpreet.erdreamcmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup ERDREA_GROUP = new ItemGroup("erdreaModTab") {
        @java.lang.Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RUBY.get());
        }
    };
}
