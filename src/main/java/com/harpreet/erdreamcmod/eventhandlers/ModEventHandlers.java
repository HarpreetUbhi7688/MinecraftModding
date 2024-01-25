package com.harpreet.erdreamcmod.eventhandlers;

import com.harpreet.erdreamcmod.block.custom.CrimsonGlowBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "erdreamcmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventHandlers {
    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof CrimsonGlowBlock) {
            System.out.println("I left-clicked a CrimsonGlow block!");
        }
    }
}
