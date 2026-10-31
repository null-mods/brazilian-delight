package net.nullmods.braziliandelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nullmods.braziliandelight.registry.ModBlockEntities;

public class GuaranaBushBlockEntity extends BlockEntity {
    public GuaranaBushBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GUARANA_BUSH.get(), pos, state);
    }
}
