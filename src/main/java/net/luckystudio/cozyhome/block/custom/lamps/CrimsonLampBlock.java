package net.luckystudio.cozyhome.block.custom.lamps;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

// Copied from net.minecraft.block.TorchBlock and SporeBlossomBlock
public class CrimsonLampBlock extends AbstractLampBlock {
    protected static final MapCodec<SimpleParticleType> PARTICLE_TYPE_CODEC = Registries.PARTICLE_TYPE
            .getCodec()
            .<SimpleParticleType>comapFlatMap(
                    particleType -> particleType instanceof SimpleParticleType simpleParticleType
                            ? DataResult.success(simpleParticleType)
                            : DataResult.error(() -> "Not a SimpleParticleType: " + particleType),
                    particleType -> particleType
            )
            .fieldOf("particle_options");
    public static final MapCodec<CrimsonLampBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(PARTICLE_TYPE_CODEC.forGetter(block -> block.particle), createSettingsCodec()).apply(instance, CrimsonLampBlock::new)
    );
    public static final VoxelShape TOP_PIECE = Block.createCuboidShape(2, 8, 2, 14, 14, 14);
    public static final VoxelShape POT = Block.createCuboidShape(4, 0, 4, 12, 6, 12);

    public static final VoxelShape SINGLE_SHAPE = VoxelShapes.union(TOP_PIECE, Block.createCuboidShape(5, 6, 5, 11, 8, 11), POT);
    public static final VoxelShape TOP_SHAPE = VoxelShapes.union(TOP_PIECE, Block.createCuboidShape(5, 0, 5, 11, 8, 11));
    public static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(5, 0, 5, 11, 16, 11);
    public static final VoxelShape BOTTOM_SHAPE = VoxelShapes.union(POT, Block.createCuboidShape(5, 2, 5, 11, 16, 11));
    protected final SimpleParticleType particle;

    public CrimsonLampBlock(SimpleParticleType particle, Settings settings) {
        super(settings);
        this.particle = particle;
    }

    @Override
    protected MapCodec<? extends CrimsonLampBlock> getCodec() {
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

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            double d = (double) i + random.nextDouble();
            double e = (double) j + 0.7;
            double f = (double) k + random.nextDouble();
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);
        }
    }
}
