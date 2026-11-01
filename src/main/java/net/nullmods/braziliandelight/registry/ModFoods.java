package net.nullmods.braziliandelight.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public final class ModFoods {

    public static final FoodProperties GUARANA = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 0.3F)
            .build();

    public static final FoodProperties GUARANA_SODA = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3F)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 0.3F)
            .build();
            
    public static final FoodProperties COOKED_CASSAVA = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.6F)
            .build();

    public static final FoodProperties CHEESE_BREAD = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.65F)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 0.45F)
            .build();

    public static final FoodProperties CHEESE_SLICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.5F)
            .fast()
            .build();

    private ModFoods() {
    }
}
