package cn.foggyhillside.dumplings_delight.registry;

import cn.foggyhillside.dumplings_delight.DumplingsDelight;
import cn.foggyhillside.dumplings_delight.effect.GarlicPotionEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegistry {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, DumplingsDelight.MOD_ID);

    public static final RegistryObject<Effect> Garlic = EFFECTS.register("garlic",
            GarlicPotionEffect::new);
}
