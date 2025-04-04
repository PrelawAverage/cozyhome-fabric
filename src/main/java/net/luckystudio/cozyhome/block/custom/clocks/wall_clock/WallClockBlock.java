package net.luckystudio.cozyhome.block.custom.clocks.wall_clock;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallClockBlock extends BlockWithEntity implements Waterloggable{
    public static final MapCodec<WallClockBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(ClockType.CODEC.fieldOf("kind").forGetter(WallClockBlock::getClockType), createSettingsCodec())
                    .apply(instance, WallClockBlock::new));

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape NORTH_SHAPE = WallClockBlock.createCuboidShape(2, 2, 15, 14, 14, 16);
    private static final VoxelShape EAST_SHAPE = WallClockBlock.createCuboidShape(0, 2, 2, 1, 14, 14);
    private static final VoxelShape SOUTH_SHAPE = WallClockBlock.createCuboidShape(2, 2, 0, 14, 14, 1);
    private static final VoxelShape WEST_SHAPE = WallClockBlock.createCuboidShape(15, 2, 2, 16, 14, 14);

    private final ClockType type;

    @Override
    protected MapCodec<? extends WallClockBlock> getCodec() {
        return CODEC;
    }

    public WallClockBlock(ClockType grandfatherClockType, Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, Boolean.FALSE));
        this.type = grandfatherClockType;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    /**
     * This creates the solid looking hit-box for the entire block
     */
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> null;
        };
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WallClockBlockEntity(pos, state);
    }

    /**
     * This makes sure the tick only runs on the client and only the top part of the block, which in this case is what we need
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntityTypes.WALL_CLOCK_BLOCK_ENTITY, WallClockBlockEntity::tick);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.offset(state.get(FACING).getOpposite())).isSolid();
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction[] directions = ctx.getPlacementDirections();

        for (Direction direction : directions) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction2 = direction.getOpposite();
                blockState = blockState.with(FACING, direction2);
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
                }
            }
        }
        return null;
    }
    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        // Check if the block can remain in place
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }

        // Schedule fluid tick if waterlogged
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        // Return updated state
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient && player instanceof ServerPlayerEntity) {
            long time = world.getTimeOfDay() % 24000; // Get the in-game time (0-23999)
            String formattedTime = formatInGameTime(time); // Convert to readable format
            String symbol = (time >= 0 && time < 12300) || (time > 23850) ? "§6☀§f " : "§9☽§f "; // Night: 0-12300, 23850-24000; Day: 12300-23850
            player.sendMessage(Text.literal(symbol + formattedTime), true);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    /**
     * Formats the in-game time to a readable format (e.g., HH:MM AM/PM).
     */
    private String formatInGameTime(long time) {
        int hours = (int) (time / 1000 + 6) % 24; // Each 1000 ticks = 1 hour, offset by 6 for MC time
        int minutes = (int) (time % 1000 * 60 / 1000); // Convert remainder ticks to minutes
        String period = hours >= 12 ? "PM" : "AM"; // Determine AM or PM
        hours = hours % 12; // Convert to 12-hour format
        if (hours == 0) hours = 12; // Adjust 0 to 12 for 12-hour clock
        return String.format("%02d:%02d %s", hours, minutes, period);
    }

    public enum Type implements ClockType {
        OAK("oak"),
        SPRUCE("spruce"),
        BIRCH("birch"),
        JUNGLE("jungle"),
        ACACIA("acacia"),
        DARK_OAK("dark_oak"),
        MANGROVE("mangrove"),
        CHERRY("cherry"),
        BAMBOO("bamboo"),
        CRIMSON("crimson"),
        WARPED("warped"),
        PRINCESS("princess"),
        IRON("iron"),
        GLASS("iron"),
        UNDEAD("undead"),
        OMINOUS("ominous");

        private final String id;

        Type(final String id) {
            this.id = id;
            TYPES.put(id, this);
        }

        @Override
        public String asString() {
            return this.id;
        }
    }

    public ClockType getClockType() {
        return this.type;
    }

    public interface ClockType extends StringIdentifiable {
        Map<String, ClockType> TYPES = new Object2ObjectArrayMap<>();
        Codec<ClockType> CODEC = Codec.stringResolver(StringIdentifiable::asString, TYPES::get);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}
