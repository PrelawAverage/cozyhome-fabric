package net.luckystudio.cozyhome.block.custom.clocks.grandfather_clock;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.luckystudio.cozyhome.block.util.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.TripleTallBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class GrandfatherClockBlock extends BlockWithEntity implements Waterloggable {
    public static final MapCodec<GrandfatherClockBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(GrandfatherClockType.CODEC.fieldOf("kind").forGetter(GrandfatherClockBlock::getGrandfatherClockType), createSettingsCodec())
                    .apply(instance, GrandfatherClockBlock::new));

    public static final EnumProperty<TripleTallBlock> TRIPLE_TALL_BLOCK = ModProperties.TRIPLE_TALL_BLOCK;
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;
    public static final IntProperty ROTATION = Properties.ROTATION;

    private static final VoxelShape BOTTOM_BOTTOM_PIECE = GrandfatherClockBlock.createCuboidShape(1, 0, 1, 15, 2, 15);
    private static final VoxelShape BOTTOM_MIDDLE_PIECE = GrandfatherClockBlock.createCuboidShape(2, 2, 2, 14, 30, 14);
    private static final VoxelShape BOTTOM_TOP_PIECE = GrandfatherClockBlock.createCuboidShape(1, 30, 1, 15, 44, 15);
    private static final VoxelShape BOTTOM_SHAPE = VoxelShapes.union(BOTTOM_BOTTOM_PIECE, BOTTOM_MIDDLE_PIECE, BOTTOM_TOP_PIECE);
    private static final VoxelShape MIDDLE_BOTTOM_PIECE = GrandfatherClockBlock.createCuboidShape(1, -16, 1, 15, -14, 15);
    private static final VoxelShape MIDDLE_MIDDLE_PIECE = GrandfatherClockBlock.createCuboidShape(2, -14, 2, 14, 14, 14);
    private static final VoxelShape MIDDLE_TOP_PIECE = GrandfatherClockBlock.createCuboidShape(1, 14, 1, 15, 28, 15);
    private static final VoxelShape MIDDLE_SHAPE = VoxelShapes.union(MIDDLE_BOTTOM_PIECE, MIDDLE_MIDDLE_PIECE, MIDDLE_TOP_PIECE);
    private static final VoxelShape TOP_BOTTOM_PIECE = GrandfatherClockBlock.createCuboidShape(1, -32, 1, 15, -30, 15);
    private static final VoxelShape TOP_MIDDLE_PIECE = GrandfatherClockBlock.createCuboidShape(2, -30, 2, 14, -2, 14);
    private static final VoxelShape TOP_TOP_PIECE = GrandfatherClockBlock.createCuboidShape(1, -2, 1, 15, 12, 15);
    private static final VoxelShape TOP_SHAPE = VoxelShapes.union(TOP_BOTTOM_PIECE, TOP_MIDDLE_PIECE, TOP_TOP_PIECE);

    private final GrandfatherClockType type;

    @Override
    protected MapCodec<? extends GrandfatherClockBlock> getCodec() {
        return CODEC;
    }

    public GrandfatherClockBlock(GrandfatherClockType grandfatherClockType, Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(TRIPLE_TALL_BLOCK, TripleTallBlock.BOTTOM)
                .with(TRIGGERED, false)
                .with(ROTATION, 0));
        this.type = grandfatherClockType;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TRIPLE_TALL_BLOCK, TRIGGERED, ROTATION);
    }

    /**
     * This creates the solid looking hitbox for the entire block
     */
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(TRIPLE_TALL_BLOCK)) {
            case TOP -> TOP_SHAPE;
            case MIDDLE -> MIDDLE_SHAPE;
            case BOTTOM -> BOTTOM_SHAPE;
        };
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GrandfatherClockBlockEntity(pos, state);
    }

    /**
     * This makes sure the tick only runs on the client and only the top part of the block, which in this case is what we need
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return (state.get(TRIPLE_TALL_BLOCK) == TripleTallBlock.TOP) ? validateTicker(type, ModBlockEntityTypes.GRANDFATHER_CLOCK_BLOCK_ENTITY, GrandfatherClockBlockEntity::tick) : null;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        TripleTallBlock currentPart = state.get(TRIPLE_TALL_BLOCK); // Get the part of the block (TOP, MIDDLE, or BOTTOM)
        if (direction.getAxis() != Direction.Axis.Y) { // Check if the direction is along the Y-axis (up or down)
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
        switch (currentPart) { // Handle the logic based on which part of the block this is
            case TOP:
                if (direction == Direction.DOWN) { // Ensure the middle part is below and the block is placeable
                    BlockState belowState = world.getBlockState(pos.down());
                    return (!belowState.isOf(this) || belowState.get(TRIPLE_TALL_BLOCK) != TripleTallBlock.MIDDLE) ?
                            Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos); // Break the block if the middle part is missing
                }
                break;
            case MIDDLE:
                if (direction == Direction.UP) { // Ensure the top part is above and the bottom part is below
                    BlockState aboveState = world.getBlockState(pos.up());
                    return (!aboveState.isOf(this) || aboveState.get(TRIPLE_TALL_BLOCK) != TripleTallBlock.TOP) ?
                    Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos); // Break the block if the middle part is missing
                } else if (direction == Direction.DOWN) {
                    BlockState belowState = world.getBlockState(pos.down());
                    return  (!belowState.isOf(this) || belowState.get(TRIPLE_TALL_BLOCK) != TripleTallBlock.BOTTOM) ?
                    Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos); // Break the block if the middle part is missing
                }
                break;
            case BOTTOM:
                if (direction == Direction.UP) { // Ensure the middle part is above
                    BlockState aboveState = world.getBlockState(pos.up());
                    if (!aboveState.isOf(this) || aboveState.get(TRIPLE_TALL_BLOCK) != TripleTallBlock.MIDDLE) {
                        world.breakBlock(pos, true);
                        return Blocks.AIR.getDefaultState();  // Break the block if the middle part is missing
                    }
                }
                break;
            default:
                break;
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        return blockPos.getY() < world.getTopY() - 2 && world.getBlockState(blockPos.up()).canReplace(ctx) && world.getBlockState(blockPos.up(2)).canReplace(ctx) ? super.getPlacementState(ctx)
                .with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw()))
                .with(TRIPLE_TALL_BLOCK, TripleTallBlock.BOTTOM) : null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), withWaterloggedState(world, pos.up(), this.getDefaultState()
                .with(TRIPLE_TALL_BLOCK, TripleTallBlock.MIDDLE)
                .with(ROTATION, state.get(ROTATION))), Block.NOTIFY_ALL);
        world.setBlockState(pos.up(2), withWaterloggedState(world, pos.up(2), this.getDefaultState()
                .with(TRIPLE_TALL_BLOCK, TripleTallBlock.TOP)
                .with(ROTATION, state.get(ROTATION))), Block.NOTIFY_ALL);
    }

    public static BlockState withWaterloggedState(WorldView world, BlockPos pos, BlockState state) {
        return state.contains(Properties.WATERLOGGED) ? state.with(Properties.WATERLOGGED, world.isWater(pos)) : state;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            if (player.isCreative()) {
                onBreakInCreative(world, pos, state, player);
            } else {
                dropStacks(state, world, pos, null, player, player.getMainHandStack());
            }
        }
        return super.onBreak(world, pos, state, player);
    }

    /**
     * Destroys a bottom half of a tall double block (such as a plant or a door)
     * without dropping an item when broken in creative.
     *
     * @see Block#onBreak(World, BlockPos, BlockState, PlayerEntity)
     */
    protected static void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        TripleTallBlock tripleTallBlock = state.get(TRIPLE_TALL_BLOCK);
        BlockPos blockPosBelow = pos.down();
        BlockState blockStateBelow = world.getBlockState(blockPosBelow);
        BlockPos blockPosFarBelow = pos.down(2);
        BlockState blockStateFarBelow = world.getBlockState(blockPosFarBelow);
        if (tripleTallBlock == TripleTallBlock.TOP) {
            if (blockStateBelow.isOf(state.getBlock()) && blockStateBelow.get(TRIPLE_TALL_BLOCK) == TripleTallBlock.BOTTOM) {
                BlockState blockState2 = blockStateBelow.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                world.setBlockState(blockPosBelow, blockState2, Block.NOTIFY_ALL | Block.SKIP_DROPS);
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPosBelow, Block.getRawIdFromState(blockStateBelow));
            }
            if (blockStateFarBelow.isOf(state.getBlock()) && blockStateFarBelow.get(TRIPLE_TALL_BLOCK) == TripleTallBlock.BOTTOM) {
                BlockState blockState3 = blockStateFarBelow.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                world.setBlockState(blockPosFarBelow, blockState3, Block.NOTIFY_ALL | Block.SKIP_DROPS);
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPosFarBelow, Block.getRawIdFromState(blockStateFarBelow));
            }
        } else if (tripleTallBlock == TripleTallBlock.MIDDLE) {
            if (blockStateBelow.isOf(state.getBlock()) && blockStateBelow.get(TRIPLE_TALL_BLOCK) == TripleTallBlock.BOTTOM) {
                BlockState blockState2 = blockStateBelow.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                world.setBlockState(blockPosBelow, blockState2, Block.NOTIFY_ALL | Block.SKIP_DROPS);
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPosBelow, Block.getRawIdFromState(blockStateBelow));
            }
        }
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, Blocks.AIR.getDefaultState(), blockEntity, tool);
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

    public enum Type implements GrandfatherClockType {
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

    public GrandfatherClockType getGrandfatherClockType() {
        return this.type;
    }

    public interface GrandfatherClockType extends StringIdentifiable {
        Map<String, GrandfatherClockType> TYPES = new Object2ObjectArrayMap<>();
        Codec<GrandfatherClockType> CODEC = Codec.stringResolver(StringIdentifiable::asString, TYPES::get);
    }
}
