package net.nullmods.braziliandelight.registry;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nullmods.braziliandelight.BrazilianDelight;
import net.nullmods.braziliandelight.block.CassavaCropBlock;
import net.nullmods.braziliandelight.block.GuaranaBushBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BrazilianDelight.MOD_ID);

    public static final DeferredBlock<GuaranaBushBlock> GUARANA_BUSH = BLOCKS.register("guarana_bush",
            () -> new GuaranaBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<CassavaCropBlock> CASSAVA_CROP = BLOCKS.register("cassava_crop",
            () -> new CassavaCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));

    public static final DeferredBlock<WildCropBlock> WILD_CASSAVA = BLOCKS.register("wild_cassava",
            () -> new WildCropBlock(MobEffects.POISON, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));

    private ModBlocks() {
    }
}
