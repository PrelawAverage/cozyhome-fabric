package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class SinkCounterBlock extends LeveledCauldronBlock {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty LEVEL = Properties.LEVEL_3;

    public static final VoxelShape NORTH = Block.createCuboidShape(0, 0, 0, 16, 12, 14);
    public static final VoxelShape EAST = Block.createCuboidShape(2, 0, 0, 16, 12, 16);
    public static final VoxelShape SOUTH = Block.createCuboidShape(0, 0, 2, 16, 12, 16);
    public static final VoxelShape WEST = Block.createCuboidShape(0, 0, 0, 14, 12, 16);

    private final Biome.Precipitation precipitation;
    /**
     * Constructs a leveled cauldron block.
     *
     * @param precipitation
     * @param behaviorMap
     * @param settings
     */
    public SinkCounterBlock(Biome.Precipitation precipitation, CauldronBehavior.CauldronBehaviorMap behaviorMap, Settings settings) {
        super(precipitation, behaviorMap, settings);
        this.precipitation = precipitation;
        this.setDefaultState(this.stateManager.getDefaultState().with(LEVEL, 1));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (direction) {
            case NORTH:
                return NORTH;
            case EAST:
                return EAST;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            default:
                throw new IllegalStateException("Unexpected value: " + FACING);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEVEL);
    }
}
