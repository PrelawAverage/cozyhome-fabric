package net.luckystudio.cozyhome.block.custom;

import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.StackableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.Thickness;
import net.minecraft.component.Component;
import net.minecraft.datafixer.fix.ChunkPalettedStorageFix;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;
import java.util.Stack;

public class PlankedWallBlock extends PillarBlock {
    public static final EnumProperty<StackableBlock> STACKABLE_BLOCK = ModProperties.STACKABLE_BLOCK;
    public PlankedWallBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, STACKABLE_BLOCK);
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

        state = state.with(STACKABLE_BLOCK, stackableBlockType);

        world.setBlockState(pos, state, 3);
    }

    public BlockPos getRelativeAxisNeighborPosition(Direction.Axis axis, BlockPos pos, StackableBlock stackableBlock) {
        if (axis == Direction.Axis.X && stackableBlock == StackableBlock.HEAD) {return pos.east();}
        else if (axis == Direction.Axis.X && stackableBlock == StackableBlock.TAIL) {return pos.west();}
        else if (axis == Direction.Axis.Y && stackableBlock == StackableBlock.HEAD) {return pos.up();}
        else if (axis == Direction.Axis.Y && stackableBlock == StackableBlock.TAIL) {return pos.down();}
        else if (axis == Direction.Axis.Z && stackableBlock == StackableBlock.HEAD) {return pos.south();}
        else if (axis == Direction.Axis.Z && stackableBlock == StackableBlock.TAIL) {return pos.north();}
        else {return null;}
    }

    public StackableBlock getStackableBlockType(BlockState state, BlockState relativeHeadBlock, BlockState relativeBlockTail) {
        boolean isHeadBlockConnected = relativeHeadBlock.isOf(state.getBlock()) && relativeHeadBlock.get(AXIS) == state.get(AXIS);
        boolean isTailBlockConnected = relativeBlockTail.isOf(state.getBlock()) && relativeBlockTail.get(AXIS) == state.get(AXIS);

        if (isHeadBlockConnected && isTailBlockConnected) return StackableBlock.MIDDLE;
        if (isHeadBlockConnected) return StackableBlock.TAIL;
        if (isTailBlockConnected) return StackableBlock.HEAD;
        return StackableBlock.SINGLE;
    }
}
