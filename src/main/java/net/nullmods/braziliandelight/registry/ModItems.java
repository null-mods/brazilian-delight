package net.nullmods.braziliandelight.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nullmods.braziliandelight.BrazilianDelight;
import net.nullmods.braziliandelight.item.GraterItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BrazilianDelight.MOD_ID);

    public static final DeferredItem<Item> GUARANA = ITEMS.register("guarana",
            () -> new ItemNameBlockItem(ModBlocks.GUARANA_BUSH.get(), new Item.Properties()));

    public static final DeferredItem<Item> GUARANA_SODA = ITEMS.register("guarana_soda",
            () -> new DrinkableItem(new Item.Properties().stacksTo(16).food(ModFoods.GUARANA_SODA), true,
                    false));

    public static final DeferredItem<Item> CASSAVA = ITEMS.register("cassava",
            () -> new ItemNameBlockItem(ModBlocks.CASSAVA_CROP.get(), new Item.Properties().food(ModFoods.CASSAVA)));

    public static final DeferredItem<Item> WILD_CASSAVA = ITEMS.register("wild_cassava",
            () -> new BlockItem(ModBlocks.WILD_CASSAVA.get(), new Item.Properties()));

    public static final DeferredItem<Item> COOKED_CASSAVA = ITEMS.register("cooked_cassava",
            () -> new Item(new Item.Properties().stacksTo(64).food(ModFoods.COOKED_CASSAVA)));

    public static final DeferredItem<Item> GRATED_CASSAVA = ITEMS.register("grated_cassava",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TOASTED_CASSAVA_FLOUR = ITEMS.register("toasted_cassava_flour",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final DeferredItem<Item> CASSAVA_STARCH = ITEMS.register("cassava_starch",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final DeferredItem<Item> CHEESE_BREAD = ITEMS.register("cheese_bread",
            () -> new Item(new Item.Properties().stacksTo(64).food(ModFoods.CHEESE_BREAD)));

    public static final DeferredItem<Item> GRATER = ITEMS.register("grater",
            () -> new GraterItem(new Item.Properties().durability(250)));

    private ModItems() {
    }
}
