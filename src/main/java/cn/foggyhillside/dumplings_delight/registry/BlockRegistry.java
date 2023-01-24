package cn.foggyhillside.dumplings_delight.registry;

import cn.foggyhillside.dumplings_delight.DumplingsDelight;
import cn.foggyhillside.dumplings_delight.block.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DumplingsDelight.MOD_ID);

    public static final RegistryObject<Block> ChineseCabbages = BLOCKS.register("chinese_cabbages",
            ()-> new ChineseCabbageBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> Garlic = BLOCKS.register("garlic",
            ()-> new GarlicBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> GreenOnion = BLOCKS.register("greenonion",
            ()-> new GreenOnionBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> Eggplant = BLOCKS.register("eggplant",
            ()-> new EggplantBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> GarlicChive = BLOCKS.register("garlic_chive",
            ()-> new GarlicChiveBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> Fennel = BLOCKS.register("fennel",
            ()-> new FennelBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> ChineseCabbageCrate = BLOCKS.register("chinese_cabbage_crate",
            ()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GarlicCrate = BLOCKS.register("garlic_crate",
            ()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GreenOnionCrate = BLOCKS.register("greenonion_crate",
            ()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> EggplantCrate = BLOCKS.register("eggplant_crate",
            ()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> GarlicChiveCrate = BLOCKS.register("garlic_chive_crate",
            ()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> FennelCrate = BLOCKS.register("fennel_crate",
            ()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DumplingMedley = BLOCKS.register("dumpling_medley",
            ()-> new DumplingMedleyBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL), true));
}

