package cn.foggyhillside.dumplings_delight;

import cn.foggyhillside.dumplings_delight.registry.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DumplingsDelightGroup extends ItemGroup {
    public static final DumplingsDelightGroup DumplingsDelightGroup = new DumplingsDelightGroup();

    public DumplingsDelightGroup() {
        super("dumplings_delight");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemRegistry.PorkCabbageBoiledDumpling.get());
    }

}
