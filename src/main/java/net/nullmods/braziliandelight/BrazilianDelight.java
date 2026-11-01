package net.nullmods.braziliandelight;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.nullmods.braziliandelight.block.MilkCauldronBlock;
import net.nullmods.braziliandelight.registry.ModBlockEntities;
import net.nullmods.braziliandelight.registry.ModBlocks;
import net.nullmods.braziliandelight.registry.ModCreativeTabs;
import net.nullmods.braziliandelight.registry.ModItems;
import org.slf4j.Logger;

@Mod(BrazilianDelight.MOD_ID)
public class BrazilianDelight {
    public static final String MOD_ID = "brazilian_delight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BrazilianDelight(IEventBus modBus) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);
        ModCreativeTabs.TABS.register(modBus);
        modBus.addListener(BrazilianDelight::commonSetup);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(MilkCauldronBlock::registerInteractions);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
