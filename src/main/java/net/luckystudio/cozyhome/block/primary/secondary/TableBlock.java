package net.luckystudio.cozyhome.block.primary.secondary;

import net.luckystudio.cozyhome.block.primary.AbstractAllSidesConnectingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TableBlock extends AbstractAllSidesConnectingBlock {

    public static final VoxelShape TOP_PIECE = Block.createCuboidShape(0, 14, 0, 16, 16, 16);
    public static final VoxelShape BOTTOM_PIECE = Block.createCuboidShape(0, 4, 0, 16, 5, 16);
    public static final VoxelShape NORTH_WEST_LEG = Block.createCuboidShape(1, 0, 1, 3, 14, 3);
    public static final VoxelShape NORTH_EAST_LEG = Block.createCuboidShape(13, 0, 1, 15, 14, 3);
    public static final VoxelShape SOUTH_WEST_LEG = Block.createCuboidShape(1, 0, 13, 3, 14, 15);
    public static final VoxelShape SOUTH_EAST_LEG = Block.createCuboidShape(13, 0, 13, 15, 14, 15);

    public static final VoxelShape SINGLE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_EAST_LEG, NORTH_WEST_LEG, SOUTH_EAST_LEG, SOUTH_WEST_LEG);
    public static final VoxelShape NORTH_SIDE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_EAST_LEG, NORTH_WEST_LEG);
    public static final VoxelShape SOUTH_SIDE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, SOUTH_EAST_LEG, SOUTH_WEST_LEG);
    public static final VoxelShape EAST_SIDE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_EAST_LEG, SOUTH_EAST_LEG);
    public static final VoxelShape WEST_SIDE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_WEST_LEG, SOUTH_WEST_LEG);

    public static final VoxelShape NORTH_EAST_CORNER = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_EAST_LEG);
    public static final VoxelShape NORTH_WEST_CORNER = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_WEST_LEG);
    public static final VoxelShape SOUTH_EAST_CORNER = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, SOUTH_EAST_LEG);
    public static final VoxelShape SOUTH_WEST_CORNER = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, SOUTH_WEST_LEG);

    public static final VoxelShape NORTH_EAST_INNER_CORNER = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_EAST_LEG, SOUTH_WEST_LEG);
    public static final VoxelShape NORTH_WEST_INNER_CORNER = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_WEST_LEG, SOUTH_EAST_LEG);
    public static final VoxelShape SOUTH_EAST_INNER_CORNER = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, SOUTH_EAST_LEG, NORTH_WEST_LEG);
    public static final VoxelShape SOUTH_WEST_INNER_CORNER = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, SOUTH_WEST_LEG, NORTH_EAST_LEG);

    public static final VoxelShape NORTH_SOUTH_EAST_SIDE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_EAST_LEG, SOUTH_EAST_LEG);
    public static final VoxelShape NORTH_EAST_WEST_SIDE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_EAST_LEG, NORTH_WEST_LEG);
    public static final VoxelShape SOUTH_EAST_WEST_SIDE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, SOUTH_EAST_LEG, SOUTH_WEST_LEG);
    public static final VoxelShape NORTH_SOUTH_WEST_SIDE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE, NORTH_EAST_LEG, SOUTH_EAST_LEG);

    public static final VoxelShape LEGLESS = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE);

    public TableBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        boolean north = state.get(NORTH);
        boolean east = state.get(EAST);
        boolean south = state.get(SOUTH);
        boolean west = state.get(WEST);
        boolean northEast = state.get(NORTH_EAST);
        boolean northWest = state.get(NORTH_WEST);
        boolean southEast = state.get(SOUTH_EAST);
        boolean southWest = state.get(SOUTH_WEST);
        return SINGLE;
    }
}
