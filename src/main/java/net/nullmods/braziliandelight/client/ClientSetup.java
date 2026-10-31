package net.nullmods.braziliandelight.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.nullmods.braziliandelight.BrazilianDelight;
import net.nullmods.braziliandelight.registry.ModBlockEntities;

@EventBusSubscriber(modid = BrazilianDelight.MOD_ID, value = Dist.CLIENT)
public final class ClientSetup {
    private ClientSetup() {
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GUARANA_BUSH.get(), GuaranaBushRenderer::new);
    }

    @SubscribeEvent
    public static void registerEyelidModels(ModelEvent.RegisterAdditional event) {
        GuaranaBushRenderer.EYELID_MODELS.forEach(event::register);
    }
}
