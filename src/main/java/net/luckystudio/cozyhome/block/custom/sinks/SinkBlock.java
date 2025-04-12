package net.luckystudio.cozyhome.block.custom.sinks;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.luckystudio.cozyhome.block.util.interfaces.WaterHoldingBlock;
import net.luckystudio.cozyhome.util.ModScreenTexts;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
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

public class SinkBlock extends AbstractLiquidHoldingBlock implements Waterloggable, WaterHoldingBlock {
    public static final MapCodec<SinkBlock> CODEC = createCodec(SinkBlock::new);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final IntProperty LEVEL = ModProperties.FILLED_LEVEL_0_3;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(1, 8, 1, 15, 14, 15));

    @Override
    protected MapCodec<? extends SinkBlock> getCodec() {
        return CODEC;
    }

    public SinkBlock(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState()
                .with(LEVEL, 0)
                .with(NEXT_LEVEL, 0)
                .with(WATERLOGGED, false)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(WATERLOGGED, LEVEL, FACING));
    }

    @Override
    public float getLiquidLevelHeight(BlockState state) {
        int level = state.get(LEVEL);
        return switch (level) {
            case 1 -> 0.438f;
            case 2 -> 0.688f;
            case 3 -> 0.938f;
            default -> 0.125f;
        };
    }

    @Override
    public List<Direction> sidesToPull(BlockState state) {
        Direction behind = state.get(FACING).getOpposite();
        return List.of(behind);
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.get(LEVEL) == 3;
    }

    @Override
    public int getLevel(BlockState state) {
        return state.get(LEVEL);
    }

    @Override
    public void addLiquid(BlockState state, World world, BlockPos pos, int amount, ContainsBlock contains) {
        if (state.get(CONTAINS) == ContainsBlock.NONE) {
            state = state.with(CONTAINS, contains);
            world.setBlockState(pos, state, 3);
        }
        int level = state.get(LEVEL);
        int newLevel = Math.min(6, level + amount); // clamp to max of 6

        if (level < 6) {
            world.setBlockState(pos, state.with(LEVEL, newLevel), 3);
        }
    }

    @Override
    public void removeLiquid(BlockState state, World world, BlockPos pos, int amount) {
        int level = state.get(LEVEL);
        int newLevel = Math.max(0, level - amount); // ensures level never goes below 0
        ContainsBlock contains = newLevel == 0 ? ContainsBlock.NONE : state.get(CONTAINS);
        world.setBlockState(pos, state.with(LEVEL, newLevel).with(CONTAINS, contains), 3);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.combine(SHAPE, Block.createCuboidShape(3, 10, 3, 13, 16, 13), BooleanBiFunction.ONLY_FIRST);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.pulls_water_from").formatted(Formatting.GRAY));
        tooltip.add(ModScreenTexts.entry().append(Text.translatable("tooltip.cozyhome.behind")));
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}