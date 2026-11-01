package net.nullmods.braziliandelight.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.nullmods.braziliandelight.BrazilianDelight;
import net.nullmods.braziliandelight.item.GratingRecipe;
import net.nullmods.braziliandelight.registry.ModItems;

public class GratingCategory implements IRecipeCategory<GratingRecipe> {
    public static final RecipeType<GratingRecipe> TYPE = RecipeType.create(BrazilianDelight.MOD_ID, "grating",
            GratingRecipe.class);
    private static final int WIDTH = 82;
    private static final int HEIGHT = 36;
    private static final int SLOT_Y = 19;

    private final IDrawable icon;
    private final IDrawable slot;
    private final IDrawableAnimated arrow;

    public GratingCategory(IGuiHelper guiHelper) {
        this.icon = guiHelper.createDrawableItemLike(ModItems.GRATER.get());
        this.slot = guiHelper.getSlotDrawable();
        this.arrow = guiHelper.createAnimatedRecipeArrow(32);
    }

    @Override
    public RecipeType<GratingRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.brazilian_delight.grating");
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GratingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, SLOT_Y).addItemLike(recipe.input().get());
        builder.addSlot(RecipeIngredientRole.CATALYST, 33, 1).addItemLike(ModItems.GRATER.get());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 65, SLOT_Y).addItemLike(recipe.output().get());
    }

    @Override
    public void draw(GratingRecipe recipe, IRecipeSlotsView slots, GuiGraphics graphics, double mouseX,
                     double mouseY) {
        slot.draw(graphics, 0, SLOT_Y - 1);
        slot.draw(graphics, 32, 0);
        slot.draw(graphics, 64, SLOT_Y - 1);
        arrow.draw(graphics, 28, SLOT_Y);
    }
}
