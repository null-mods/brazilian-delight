package net.nullmods.braziliandelight.item;

import java.util.List;
import java.util.function.Supplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.nullmods.braziliandelight.registry.ModItems;

public record GratingRecipe(Supplier<Item> input, Supplier<Item> output) {
    public static final List<GratingRecipe> ALL = List.of(
            new GratingRecipe(ModItems.CASSAVA, ModItems.GRATED_CASSAVA),
            new GratingRecipe(ModItems.CHEESE_SLICE, ModItems.GRATED_CHEESE));

    public static ItemStack resultFor(ItemStack ingredient) {
        for (GratingRecipe recipe : ALL) {
            if (ingredient.is(recipe.input().get())) {
                return new ItemStack(recipe.output().get());
            }
        }
        return ItemStack.EMPTY;
    }
}
