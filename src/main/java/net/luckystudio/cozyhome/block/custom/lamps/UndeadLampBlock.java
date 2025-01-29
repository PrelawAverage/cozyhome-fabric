package net.luckystudio.cozyhome.block.custom.lamps;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.block_entity.LampBlockEntity;
import net.luckystudio.cozyhome.block.util.enums.VerticalLinearConnectionBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class UndeadLampBlock extends AbstractLampBlock {
    public static final MapCodec<UndeadLampBlock> CODEC = createCodec(UndeadLampBlock::new);
    public static final VoxelShape TOP_PIECE = Block.createCuboidShape(4, 4, 4, 12, 12, 12);
    public static final VoxelShape BOTTOM_PIECE = Block.createCuboidShape(6, 0, 6, 10, 4, 10);

    public static final VoxelShape SINGLE_SHAPE = VoxelShapes.union(TOP_PIECE, BOTTOM_PIECE);
    public static final VoxelShape TOP_SHAPE = VoxelShapes.union(TOP_PIECE, Block.createCuboidShape(6, 0, 6, 10, 4, 10));
    public static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(6, 0, 6, 10, 16, 10);
    public static final VoxelShape BOTTOM_SHAPE = VoxelShapes.union(BOTTOM_PIECE, Block.createCuboidShape(6, 2, 6, 10, 16, 10));

    public UndeadLampBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(CONNECTION)) {
            case HEAD -> TOP_SHAPE;
            case MIDDLE -> MIDDLE_SHAPE;
            case TAIL -> BOTTOM_SHAPE;
            default -> SINGLE_SHAPE;
        };
    }

    // Gives the Iron Lamp that default looking color we all know
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world.getBlockEntity(pos) instanceof LampBlockEntity lampBlockEntity) {
            final int color = DyedColorComponent.getColor(itemStack, -5231066);
            ComponentMap components = ComponentMap.builder().add(DataComponentTypes.DYED_COLOR, new DyedColorComponent(color, false)).build();
            lampBlockEntity.setComponents(components);
            lampBlockEntity.markDirty();
            world.updateListeners(pos, state, state, 0);
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    // Add flame particles to the lamp when on
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (state.get(CONNECTION) == VerticalLinearConnectionBlock.HEAD || state.get(CONNECTION) == VerticalLinearConnectionBlock.SINGLE) {
            if (state.get(LIT)) {
                double x = pos.getX() + 0.5D;
                double y = pos.getY() + 0.55D;
                double z = pos.getZ() + 0.5D;
                world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.SMALL_FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
