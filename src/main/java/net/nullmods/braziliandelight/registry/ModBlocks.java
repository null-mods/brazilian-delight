package net.nullmods.braziliandelight.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nullmods.braziliandelight.BrazilianDelight;
import net.nullmods.braziliandelight.block.GuaranaBushBlock;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BrazilianDelight.MOD_ID);

    public static final DeferredBlock<GuaranaBushBlock> GUARANA_BUSH = BLOCKS.register("guarana_bush",
            () -> new GuaranaBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    private ModBlocks() {
    }
}
