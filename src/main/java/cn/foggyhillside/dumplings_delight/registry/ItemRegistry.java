package cn.foggyhillside.dumplings_delight.registry;

import cn.foggyhillside.dumplings_delight.DumplingsDelight;
import cn.foggyhillside.dumplings_delight.DumplingsDelightGroup;
import cn.foggyhillside.dumplings_delight.item.FoodList;
import cn.foggyhillside.dumplings_delight.item.GarlicItem;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.system.CallbackI;
import vectorwing.farmersdelight.items.ConsumableItem;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DumplingsDelight.MOD_ID);

    //Crate
    public static final RegistryObject<Item> ChineseCabbageCrate = ITEMS.register("chinese_cabbage_crate",
            ()-> new BlockItem(BlockRegistry.ChineseCabbageCrate.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> GarlicCrate = ITEMS.register("garlic_crate",
            ()-> new BlockItem(BlockRegistry.GarlicCrate.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> GreenOnionCrate = ITEMS.register("greenonion_crate",
            ()-> new BlockItem(BlockRegistry.GreenOnionCrate.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> EggplantCrate = ITEMS.register("eggplant_crate",
            ()-> new BlockItem(BlockRegistry.EggplantCrate.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> GarlicChiveCrate = ITEMS.register("garlic_chive_crate",
            ()-> new BlockItem(BlockRegistry.GarlicChiveCrate.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> FennelCrate = ITEMS.register("fennel_crate",
            ()-> new BlockItem(BlockRegistry.FennelCrate.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    //Feast
    public static final RegistryObject<Item> DumplingMedley = ITEMS.register("dumpling_medley",
            ()-> new BlockItem(BlockRegistry.DumplingMedley.get(), new Item.Properties().maxStackSize(1).group(DumplingsDelightGroup.DumplingsDelightGroup)));

    public static final RegistryObject<Item> Vinegar = ITEMS.register("vinegar",
            () -> new ConsumableItem(new Item.Properties().maxStackSize(1).containerItem(Items.GLASS_BOTTLE).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> Calamari = ITEMS.register("calamari",
            ()-> new Item(new Item.Properties().food(FoodList.Calamari).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    //Crops
    public static final RegistryObject<Item> ChineseCabbage = ITEMS.register("chinese_cabbage",
            ()-> new Item(new Item.Properties().food(FoodList.ChineseCabbage).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> ChineseCabbageSeeds = ITEMS.register("chinese_cabbage_seeds",
            ()-> new BlockItem(BlockRegistry.ChineseCabbages.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> ChineseCabbageLeaf = ITEMS.register("chinese_cabbage_leaf",
            ()-> new Item(new Item.Properties().food(FoodList.ChineseCabbageLeaf).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> Garlic = ITEMS.register("garlic",
            ()-> new GarlicItem(BlockRegistry.Garlic.get(), new Item.Properties().food(FoodList.Garlic).group(DumplingsDelightGroup.DumplingsDelightGroup), true));
    public static final RegistryObject<Item> GarlicClove = ITEMS.register("garlic_clove",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.GarlicClove).group(DumplingsDelightGroup.DumplingsDelightGroup),true));
    public static final RegistryObject<Item> GreenOnion = ITEMS.register("greenonion",
            ()-> new BlockItem(BlockRegistry.GreenOnion.get(), new Item.Properties().food(FoodList.GreenOnion).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> Eggplant = ITEMS.register("eggplant",
            ()-> new Item(new Item.Properties().food(FoodList.Eggplant).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> EggplantSeeds = ITEMS.register("eggplant_seeds",
            ()-> new BlockItem(BlockRegistry.Eggplant.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> GarlicChive = ITEMS.register("garlic_chive",
            ()-> new Item(new Item.Properties().food(FoodList.GarlicChive).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> GarlicChiveSeeds = ITEMS.register("garlic_chive_seeds",
            ()-> new BlockItem(BlockRegistry.GarlicChive.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> Fennel = ITEMS.register("fennel",
            ()-> new Item(new Item.Properties().food(FoodList.Fennel).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> FennelSeeds = ITEMS.register("fennel_seeds",
            ()-> new BlockItem(BlockRegistry.Fennel.get(), new Item.Properties().group(DumplingsDelightGroup.DumplingsDelightGroup)));
    //Dumplings
    public static final RegistryObject<Item> PorkCabbageBoiledDumpling = ITEMS.register("pork_cabbage_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.PorkCabbageBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> PorkCeleryBoiledDumpling = ITEMS.register("pork_celery_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.PorkCeleryBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> PorkKelpBoiledDumpling = ITEMS.register("pork_kelp_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.PorkKelpBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> PorkPotatoBoiledDumpling = ITEMS.register("pork_potato_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.PorkPotatoBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> PorkFennelBoiledDumpling= ITEMS.register("pork_fennel_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.PorkFennelBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> TomatoEggBoiledDumpling = ITEMS.register("tomato_egg_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.TomatoEggBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> BeefTomatoBoiledDumpling = ITEMS.register("beef_tomato_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.BeefTomatoBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> MuttonBoiledDumpling = ITEMS.register("mutton_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.MuttonBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> ChickenMushroomBoiledDumpling = ITEMS.register("chicken_mushroom_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.ChickenMushroomBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> CodBoiledDumpling = ITEMS.register("cod_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.CodBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> SalmonBoiledDumpling = ITEMS.register("salmon_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.SalmonBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> EggplantEggBoiledDumpling = ITEMS.register("eggplant_egg_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.EggplantEggBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> MushroomBoiledDumpling = ITEMS.register("mushroom_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.MushroomBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> FungusBoiledDumpling = ITEMS.register("fungus_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.FungusBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> CalamariBoiledDumpling = ITEMS.register("calamari_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.CalamariBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> GarlicChiveEggBoiledDumpling = ITEMS.register("garlic_chive_egg_boiled_dumpling",
            () -> new Item(new Item.Properties().food(FoodList.GarlicChiveEggBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> DandelionLeafBoiledDumpling = ITEMS.register("dandelion_leaf_boiled_dumpling",
            () -> new ConsumableItem(new Item.Properties().food(FoodList.DandelionLeafBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup), true));
    public static final RegistryObject<Item> PufferfishBoiledDumpling = ITEMS.register("pufferfish_boiled_dumpling",
            () -> new ConsumableItem(new Item.Properties().food(FoodList.PufferfishBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup), true));
    public static final RegistryObject<Item> RabbitMeatBoiledDumpling = ITEMS.register("rabbit_meat_boiled_dumpling",
            () -> new ConsumableItem(new Item.Properties().food(FoodList.RabbitMeatBoiledDumpling).group(DumplingsDelightGroup.DumplingsDelightGroup), true));

    //Wonton
    public static final RegistryObject<Item> PorkCarrotWonton = ITEMS.register("pork_carrot_wonton",
            () -> new ConsumableItem(new Item.Properties().containerItem(Items.BOWL).maxStackSize(16).food(FoodList.PorkCarrotWonton).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> PorkMushroomWonton = ITEMS.register("pork_mushroom_wonton",
            () -> new ConsumableItem(new Item.Properties().containerItem(Items.BOWL).maxStackSize(16).food(FoodList.PorkMushroomWonton).group(DumplingsDelightGroup.DumplingsDelightGroup)));
    public static final RegistryObject<Item> PorkCabbageWonton = ITEMS.register("pork_cabbage_wonton",
            () -> new ConsumableItem(new Item.Properties().containerItem(Items.BOWL).maxStackSize(16).food(FoodList.PorkCabbageWonton).group(DumplingsDelightGroup.DumplingsDelightGroup)));
}
