package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.ModBlockEntityTypes;
import net.luckystudio.cozyhome.block.entity.ChimneyBlockEntity;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.enums.VerticalLinearConnectionBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class ChimneyBlock extends BlockWithEntity {
    public static final MapCodec<ChimneyBlock> CODEC = createCodec(ChimneyBlock::new);
    public static final EnumProperty<VerticalLinearConnectionBlock> STACKABLE_BLOCK = ModProperties.VERTICAL_CONNECTION;
    public static final BooleanProperty LIT = Properties.LIT;

    public static final VoxelShape SINGLE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    public static final VoxelShape TOP = VoxelShapes.union(
            Block.createCuboidShape(0,4,0,16,16,16),
            Block.createCuboidShape(2, 0, 2, 14, 4, 14));
    public static final VoxelShape MIDDLE = Block.createCuboidShape(2, 0, 2, 14, 16, 14);
    public static final VoxelShape BOTTOM = VoxelShapes.union(
            Block.createCuboidShape(2,4,2,14,16,14),
            Block.createCuboidShape(0, 0, 0, 16, 4, 16));

    @Override
    public MapCodec<? extends ChimneyBlock> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChimneyBlockEntity(pos, state);
    }

    public ChimneyBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(STACKABLE_BLOCK, VerticalLinearConnectionBlock.SINGLE)
                .with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STACKABLE_BLOCK, LIT);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(STACKABLE_BLOCK)) {
            case SINGLE -> SINGLE;
            case HEAD -> TOP;
            case MIDDLE -> MIDDLE;
            case TAIL -> BOTTOM;
        };
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(STACKABLE_BLOCK, VerticalLinearConnectionBlock.SINGLE)
                .with(LIT, isLIT(ctx.getWorld(), ctx.getBlockPos()));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockPos relativeHeadBlockPos = pos.up();
        BlockPos relativeTailBlockPos = pos.down();

        BlockState relativeHeadBlock = world.getBlockState(relativeHeadBlockPos);
        BlockState relativeTailBlock = world.getBlockState(relativeTailBlockPos);

        VerticalLinearConnectionBlock LinearConnectionBlockType = getLinearConnectionBlockType(state, relativeHeadBlock, relativeTailBlock);

        return state.with(STACKABLE_BLOCK, LinearConnectionBlockType).with(LIT, isLIT(world, pos));
    }

    private boolean isLIT(WorldAccess world, BlockPos pos) {
        for (int i = 1; i < 2; i++) {
            BlockPos blockPosBelow = pos.down(i);
            BlockState blockStateBelow = world.getBlockState(blockPosBelow);
            Block blockBelow = blockStateBelow.getBlock();
            if (blockBelow == this) {
                return blockStateBelow.get(LIT);
            }
            if (blockBelow instanceof AbstractFurnaceBlock || blockBelow instanceof CampfireBlock) {
                return blockStateBelow.get(Properties.LIT);
            }
        }
        return false;
    }

    private VerticalLinearConnectionBlock getLinearConnectionBlockType(BlockState state, BlockState relativeHeadBlock, BlockState relativeBlockTail) {
        boolean isHeadBlockConnected = relativeHeadBlock.isOf(state.getBlock());
        boolean isTailBlockConnected = relativeBlockTail.isOf(state.getBlock());

        if (isHeadBlockConnected && isTailBlockConnected) return VerticalLinearConnectionBlock.MIDDLE;
        if (isHeadBlockConnected) return VerticalLinearConnectionBlock.TAIL;
        if (isTailBlockConnected) return VerticalLinearConnectionBlock.HEAD;
        return VerticalLinearConnectionBlock.SINGLE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient && state.get(LIT) && state.get(STACKABLE_BLOCK) == VerticalLinearConnectionBlock.HEAD ? validateTicker(type, ModBlockEntityTypes.CHIMNEY_BLOCK_ENTITY, ChimneyBlockEntity::clientTick) : null;
    }
}
