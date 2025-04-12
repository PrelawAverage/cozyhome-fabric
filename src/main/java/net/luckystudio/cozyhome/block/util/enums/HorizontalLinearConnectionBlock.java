package net.luckystudio.cozyhome.block.util.enums;

import net.luckystudio.cozyhome.block.util.interfaces.ConnectingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public enum HorizontalLinearConnectionBlock implements StringIdentifiable {
    SINGLE("single"),
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right");

    private final String name;

    private HorizontalLinearConnectionBlock(final String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public static HorizontalLinearConnectionBlock setHorizontalConnection(BlockState state, WorldAccess world, BlockPos pos) {
        Direction facing = state.get(HorizontalFacingBlock.FACING);

        Direction left = facing.rotateYClockwise();
        Direction right = facing.rotateYCounterclockwise();

        BlockState stateLeft = world.getBlockState(pos.offset(left));
        BlockState stateRight = world.getBlockState(pos.offset(right));

        if (state.getBlock() instanceof ConnectingBlock connectingBlock) {
            if (connectingBlock.isMatchingBlock(stateLeft) && connectingBlock.isMatchingBlock(stateRight)) {
                return HorizontalLinearConnectionBlock.MIDDLE;
            } else if (connectingBlock.isMatchingBlock(stateLeft)) {
                return HorizontalLinearConnectionBlock.LEFT;
            } else if (connectingBlock.isMatchingBlock(stateRight)) {
                return HorizontalLinearConnectionBlock.RIGHT;
            }
        }
        return HorizontalLinearConnectionBlock.SINGLE;
    }
}
