package net.luckystudio.cozyhome.block.custom.sinks;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class SinkCounterBlock extends AbstractWaterHoldingBlock {
    public static final MapCodec<SinkCounterBlock> CODEC = createCodec(SinkCounterBlock::new);

    public SinkCounterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(TRIGGERED, false)
                .with(LEVEL, 0)
                .with(NEXT_LEVEL, 0)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends SinkCounterBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    private VoxelShape getShape(BlockState state) {
        Direction direction = state.get(FACING);
        return VoxelShapes.combine(
                VoxelShapes.union(COUNTER_TOP, Block.createCuboidShape(
                        direction == Direction.EAST ? 2 : 0,
                        0,
                        direction == Direction.SOUTH ? 2 : 0,
                        direction == Direction.WEST ? 14 : 16,
                        12,
                        direction == Direction.NORTH ? 14 : 16)),
                Block.createCuboidShape(3, 2, 3, 13, 16, 13),
                BooleanBiFunction.ONLY_FIRST);
    }

    @Override
    public float getWaterLevel(BlockState state) {
        int level = state.get(LEVEL);
        return switch (level) {
            case 1 -> 0.438f;
            case 2 -> 0.688f;
            case 3 -> 0.938f;
            default -> 0.125f;
        };
    }

    @Override
    public boolean hasWaterToPull(BlockState state, World world, BlockPos pos) {
        return isWaterLogged(world, pos.down()) || isWaterLogged(world, pos.offset(state.get(FACING)));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.pulls_water_from").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.behind")));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.below")));
    }
}
