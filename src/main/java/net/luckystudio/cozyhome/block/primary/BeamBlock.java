package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.EnumMap;
import java.util.Map;

public class BeamBlock extends ConnectingBlock {
    public static final DirectionProperty FACING = Properties.FACING;
    private static final Direction[] FACINGS = Direction.values();
    protected final VoxelShape[] facingsToShape;
    @Override
    public MapCodec<BeamBlock> getCodec() {
        return null;
    }

    public BeamBlock(float radius, Settings settings) {
        super(radius, settings);
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(FACING, Direction.NORTH)
                        .with(NORTH, false)
                        .with(EAST, false)
                        .with(SOUTH, false)
                        .with(WEST, false)
                        .with(UP, false)
                        .with(DOWN, false)
        );
        this.facingsToShape = this.generateFacingsToShapeMap(radius);
    }

    private VoxelShape[] generateFacingsToShapeMap(float radius) {
        float f = 0.5F - radius;
        float g = 0.5F + radius;
        VoxelShape voxelShape = Block.createCuboidShape(
                f * 16.0F, f * 16.0F, f * 16.0F, g * 16.0F, g * 16.0F, g * 16.0F
        );
        VoxelShape[] voxelShapes = new VoxelShape[FACINGS.length];

        for (int i = 0; i < FACINGS.length; i++) {
            Direction direction = FACINGS[i];
            voxelShapes[i] = VoxelShapes.cuboid(
                    0.5 + Math.min(-radius, (double)direction.getOffsetX() * 0.5),
                    0.5 + Math.min(-radius, (double)direction.getOffsetY() * 0.5),
                    0.5 + Math.min(-radius, (double)direction.getOffsetZ() * 0.5),
                    0.5 + Math.max(radius, (double)direction.getOffsetX() * 0.5),
                    0.5 + Math.max(radius, (double)direction.getOffsetY() * 0.5),
                    0.5 + Math.max(radius, (double)direction.getOffsetZ() * 0.5)
            );
        }

        VoxelShape[] voxelShapes2 = new VoxelShape[64];

        for (int j = 0; j < 64; j++) {
            VoxelShape voxelShape2 = voxelShape;

            for (int k = 0; k < FACINGS.length; k++) {
                if ((j & 1 << k) != 0) {
                    voxelShape2 = VoxelShapes.union(voxelShape2, voxelShapes[k]);
                }
            }

            voxelShapes2[j] = voxelShape2;
        }

        return voxelShapes2;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.facingsToShape[this.getConnectionMask(state)];
    }

    protected int getConnectionMask(BlockState state) {
        int i = 0;

        for (int j = 0; j < FACINGS.length; j++) {
            if ((Boolean)state.get(FACING_PROPERTIES.get(FACINGS[j]))) {
                i |= 1 << j;
            }
        }

        return i;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = this.getDefaultState();

        state = state.withIfExists(FACING, ctx.getSide());

        return updateConnectionProperties(ctx.getWorld(), ctx.getBlockPos(), state);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return updateConnectionProperties(world, pos, state);
    }

    public static BlockState updateConnectionProperties(WorldAccess world, BlockPos pos, BlockState state) {
        Map<Direction, BlockState> blockStates = getBlockDirections(world, pos);

        for (Direction direction : Direction.values()) {
            state = state.withIfExists(FACING_PROPERTIES.get(direction), isConnectableFace(state, blockStates.get(direction), world, pos, direction));
        }

        return state;
    }

    private static boolean isConnectableFace(BlockState originBlock, BlockState targetBlock, WorldAccess world, BlockPos pos, Direction currentDirection) {
        if (targetBlock.isSideSolidFullSquare(world, pos, currentDirection.getOpposite())) return Boolean.TRUE;
    
        if (isBeamBlock(targetBlock.getBlock())
                && isContinuousBeamFace(originBlock, currentDirection)) return Boolean.TRUE;

        if (isBeamBlock(targetBlock.getBlock())
                && !isContinuousBeamFace(originBlock, currentDirection)
                && !isParallel(originBlock, targetBlock)) return Boolean.TRUE;

        return Boolean.FALSE;
    }

    private static boolean isContinuousBeamFace(BlockState originBlock, Direction currentDirection) {
        Direction blockFacing = originBlock.get(FACING);
        return (currentDirection == blockFacing || currentDirection == blockFacing.getOpposite());
    }

    private static boolean isParallel(BlockState originBlock, BlockState targetBlock) {
        Direction blockFacing = originBlock.get(FACING);
        Direction targetFacing = targetBlock.get(FACING);
        return (blockFacing == targetFacing || blockFacing == targetFacing.getOpposite());
    }

    private static boolean isBeamBlock(Block block) {
        return block instanceof BeamBlock;
    }

    // Testing if the beam can be straight
    public static Map<Direction, BlockState> getBlockDirections(WorldAccess world, BlockPos pos) {
        Map<Direction, BlockState> blockStates = new EnumMap<>(Direction.class);

        for (Direction direction : Direction.values()) {
            blockStates.put(direction, world.getBlockState(pos.offset(direction)));
        }

        return blockStates;
    }
}
