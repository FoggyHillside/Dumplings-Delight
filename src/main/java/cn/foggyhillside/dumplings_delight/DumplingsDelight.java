package cn.foggyhillside.dumplings_delight;

import cn.foggyhillside.dumplings_delight.registry.BlockRegistry;
import cn.foggyhillside.dumplings_delight.registry.EffectRegistry;
import cn.foggyhillside.dumplings_delight.registry.ItemRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("dumplings_delight")
public class DumplingsDelight {
    public DumplingsDelight() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EffectRegistry.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());

        eventBus.addListener(this::doClientStuff);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(()-> {
            RenderTypeLookup.setRenderLayer(BlockRegistry.ChineseCabbages.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.Garlic.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.GreenOnion.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.Eggplant.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.GarlicChive.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.Fennel.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.DumplingMedley.get(), RenderType.getCutout());
        });
    }

    public static final String MOD_ID = "dumplings_delight";
}