package cn.foggyhillside.dumplings_delight.item;

import cn.foggyhillside.dumplings_delight.registry.EffectRegistry;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList {
    public static final Food PorkCabbageBoiledDumpling = new Food.Builder().hunger(8).saturation(0.9F).build();
    public static final Food PorkCarrotWonton = new Food.Builder().hunger(17).saturation(0.5F).build();
    public static final Food PorkMushroomWonton = new Food.Builder().hunger(16).saturation(0.6F).build();
    public static final Food PorkCabbageWonton = new Food.Builder().hunger(12).saturation(0.8F).build();
    public static final Food FungusBoiledDumpling = new Food.Builder().hunger(6).saturation(0.7F).build();
    public static final Food PorkKelpBoiledDumpling = new Food.Builder().hunger(7).saturation(0.8F).build();
    public static final Food CalamariBoiledDumpling = new Food.Builder().hunger(7).saturation(0.8F).build();
    public static final Food PorkCeleryBoiledDumpling = new Food.Builder().hunger(8).saturation(0.9F).build();
    public static final Food DandelionLeafBoiledDumpling = new Food.Builder().hunger(5).saturation(0.7F).effect(() -> new EffectInstance(Effects.REGENERATION,3*20,0), 1).build();
    public static final Food PufferfishBoiledDumpling = new Food.Builder().hunger(5).saturation(0.7F).effect(() -> new EffectInstance(Effects.POISON,3*20,0),0.01F).effect(() -> new EffectInstance(Effects.WATER_BREATHING,3*20,0), 1).build();
    public static final Food RabbitMeatBoiledDumpling = new Food.Builder().hunger(5).saturation(0.7F).effect(() -> new EffectInstance(Effects.JUMP_BOOST,15*20,0),1).build();
    public static final Food TomatoEggBoiledDumpling = new Food.Builder().hunger(6).saturation(0.7F).build();
    public static final Food MuttonBoiledDumpling = new Food.Builder().hunger(6).saturation(0.7F).build();
    public static final Food BeefTomatoBoiledDumpling = new Food.Builder().hunger(8).saturation(0.9F).build();
    public static final Food ChickenMushroomBoiledDumpling = new Food.Builder().hunger(7).saturation(0.8F).build();
    public static final Food MushroomBoiledDumpling = new Food.Builder().hunger(6).saturation(0.7F).build();
    public static final Food CodBoiledDumpling = new Food.Builder().hunger(6).saturation(0.7F).build();
    public static final Food PorkPotatoBoiledDumpling = new Food.Builder().hunger(10).saturation(0.6F).build();
    public static final Food SalmonBoiledDumpling = new Food.Builder().hunger(5).saturation(0.7F).build();
    public static final Food EggplantEggBoiledDumpling = new Food.Builder().hunger(6).saturation(0.7F).build();
    public static final Food GarlicClove = new Food.Builder().hunger(0).saturation(0).effect(() -> new EffectInstance(EffectRegistry.Garlic.get(), 18*20, 0),1).setAlwaysEdible().build();
    public static final Food Calamari = new Food.Builder().hunger(1).saturation(0.4F).build();
    public static final Food ChineseCabbage = new Food.Builder().hunger(3).saturation(0.2F).build();
    public static final Food ChineseCabbageLeaf = new Food.Builder().hunger(1).saturation(0.4F).build();
    public static final Food Garlic = new Food.Builder().hunger(1).saturation(0.2F).effect(() -> new EffectInstance(EffectRegistry.Garlic.get(), 75*20, 0),1).setAlwaysEdible().build();
    public static final Food GreenOnion = new Food.Builder().hunger(1).saturation(0.2F).build();
    public static final Food Eggplant = new Food.Builder().hunger(2).saturation(0.2F).build();
    public static final Food GarlicChive = new Food.Builder().hunger(1).saturation(0.2F).build();
    public static final Food Fennel = new Food.Builder().hunger(1).saturation(0.2F).build();
    public static final Food GarlicChiveEggBoiledDumpling = new Food.Builder().hunger(5).saturation(0.7F).build();
    public static final Food PorkFennelBoiledDumpling = new Food.Builder().hunger(6).saturation(0.7F).build();
}
