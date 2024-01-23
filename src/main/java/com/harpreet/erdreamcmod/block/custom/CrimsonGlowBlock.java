package com.harpreet.erdreamcmod.block.custom;

import com.harpreet.erdreamcmod.item.custom.CrimsonGlow;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class CrimsonGlowBlock extends Block {
    public CrimsonGlowBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity,
                                Hand hand, BlockRayTraceResult hit) {
        if(!world.isClientSide()) {

            if(hand == Hand.MAIN_HAND) {
                System.out.println("I right-clicked a CrimsonGlowBlock. Called for the Main Hand!");
            } else if(hand == Hand.OFF_HAND) {
                System.out.println("I right-clicked a CrimsonGlowBlock. Called for the Off Hand!");
            } else {
                System.out.println("I left-clicked a CrimsonGlowBlock.");
            }
        }
        return super.use(blockState, world, blockPos, playerEntity, hand, hit);
    }



    @Override
    public void stepOn(World world, BlockPos blockPos, Entity entity) {
        CrimsonGlow.lightEntityOnFire(entity, 5);
        super.stepOn(world, blockPos, entity);
    }
}
