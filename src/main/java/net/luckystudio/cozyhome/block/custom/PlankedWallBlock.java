package net.luckystudio.cozyhome.block.custom;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.StackableBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class PlankedWallBlock extends PillarBlock {
    public static final EnumProperty<StackableBlock> STACKABLE_BLOCK = ModProperties.STACKABLE_BLOCK;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public PlankedWallBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(STACKABLE_BLOCK, StackableBlock.SINGLE)
                .with(AXIS, ctx.getSide().getAxis());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, STACKABLE_BLOCK, AXIS);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.cozyhome.block.planked_wall"));
        super.appendTooltip(stack, context, tooltip, options);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if(world.isClient) return;

        Direction.Axis axis = state.get(AXIS);

        BlockPos relativeHeadBlockPos = getRelativeAxisNeighborPosition(axis, pos, StackableBlock.HEAD);
        BlockPos relativeTailBlockPos = getRelativeAxisNeighborPosition(axis, pos, StackableBlock.TAIL);

        BlockState relativeHeadBlock = world.getBlockState(relativeHeadBlockPos);
        BlockState relativeTailBlock = world.getBlockState(relativeTailBlockPos);

        StackableBlock stackableBlockType = getStackableBlockType(state, relativeHeadBlock, relativeTailBlock);

        BlockState updatedState = state.with(STACKABLE_BLOCK, stackableBlockType);

        world.setBlockState(pos, updatedState, 3);
    }

    private BlockPos getRelativeAxisNeighborPosition(Direction.Axis axis, BlockPos pos, StackableBlock stackableBlock) {
        return switch (axis) {
            case X -> stackableBlock == StackableBlock.HEAD ? pos.east() :
                    stackableBlock == StackableBlock.TAIL ? pos.west() : null;
            case Y -> stackableBlock == StackableBlock.HEAD ? pos.up() :
                    stackableBlock == StackableBlock.TAIL ? pos.down() : null;
            case Z -> stackableBlock == StackableBlock.HEAD ? pos.north() :
                    stackableBlock == StackableBlock.TAIL ? pos.south() : null;
        };
    }

    private StackableBlock getStackableBlockType(BlockState state, BlockState relativeHeadBlock, BlockState relativeBlockTail) {
        boolean isHeadBlockConnected = relativeHeadBlock.isOf(state.getBlock()) && relativeHeadBlock.get(AXIS) == state.get(AXIS);
        boolean isTailBlockConnected = relativeBlockTail.isOf(state.getBlock()) && relativeBlockTail.get(AXIS) == state.get(AXIS);

        if (isHeadBlockConnected && isTailBlockConnected) return StackableBlock.MIDDLE;
        if (isHeadBlockConnected) return StackableBlock.TAIL;
        if (isTailBlockConnected) return StackableBlock.HEAD;
        return StackableBlock.SINGLE;
    }
}
