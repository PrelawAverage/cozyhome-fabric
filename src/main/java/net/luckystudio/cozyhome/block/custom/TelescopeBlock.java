package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.block_entity.telescope.TelescopeBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class TelescopeBlock extends BlockWithEntity implements Waterloggable {
    public static final MapCodec<TelescopeBlock> CODEC = createCodec(TelescopeBlock::new);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final VoxelShape SHAPE = Block.createCuboidShape(5, 0, 5, 11, 16, 11);

    private static final List<String> GENERAL_FACTS = Arrays.asList(
            "message.cozyhome.telescope.moon_fact_0",
            "message.cozyhome.telescope.moon_fact_1",
            "message.cozyhome.telescope.moon_fact_2"
    );

    private static final Supplier<List<String>> FULL_MOON_FACTS_SUPPLIER = () -> {
        List<String> facts = new ArrayList<>(Arrays.asList(
                "message.cozyhome.telescope.full_moon_facts_0",
                "message.cozyhome.telescope.full_moon_facts_1",
                "message.cozyhome.telescope.full_moon_facts_2",
                "message.cozyhome.telescope.full_moon_facts_3",
                "message.cozyhome.telescope.full_moon_facts_4"
        ));
        facts.add(randomText(GENERAL_FACTS)); // Add one random fact
        return facts;
    };

    // Access random facts dynamically
    private static List<String> getFullMoonFacts() {
        return FULL_MOON_FACTS_SUPPLIER.get();
    }

    private static final List<String> WANING_GIBBOUS_FACTS = Arrays.asList(
            "message.cozyhome.telescope.waning_gibbous_facts_0",
            GENERAL_FACTS.get(Random.createLocal().nextInt(GENERAL_FACTS.size()))
    );

    private static final List<String> LAST_QUARTER_FACTS = Arrays.asList(
            "message.cozyhome.telescope.last_quarter_facts_0",
            GENERAL_FACTS.get(Random.create().nextInt(GENERAL_FACTS.size()))
    );

    private static final List<String> WANING_CRESCENT_FACTS = Arrays.asList(
            "message.cozyhome.telescope.waning_crescent_facts_0",
            GENERAL_FACTS.get(Random.create().nextInt(GENERAL_FACTS.size()))
    );

    private static final List<String> NEW_MOON_FACTS = Arrays.asList(
            "message.cozyhome.telescope.new_moon_facts_0",
            "message.cozyhome.telescope.new_moon_facts_1",
            "message.cozyhome.telescope.new_moon_facts_2",
            GENERAL_FACTS.get(Random.create().nextInt(GENERAL_FACTS.size()))
    );

    private static final List<String> WAXING_CRESCENT_FACTS = Arrays.asList(
            "message.cozyhome.telescope.waxing_crescent_facts_0",
            GENERAL_FACTS.get(Random.create().nextInt(GENERAL_FACTS.size()))
    );

    private static final List<String> FIRST_QUARTER_FACTS = Arrays.asList(
            "message.cozyhome.telescope.first_quarter_facts_0",
            GENERAL_FACTS.get(Random.create().nextInt(GENERAL_FACTS.size()))
    );

    private static final List<String> WAXING_GIBBOUS_FACTS = Arrays.asList(
            "message.cozyhome.telescope.waxing_gibbous_facts_0",
            GENERAL_FACTS.get(Random.create().nextInt(GENERAL_FACTS.size()))
    );

    private static String randomText(List<String> facts) {
        return facts.get(Random.create().nextInt(facts.size()));
    }


    public TelescopeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(WATERLOGGED, Boolean.FALSE)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TelescopeBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, bl);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        boolean isDay = world.isDay();
        if (!world.isClient()) {
            if (isDay) {
                player.sendMessage(Text.translatable("message.cozyhome.telescope.cant_use"), true);
            } else {
                int phase = world.getMoonPhase();
                String moonPhaseSymbol = getMoonSymbol(phase);

                // Use translatable for the moon phase name
                Text moonPhaseName = Text.translatable(getMoonPhaseName(phase));
                Text moonPhaseFact = Text.translatable(getMoonPhaseFact(phase));

                // Combine the moon phase symbol, name, and fact for chat message
                Text message = Text.literal(moonPhaseSymbol)
                        .append(moonPhaseName)
                        .append(": ")
                        .append(moonPhaseFact);

                // Send the message to the player in the chat
                player.sendMessage(message, true);
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    private String getMoonSymbol(int phase) {
        return switch (phase) {
            case 0 -> "§9§l\uD83C\uDF15§r ";
            case 1 -> "§9§l\uD83C\uDF16§r ";
            case 2 -> "§9§l\uD83C\uDF17§r ";
            case 3 -> "§9§l\uD83C\uDF18§r ";
            case 4 -> "§9§l\uD83C\uDF11§r ";
            case 5 -> "§9§l\uD83C\uDF12§r ";
            case 6 -> "§9§l\uD83C\uDF13§r ";
            case 7 -> "§9§l\uD83C\uDF14§r ";
            default -> throw new IllegalStateException("Unexpected value: " + phase + " from " + CozyHome.MOD_ID);
        };
    }

    private String getMoonPhaseFact(int phase) {
        return switch (phase) {
            case 0 -> randomText(getFullMoonFacts());
            case 1 -> randomText(WANING_GIBBOUS_FACTS);
            case 2 -> randomText(LAST_QUARTER_FACTS);
            case 3 -> randomText(WANING_CRESCENT_FACTS);
            case 4 -> randomText(NEW_MOON_FACTS);
            case 5 -> randomText(WAXING_CRESCENT_FACTS);
            case 6 -> randomText(FIRST_QUARTER_FACTS);
            case 7 -> randomText(WAXING_GIBBOUS_FACTS);
            default -> randomText(GENERAL_FACTS);
        };
    }

    private String getMoonPhaseName(int phase) {
        return switch (phase) {
            case 0 -> "message.cozyhome.telescope.full_moon";
            case 1 -> "message.cozyhome.telescope.waning_gibbous";
            case 2 -> "message.cozyhome.telescope.last_quarter";
            case 3 -> "message.cozyhome.telescope.waning_crescent";
            case 4 -> "message.cozyhome.telescope.new_moon";
            case 5 -> "message.cozyhome.telescope.waxing_crescent";
            case 6 -> "message.cozyhome.telescope.first_quarter";
            case 7 -> "message.cozyhome.telescope.waxing_gibbous";
            default -> throw new IllegalStateException("Unexpected value: " + phase);
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(Text.translatable("tooltip.cozyhome.block.telescope").formatted(Formatting.GRAY));
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
