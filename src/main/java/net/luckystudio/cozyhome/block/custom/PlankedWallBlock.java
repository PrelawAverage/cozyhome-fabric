package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.blockstates.LinearConnectionBlock;
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
    public static final MapCodec<PlankedWallBlock> CODEC = createCodec(PlankedWallBlock::new);
    public static final EnumProperty<LinearConnectionBlock> STACKABLE_BLOCK = ModProperties.LINEAR_CONNECTION_BLOCK;
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
                .with(STACKABLE_BLOCK, LinearConnectionBlock.SINGLE)
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

        BlockPos relativeHeadBlockPos = getRelativeAxisNeighborPosition(axis, pos, LinearConnectionBlock.HEAD);
        BlockPos relativeTailBlockPos = getRelativeAxisNeighborPosition(axis, pos, LinearConnectionBlock.TAIL);

        BlockState relativeHeadBlock = world.getBlockState(relativeHeadBlockPos);
        BlockState relativeTailBlock = world.getBlockState(relativeTailBlockPos);

        LinearConnectionBlock LinearConnectionBlockType = getLinearConnectionBlockType(state, relativeHeadBlock, relativeTailBlock);

        BlockState updatedState = state.with(STACKABLE_BLOCK, LinearConnectionBlockType);

        world.setBlockState(pos, updatedState, 3);
    }

    private BlockPos getRelativeAxisNeighborPosition(Direction.Axis axis, BlockPos pos, LinearConnectionBlock linearConnectionBlock) {
        return switch (axis) {
            case X -> linearConnectionBlock == LinearConnectionBlock.HEAD ? pos.east() :
                    linearConnectionBlock == LinearConnectionBlock.TAIL ? pos.west() : null;
            case Y -> linearConnectionBlock == LinearConnectionBlock.HEAD ? pos.up() :
                    linearConnectionBlock == LinearConnectionBlock.TAIL ? pos.down() : null;
            case Z -> linearConnectionBlock == LinearConnectionBlock.HEAD ? pos.north() :
                    linearConnectionBlock == LinearConnectionBlock.TAIL ? pos.south() : null;
        };
    }

    private LinearConnectionBlock getLinearConnectionBlockType(BlockState state, BlockState relativeHeadBlock, BlockState relativeBlockTail) {
        boolean isHeadBlockConnected = relativeHeadBlock.isOf(state.getBlock()) && relativeHeadBlock.get(AXIS) == state.get(AXIS);
        boolean isTailBlockConnected = relativeBlockTail.isOf(state.getBlock()) && relativeBlockTail.get(AXIS) == state.get(AXIS);

        if (isHeadBlockConnected && isTailBlockConnected) return LinearConnectionBlock.MIDDLE;
        if (isHeadBlockConnected) return LinearConnectionBlock.TAIL;
        if (isTailBlockConnected) return LinearConnectionBlock.HEAD;
        return LinearConnectionBlock.SINGLE;
    }
}
