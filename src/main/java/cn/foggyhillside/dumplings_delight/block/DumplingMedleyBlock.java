package cn.foggyhillside.dumplings_delight.block;

import cn.foggyhillside.dumplings_delight.effect.GarlicPotionEffect;
import cn.foggyhillside.dumplings_delight.registry.EffectRegistry;
import cn.foggyhillside.dumplings_delight.registry.ItemRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Collection;

public class DumplingMedleyBlock extends Block {
        public static final DirectionProperty FACING;
        public static final IntegerProperty SERVINGS;
        public final boolean hasLeftovers;
        protected static final VoxelShape[] SHAPES;

        public DumplingMedleyBlock(AbstractBlock.Properties properties, boolean hasLeftovers) {
            super(properties);
            this.hasLeftovers = hasLeftovers;
            this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with(FACING, Direction.NORTH)).with(SERVINGS, 7));
        }

        public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
            return SHAPES[(Integer)state.get(SERVINGS)];
        }

        public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
            return worldIn.isRemote && this.takeServing(worldIn, pos, state, player, handIn).isSuccessOrConsume() ? ActionResultType.SUCCESS : this.takeServing(worldIn, pos, state, player, handIn);
        }


        public boolean garlic(PlayerEntity player) {
            Collection<EffectInstance> effects = player.getActivePotionEffects();
            for (EffectInstance effect : effects) {
                if (effect.getPotion() == EffectRegistry.Garlic.get()) {
                    return true;
                }
            }
            return false;
        }

    private ActionResultType takeServing(IWorld worldIn, BlockPos pos, BlockState state, PlayerEntity player, Hand handIn) {
        int servings = (Integer) state.get(SERVINGS);
        ItemStack heldStack = player.getHeldItem(handIn);
        if (servings == 0) {
            worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.PLAYERS, 0.8F, 0.8F);
            worldIn.destroyBlock(pos, true);
            return ActionResultType.SUCCESS;
        }
        if (heldStack.isEmpty() && player.canEat(false)) {
            if (servings == 7 || servings == 6) {
                player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 50, 0));
                if (!player.abilities.isCreativeMode) {
                    player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 5);
                    if (garlic(player) == true) {
                        player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
                    }
                    player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel() + 5 * 2 * 0.7F);
                }
                worldIn.setBlockState(pos, (BlockState) state.with(SERVINGS, servings - 1), 6);
                return ActionResultType.SUCCESS;
            }
            if (servings == 5 || servings == 4 || servings == 3) {
                player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 50, 0));
                if (!player.abilities.isCreativeMode) {
                    player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 6);
                    if (garlic(player) == true) {
                        player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
                    }
                    player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel() + 6 * 2 * 0.7F);
                }
                worldIn.setBlockState(pos, (BlockState) state.with(SERVINGS, servings - 1), 6);
                return ActionResultType.SUCCESS;
            }
            if (servings == 2|| servings == 1) {
                player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 50, 0));
                if (!player.abilities.isCreativeMode) {
                    player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 8);
                    if (garlic(player) == true) {
                        player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
                    }
                    player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel() + 8 * 2 * 0.9F);
                }
                worldIn.setBlockState(pos, (BlockState) state.with(SERVINGS, servings - 1), 6);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

        public BlockState getStateForPlacement(BlockItemUseContext context) {
            return (BlockState)this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
        }

        public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
            return facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }

        public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
            return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
        }

        protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
            builder.add(new Property[]{FACING, SERVINGS});
        }

        public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
            return (Integer)blockState.get(SERVINGS);
        }

        public int getMaxServings() {
            return 7;
        }

        public boolean hasComparatorInputOverride(BlockState state) {
            return true;
        }

        public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
            return false;
        }

        static {
            FACING = BlockStateProperties.HORIZONTAL_FACING;
            SERVINGS = IntegerProperty.create("servings", 0, 7);
            SHAPES = new VoxelShape[]{
                    Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0),
                    Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0),
                    Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0),
                    Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0),
                    Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0),
                    Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0),
                    Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0),
                    Block.makeCuboidShape(1.0, 0.0, 1.0, 15.0, 3.0, 15.0)
            };
        }
}