package cn.foggyhillside.dumplings_delight.block;

import java.util.Random;

import cn.foggyhillside.dumplings_delight.registry.ItemRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.ForgeEventFactory;
import vectorwing.farmersdelight.registry.ModSounds;

public class EggplantBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty AGE;
    private static final int EGGPLANT_BEARING_AGE = 7;
    private static final VoxelShape[] SHAPE_BY_AGE;

    public EggplantBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with(AGE, 0));
    }

    protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos) {
        float f = 1.0F;
        BlockPos floorPos = pos.down();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState blockstate = worldIn.getBlockState(floorPos.add(i, 0, j));
                if (blockstate.canSustainPlant(worldIn, floorPos.add(i, 0, j), Direction.UP, (IPlantable)blockIn)) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(worldIn, floorPos.add(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos northPos = pos.north();
        BlockPos southPos = pos.south();
        BlockPos westPos = pos.west();
        BlockPos eastPos = pos.east();
        boolean isMatchedWestEast = blockIn == worldIn.getBlockState(westPos).getBlock() || blockIn == worldIn.getBlockState(eastPos).getBlock();
        boolean isMatchedNorthSouth = blockIn == worldIn.getBlockState(northPos).getBlock() || blockIn == worldIn.getBlockState(southPos).getBlock();
        if (isMatchedWestEast && isMatchedNorthSouth) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockIn == worldIn.getBlockState(westPos.north()).getBlock() || blockIn == worldIn.getBlockState(eastPos.north()).getBlock() || blockIn == worldIn.getBlockState(eastPos.south()).getBlock() || blockIn == worldIn.getBlockState(westPos.south()).getBlock();
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    public int getMaxAge() {
        return 7;
    }

    protected int getAge(BlockState state) {
        return (Integer)state.get(AGE);
    }

    public BlockState withAge(int age) {
        return (BlockState)this.getDefaultState().with(AGE, age);
    }

    public boolean isMaxAge(BlockState state) {
        return (Integer)state.get(AGE) >= this.getMaxAge();
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.nextInt(worldIn.rand, 2, 5);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[(Integer)state.get(AGE)];
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack((IItemProvider) ItemRegistry.EggplantSeeds.get());
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (worldIn.isAreaLoaded(pos, 1)) {
            if (worldIn.getLightSubtracted(pos, 0) >= 9) {
                int i = this.getAge(state);
                if (i < this.getMaxAge()) {
                    float f = getGrowthChance(this, worldIn, pos);
                    if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0)) {
                        worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                        ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                    }
                }
            }

        }
    }

    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.CROP;
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        int newAge = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int maxAge = this.getMaxAge();
        if (newAge > maxAge) {
            newAge = maxAge;
        }

        worldIn.setBlockState(pos, this.withAge(newAge), 2);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int age = (Integer)state.get(AGE);
        boolean isMature = age == 7;
        if (!isMature && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
            return ActionResultType.PASS;
        } else if (isMature) {
            int j = 1 + worldIn.rand.nextInt(2);
            spawnAsEntity(worldIn, pos, new ItemStack((IItemProvider)ItemRegistry.Eggplant.get(), j));

            worldIn.playSound((PlayerEntity)null, pos, (SoundEvent)ModSounds.ITEM_TOMATO_PICK_FROM_BUSH.get(), SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
            worldIn.setBlockState(pos, (BlockState)state.with(AGE, 5), 2);
            return ActionResultType.SUCCESS;
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return (worldIn.getLightSubtracted(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && super.isValidPosition(state, worldIn, pos);
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof RavagerEntity && ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn)) {
            worldIn.destroyBlock(pos, true, entityIn);
        }

        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    static {
        AGE = BlockStateProperties.AGE_0_7;
        SHAPE_BY_AGE = new VoxelShape[]{Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0), Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0), Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0), Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
    }
}
