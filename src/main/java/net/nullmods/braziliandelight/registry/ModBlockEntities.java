package net.nullmods.braziliandelight.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nullmods.braziliandelight.BrazilianDelight;
import net.nullmods.braziliandelight.block.GuaranaBushBlockEntity;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, BrazilianDelight.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GuaranaBushBlockEntity>> GUARANA_BUSH =
            BLOCK_ENTITIES.register("guarana_bush",
                    () -> BlockEntityType.Builder.of(GuaranaBushBlockEntity::new, ModBlocks.GUARANA_BUSH.get()).build(null));

    private ModBlockEntities() {
    }
}
