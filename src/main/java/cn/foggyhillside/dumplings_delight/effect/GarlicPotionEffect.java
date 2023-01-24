package cn.foggyhillside.dumplings_delight.effect;

import cn.foggyhillside.dumplings_delight.registry.EffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effect;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GarlicPotionEffect extends Effect {
	public GarlicPotionEffect() {
		super(EffectType.BENEFICIAL, -4476269);
	}

	public static class StartEat {
		@Mod.EventBusSubscriber
		private static class GlobalTrigger {
			@SubscribeEvent
			public static void onUseItemStart(LivingEntityUseItemEvent.Start event) {
				if (event != null && event.getEntity() != null) {
					Entity entity = event.getEntity();
					double i = entity.getPosX();
					double j = entity.getPosY();
					double k = entity.getPosZ();
					double duration = event.getDuration();
					ItemStack itemstack = event.getItem();
					World world = entity.world;
					Map<String, Object> dependencies = new HashMap<>();
					dependencies.put("x", i);
					dependencies.put("y", j);
					dependencies.put("z", k);
					dependencies.put("itemstack", itemstack);
					dependencies.put("duration", duration);
					dependencies.put("world", world);
					dependencies.put("entity", entity);
					dependencies.put("event", event);
					isDumpling(dependencies);
				}
			}

			private static boolean isDumpling(Map<String, Object> dependencies) {
				ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
				if (ItemTags.getCollection().getTagByID(new ResourceLocation("dumplings_delight:dumpling")).contains(itemstack.getItem())) {
					return true;
				}
				return false;
			}
		}
	}
		public static class Finish {
			@Mod.EventBusSubscriber
			private static class GlobalTrigger {
				@SubscribeEvent
				public static void onUseItemStart(LivingEntityUseItemEvent.Finish event) {
					if (event != null && event.getEntity() != null) {
						Entity entity = event.getEntity();
						double i = entity.getPosX();
						double j = entity.getPosY();
						double k = entity.getPosZ();
						double duration = event.getDuration();
						ItemStack itemstack = event.getItem();
						World world = entity.world;
						Map<String, Object> dependencies = new HashMap<>();
						dependencies.put("x", i);
						dependencies.put("y", j);
						dependencies.put("z", k);
						dependencies.put("itemstack", itemstack);
						dependencies.put("duration", duration);
						dependencies.put("world", world);
						dependencies.put("entity", entity);
						dependencies.put("event", event);
						WithGarlicPotion(dependencies);
					}
				}

				public static void WithGarlicPotion(Map<String, Object> dependencies) {
					Entity entity = (Entity) dependencies.get("entity");
					ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
					if (GarlicPotionEffect.StartEat.GlobalTrigger.isDumpling(dependencies) && new Object() {
						boolean check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == EffectRegistry.Garlic.get())
										return true;
								}
							}
							return false;
						}
					}.check(entity)) {
						if (entity instanceof PlayerEntity)
							((PlayerEntity) entity).getFoodStats()
									.setFoodLevel((int) (((PlayerEntity) entity).getFoodStats().getFoodLevel() + 1));
					}
				}
			}
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
}
