package cn.foggyhillside.dumplings_delight.block;

import cn.foggyhillside.dumplings_delight.registry.ItemRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class GreenOnionBlock extends CropsBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0)
    };


    public GreenOnionBlock(AbstractBlock.Properties builder) {
        super(builder);
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemRegistry.GreenOnion.get();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }
}
