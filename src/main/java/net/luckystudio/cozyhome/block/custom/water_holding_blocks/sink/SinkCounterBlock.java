package net.luckystudio.cozyhome.block.custom.water_holding_blocks.sink;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.interfaces.WaterHoldingBlock;
import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SinkCounterBlock extends AbstractSinkBlock implements WaterHoldingBlock {
    public static final MapCodec<SinkCounterBlock> CODEC = createCodec(SinkCounterBlock::new);

    public static final VoxelShape COUNTER_TOP = VoxelShapes.cuboid(0, 12, 0, 16, 16, 16);

    @Override
    protected MapCodec<? extends SinkCounterBlock> getCodec() {
        return CODEC;
    }

    public SinkCounterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SinkBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    private VoxelShape getShape(BlockState state) {
        Direction direction = state.get(FACING);

        VoxelShape topShape = Block.createCuboidShape(0, 12, 0, 16, 16, 16);

        VoxelShape bottom = switch (direction) {
            case NORTH -> Block.createCuboidShape(0, 0, 0, 16, 12, 14);
            case SOUTH -> Block.createCuboidShape(0, 0, 2, 16, 12, 16);
            case EAST -> Block.createCuboidShape(2, 0, 0, 16, 12, 16);
            case WEST -> Block.createCuboidShape(0, 0, 0, 14, 12, 16);
            default -> VoxelShapes.empty();
        };

        VoxelShape baseShape = VoxelShapes.union(
                topShape,
                bottom
        );

        VoxelShape holeShape = Block.createCuboidShape(3, 2, 3, 13, 16, 13);
        return VoxelShapes.combine(baseShape, holeShape, BooleanBiFunction.ONLY_FIRST);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("tooltip.cozyhome.pulls_water_from").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.behind")));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.below")));
    }

    @Override
    public List<Direction> getDirectionsToPull(BlockState state) {
        List<Direction> directions = new ArrayList<>(super.getDirectionsToPull(state));
        directions.add(Direction.DOWN);
        return directions;
    }
}
