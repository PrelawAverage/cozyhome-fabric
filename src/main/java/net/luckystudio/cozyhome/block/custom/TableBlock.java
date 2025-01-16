package net.luckystudio.cozyhome.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TableBlock extends AbstractHorizontalConnectingBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final VoxelShape TABLE_TOP = Block.createCuboidShape(0, 14, 0, 16, 16, 16);
    public static final VoxelShape NORTH_WEST_LEG = Block.createCuboidShape(1, 0, 1, 3, 14, 3);
    public static final VoxelShape NORTH_EAST_LEG = Block.createCuboidShape(13, 0, 1, 15, 14, 3);
    public static final VoxelShape SOUTH_WEST_LEG = Block.createCuboidShape(1, 0, 13, 3, 14, 15);
    public static final VoxelShape SOUTH_EAST_LEG = Block.createCuboidShape(13, 0, 13, 15, 14, 15);

    public TableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = TABLE_TOP;
        shape = VoxelShapes.union(shape, Block.createCuboidShape(
                state.get(WEST) ? 0 : 1,
                12,
                state.get(NORTH) ? 0 : 1,
                state.get(EAST) ? 16 : 15,
                14,
                state.get(SOUTH) ? 16 : 15));

        if (state.get(NORTH) && state.get(WEST) && !state.get(NORTH_WEST) || !state.get(NORTH) && !state.get(WEST)) shape = VoxelShapes.union(shape, NORTH_WEST_LEG);
        if (state.get(NORTH) && state.get(EAST) && !state.get(NORTH_EAST) || !state.get(NORTH) && !state.get(EAST)) shape = VoxelShapes.union(shape, NORTH_EAST_LEG);
        if (state.get(SOUTH) && state.get(WEST) && !state.get(SOUTH_WEST) || !state.get(SOUTH) && !state.get(WEST)) shape = VoxelShapes.union(shape, SOUTH_WEST_LEG);
        if (state.get(SOUTH) && state.get(EAST) && !state.get(SOUTH_EAST) || !state.get(SOUTH) && !state.get(EAST)) shape = VoxelShapes.union(shape, SOUTH_EAST_LEG);

        return shape;
    }
}