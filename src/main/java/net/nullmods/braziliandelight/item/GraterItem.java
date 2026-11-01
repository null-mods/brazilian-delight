package net.nullmods.braziliandelight.item;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GraterItem extends Item {
    private static final int USE_TICKS = 32;
    private static final int SOUND_INTERVAL = 4;

    public GraterItem(Properties properties) {
        super(properties);
    }

    private static InteractionHand otherHand(InteractionHand hand) {
        return hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack grater = player.getItemInHand(hand);
        if (GratingRecipe.resultFor(player.getItemInHand(otherHand(hand))).isEmpty()) {
            return InteractionResultHolder.pass(grater);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(grater);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return USE_TICKS;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BRUSH;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingTicks) {
        ItemStack ingredient = entity.getItemInHand(otherHand(entity.getUsedItemHand()));
        if (GratingRecipe.resultFor(ingredient).isEmpty()) {
            entity.releaseUsingItem();
            return;
        }
        if (remainingTicks % SOUND_INTERVAL != 0) {
            return;
        }
        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BRUSH_GENERIC,
                SoundSource.PLAYERS, 0.6F, 0.8F + level.random.nextFloat() * 0.4F);
        if (level.isClientSide) {
            Vec3 look = entity.getLookAngle();
            Vec3 at = entity.getEyePosition().add(look.scale(0.5)).add(0, -0.3, 0);
            for (int i = 0; i < 3; i++) {
                level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, ingredient), at.x, at.y, at.z,
                        (level.random.nextDouble() - 0.5) * 0.1, 0.05, (level.random.nextDouble() - 0.5) * 0.1);
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (level.isClientSide || !(entity instanceof Player player)) {
            return stack;
        }
        InteractionHand hand = entity.getUsedItemHand();
        ItemStack ingredient = player.getItemInHand(otherHand(hand));
        ItemStack result = GratingRecipe.resultFor(ingredient);
        if (result.isEmpty()) {
            return stack;
        }
        if (!player.getAbilities().instabuild) {
            ingredient.shrink(1);
        }
        if (!player.getInventory().add(result)) {
            player.drop(result, false);
        }
        stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
        return stack;
    }
}
