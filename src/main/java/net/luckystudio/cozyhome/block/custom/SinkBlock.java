package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
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

public class SinkBlock extends AbstractSinkBlock implements Waterloggable {
    public static final MapCodec<SinkBlock> CODEC = createCodec(SinkBlock::new);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(1, 8, 1, 15, 14, 15));

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    public SinkBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(TRIGGERED, false)
                .with(LEVEL, 0)
                .with(NEXT_LEVEL, 0)
                .with(WATERLOGGED, false)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(WATERLOGGED));
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
        return isWaterLogged(world, pos.offset(state.get(FACING)));
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
