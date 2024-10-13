package net.luckystudio.cozyhome.block.test;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Map;

    public class ChairBlock extends AbstractChairBlock {
        public static final MapCodec<net.luckystudio.cozyhome.block.test.ChairBlock> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(ChairBlock.ChairType.CODEC.fieldOf("kind").forGetter(AbstractChairBlock::getChairType), createSettingsCodec())
                        .apply(instance, net.luckystudio.cozyhome.block.test.ChairBlock::new)
        );
        public static final int MAX_ROTATION_INDEX = RotationPropertyHelper.getMax();
        private static final int MAX_ROTATIONS = MAX_ROTATION_INDEX + 1;
        public static final IntProperty ROTATION = Properties.ROTATION;
        protected static final VoxelShape SHAPE = Block.createCuboidShape(2,0,2,14,10,14);

        @Override
        public MapCodec<? extends net.luckystudio.cozyhome.block.test.ChairBlock> getCodec() {
            return CODEC;
        }

        public ChairBlock(ChairBlock.ChairType chairType, AbstractBlock.Settings settings) {
            super(chairType, settings);
            this.setDefaultState(this.getDefaultState().with(ROTATION, 0));
        }

        @Override
        protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
            return SHAPE;
        }

        @Override
        protected VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
            return VoxelShapes.empty();
        }

        @Override
        public BlockState getPlacementState(ItemPlacementContext ctx) {
            return super.getPlacementState(ctx).with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw()));
        }

        @Override
        protected BlockState rotate(BlockState state, BlockRotation rotation) {
            return state.with(ROTATION, rotation.rotate((Integer) state.get(ROTATION), MAX_ROTATIONS));
        }

        @Override
        protected BlockState mirror(BlockState state, BlockMirror mirror) {
            return state.with(ROTATION, mirror.mirror((Integer) state.get(ROTATION), MAX_ROTATIONS));
        }

        @Override
        protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
            super.appendProperties(builder);
            builder.add(ROTATION);
        }

        public interface ChairType extends StringIdentifiable {
            Map<String, net.luckystudio.cozyhome.block.test.ChairBlock.ChairType> TYPES = new Object2ObjectArrayMap<>();
            Codec<ChairBlock.ChairType> CODEC = Codec.stringResolver(StringIdentifiable::asString, TYPES::get);
        }

        public static enum Type implements ChairBlock.ChairType {
            OAK("oak"),
            SPRUCE("spruce"),
            BIRCH("birch"),
            JUNGLE("jungle"),
            ACACIA("acacia"),
            DARK_OAK("dark_oak"),
            MANGROVE("mangrove");

            private final String id;

            private Type(final String id) {
                this.id = id;
                TYPES.put(id, this);
            }

            @Override
            public String asString() {
                return this.id;
            }
        }
}
