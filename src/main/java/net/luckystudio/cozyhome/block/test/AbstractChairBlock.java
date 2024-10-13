package net.luckystudio.cozyhome.block.test;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractChairBlock extends BlockWithEntity {
    public static final BooleanProperty POWERED = Properties.POWERED;
    private final ChairBlock.ChairType type;

    public AbstractChairBlock(ChairBlock.ChairType chairType, AbstractBlock.Settings settings) {
        super(settings);
        this.type = chairType;
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, Boolean.FALSE));
    }

    @Override
    protected abstract MapCodec<? extends net.luckystudio.cozyhome.block.test.AbstractChairBlock> getCodec();

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChairBlockEntity(pos, state);
    }

    public ChairBlock.ChairType getChairType() {
        return this.type;
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(POWERED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    // Might not need
    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            boolean bl = world.isReceivingRedstonePower(pos);
            if (bl != (Boolean)state.get(POWERED)) {
                world.setBlockState(pos, state.with(POWERED, bl), Block.NOTIFY_LISTENERS);
            }
        }
    }
}
