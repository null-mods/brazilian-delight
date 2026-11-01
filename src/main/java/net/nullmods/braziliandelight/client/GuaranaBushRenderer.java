package net.nullmods.braziliandelight.client;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.nullmods.braziliandelight.BrazilianDelight;
import net.nullmods.braziliandelight.block.GuaranaBushBlock;
import net.nullmods.braziliandelight.block.GuaranaBushBlockEntity;

public class GuaranaBushRenderer implements BlockEntityRenderer<GuaranaBushBlockEntity> {
    private static final int FIRST_FRUITING_AGE = 2;

    public static final List<ModelResourceLocation> EYELID_MODELS = List.of(
            eyelid(2, Blink.HALF), eyelid(2, Blink.CLOSED), lookingRight(2),
            eyelid(3, Blink.HALF), eyelid(3, Blink.CLOSED), lookingRight(3));

    private final BlockRenderDispatcher blockRenderer;

    public GuaranaBushRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    private static ModelResourceLocation eyelid(int age, Blink blink) {
        String suffix = blink == Blink.HALF ? "half" : "closed";
        return ModelResourceLocation.standalone(BrazilianDelight.id("block/guarana_bush_stage" + age + "_" + suffix));
    }

    private static ModelResourceLocation lookingRight(int age) {
        return ModelResourceLocation.standalone(BrazilianDelight.id("block/guarana_bush_stage" + age + "_right"));
    }

    @Override
    public void render(GuaranaBushBlockEntity bush, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffers, int packedLight, int packedOverlay) {
        BlockState state = bush.getBlockState();
        BakedModel model = blockRenderer.getBlockModel(state);
        Level level = bush.getLevel();
        int age = state.getValue(GuaranaBushBlock.AGE);
        if (level != null && age >= FIRST_FRUITING_AGE) {
            Blink blink = Blink.at(bush.getBlockPos().asLong(), level.getGameTime(), partialTick);
            if (blink != Blink.OPEN) {
                model = Minecraft.getInstance().getModelManager().getModel(eyelid(age, blink));
            } else if (Gaze.at(bush.getBlockPos().asLong(), level.getGameTime()) == Gaze.RIGHT) {
                model = Minecraft.getInstance().getModelManager().getModel(lookingRight(age));
            }
        }
        blockRenderer.getModelRenderer().renderModel(poseStack.last(), buffers.getBuffer(Sheets.cutoutBlockSheet()),
                state, model, 1.0F, 1.0F, 1.0F, packedLight, packedOverlay, ModelData.EMPTY, RenderType.cutout());
    }
}
