package cn.foggyhillside.dumplings_delight.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.setup.Configuration;
import vectorwing.farmersdelight.utils.TextUtils;

import javax.annotation.Nullable;
import java.util.List;

public class GarlicItem extends BlockItem {
    private final boolean hasFoodEffectTooltip;
    private final boolean hasCustomTooltip;

    public GarlicItem(Block block, Item.Properties properties) {
        super(block, properties);
        this.hasFoodEffectTooltip = false;
        this.hasCustomTooltip = false;
    }

    public GarlicItem(Block block, Item.Properties properties, boolean hasFoodEffectTooltip) {
        super(block, properties);
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        this.hasCustomTooltip = false;
    }

    public GarlicItem(Block block, Item.Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(block, properties);
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        this.hasCustomTooltip = hasCustomTooltip;
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity consumer) {
        if (!worldIn.isRemote) {
            this.affectConsumer(stack, worldIn, consumer);
        }

        ItemStack containerStack = stack.getContainerItem();
        PlayerEntity player;
        if (stack.isFood()) {
            super.onItemUseFinish(stack, worldIn, consumer);
        } else {
            player = consumer instanceof PlayerEntity ? (PlayerEntity)consumer : null;
            if (player instanceof ServerPlayerEntity) {
                CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)player, stack);
            }

            if (player != null) {
                player.addStat(Stats.ITEM_USED.get(this));
                if (!player.abilities.isCreativeMode) {
                    stack.shrink(1);
                }
            }
        }

        if (stack.isEmpty()) {
            return containerStack;
        } else {
            if (consumer instanceof PlayerEntity && !((PlayerEntity)consumer).abilities.isCreativeMode) {
                player = (PlayerEntity)consumer;
                if (!player.inventory.addItemStackToInventory(containerStack)) {
                    player.dropItem(containerStack, false);
                }
            }

            return stack;
        }
    }

    public void affectConsumer(ItemStack stack, World worldIn, LivingEntity consumer) {
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if ((Boolean) Configuration.FOOD_EFFECT_TOOLTIP.get()) {
            if (this.hasCustomTooltip) {
                IFormattableTextComponent textEmpty = TextUtils.getTranslation("tooltip." + this, new Object[0]);
                tooltip.add(textEmpty.mergeStyle(TextFormatting.BLUE));
            }

            if (this.hasFoodEffectTooltip) {
                TextUtils.addFoodEffectTooltip(stack, tooltip, 1.0F);
            }
        }

    }
}
