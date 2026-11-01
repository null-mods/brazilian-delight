package net.nullmods.braziliandelight.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.nullmods.braziliandelight.registry.ModBlocks;
import net.nullmods.braziliandelight.registry.ModItems;

public class MilkCauldronBlock extends AbstractCauldronBlock {
    public static final MapCodec<MilkCauldronBlock> CODEC = simpleCodec(MilkCauldronBlock::new);
    public static final BooleanProperty CURDLED = BooleanProperty.create("curdled");
    public static final CauldronInteraction.InteractionMap INTERACTIONS =
            CauldronInteraction.newInteractionMap("brazilian_delight_milk");
    private static final float CURDLE_CHANCE = 0.5F;

    public MilkCauldronBlock(Properties properties) {
        super(properties, INTERACTIONS);
        registerDefaultState(stateDefinition.any().setValue(CURDLED, false));
    }

    public static void registerInteractions() {
        CauldronInteraction.EMPTY.map().put(Items.MILK_BUCKET,
                (state, level, pos, player, hand, stack) -> CauldronInteraction.emptyBucket(level, pos, player,
                        hand, stack, ModBlocks.MILK_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY));
        INTERACTIONS.map().put(Items.BUCKET,
                (state, level, pos, player, hand, stack) -> CauldronInteraction.fillBucket(state, level, pos,
                        player, hand, stack, new ItemStack(Items.MILK_BUCKET), s -> !s.getValue(CURDLED),
                        SoundEvents.BUCKET_FILL));
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    protected double getContentHeight(BlockState state) {
        return 0.9375D;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CURDLED);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(CURDLED);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < CURDLE_CHANCE) {
            level.setBlockAndUpdate(pos, state.setValue(CURDLED, true));
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
                                               BlockHitResult hit) {
        if (!state.getValue(CURDLED)) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide) {
            ItemStack cheese = new ItemStack(ModItems.CHEESE_WHEEL.get());
            if (!player.getInventory().add(cheese)) {
                player.drop(cheese, false);
            }
            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
            level.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 0.8F, 1.2F);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
