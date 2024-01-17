package com.harpreet.erdreamcmod.item.custom;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;




public class CrimsonGlow extends Item {

    public CrimsonGlow(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @java.lang.Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel(); //context.getWorld does not exist and getLevel returns a World object.

        if(!world.isClientSide) {
            PlayerEntity playerEntity = context.getPlayer();
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());

            rightClickOnCertainBlockState(clickedBlock, context, playerEntity);
            stack.hurtAndBreak(1, playerEntity, player -> player.broadcastBreakEvent(context.getHand()));

        }

        return super.onItemUseFirst(stack, context);
    }

    private void rightClickOnCertainBlockState(BlockState clickedBlock, ItemUseContext context,
                                               PlayerEntity playerEntity) {


        if(random.nextFloat() > 0.5f) {
            // player is struck by lightning or on fire if lightning doesn't work.
            lightEntityOnFire(playerEntity, 6);
        } else if (!playerEntity.isOnFire() && blockIsValidForResistance(clickedBlock)) {
            // gain lightning/fire resistance and destroy block.
            gainFireREsistanceAndDestroyBlock(playerEntity, context.getLevel(), context.getClickedPos());

        } else {
            // light the ground on fire.
            lightGroundOnFire(context);
        }



    }

    private boolean blockIsValidForResistance(BlockState clickedBlock) {
        return clickedBlock.getBlock() == Blocks.BIRCH_LOG ||
                clickedBlock.getBlock() == Blocks.OAK_LOG ||
                clickedBlock.getBlock() == Blocks.SPRUCE_LOG ||
                clickedBlock.getBlock() == Blocks.DARK_OAK_LOG ||
                clickedBlock.getBlock() == Blocks.JUNGLE_LOG;
    }

    public static void lightEntityOnFire(Entity entity, int second) {
        entity.setSecondsOnFire(second);
    }

    private void gainFireREsistanceAndDestroyBlock(PlayerEntity playerEntity, World world, BlockPos pos) {
        gainFireResistance(playerEntity);
        world.destroyBlock(pos, false);
    }

    public static void gainFireResistance(PlayerEntity playerEntity) {
        playerEntity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
    }

    public static void lightGroundOnFire(ItemUseContext context) {

        PlayerEntity playerentity = context.getPlayer();
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos().relative(context.getClickedFace());


        if (AbstractFireBlock.canBePlacedAt(world, blockpos, context.getHorizontalDirection())) {
            world.playSound(playerentity, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F,
                    random.nextFloat() * 0.4F + 0.8F);

            BlockState blockstate = AbstractFireBlock.getState(world, blockpos);
            world.setBlock(blockpos, blockstate, 11);
        }
    }


}
