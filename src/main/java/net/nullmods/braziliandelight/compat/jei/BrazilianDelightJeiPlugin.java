package net.nullmods.braziliandelight.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.nullmods.braziliandelight.BrazilianDelight;
import net.nullmods.braziliandelight.item.GratingRecipe;
import net.nullmods.braziliandelight.registry.ModItems;

@JeiPlugin
public class BrazilianDelightJeiPlugin implements IModPlugin {
    private static final ResourceLocation ID = BrazilianDelight.id("jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new GratingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(GratingCategory.TYPE, GratingRecipe.ALL);
        registration.addIngredientInfo(ModItems.GRATER.get(), Component.translatable("jei.brazilian_delight.grater.info"));
        registration.addIngredientInfo(ModItems.CHEESE_WHEEL.get(),
                Component.translatable("jei.brazilian_delight.cheese_wheel.info"));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModItems.GRATER.get(), GratingCategory.TYPE);
    }
}
