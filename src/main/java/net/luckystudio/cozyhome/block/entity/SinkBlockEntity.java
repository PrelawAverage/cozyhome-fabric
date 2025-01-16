//package net.luckystudio.cozyhome.block.entity;
//
//import net.luckystudio.cozyhome.block.ModBlockEntityTypes;
//import net.luckystudio.cozyhome.block.util.ModProperties;
//import net.luckystudio.cozyhome.sound.ModSoundEvents;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.component.ComponentMap;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.network.listener.ClientPlayPacketListener;
//import net.minecraft.network.packet.Packet;
//import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
//import net.minecraft.registry.RegistryWrapper;
//import net.minecraft.state.property.Properties;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//public class SinkBlockEntity extends BlockEntity {
//
//    public SinkBlockEntity(BlockPos pos, BlockState state) {
//        super(ModBlockEntityTypes.SINK_COUNTER_BLOCK_ENTITY, pos, state);
//    }
//
//    public static void tick(World world, BlockPos pos, BlockState state, SinkBlockEntity blockEntity) {
////        if (hasWaterToPull(state, world, pos)) {
////            if (state.get(Properties.TRIGGERED)) {
////                world.playSound(null, pos, ModSoundEvents.RUNNING_WATER, SoundCategory.BLOCKS, 0.1f, 1.0f);
////                // Spawn particles at the block's position
////                world.addImportantParticle(ParticleTypes.FALLING_DRIPSTONE_WATER,
////                        true,pos.getX() + 0.5, pos.getY() + 1.1875, pos.getZ() + 0.5, // Position (center of block)
////                        0.0, 0.0, 0.0// Offset for randomness
////                );
////                world.addImportantParticle(ParticleTypes.SPLASH,
////                        true,pos.getX() + 0.5, pos.getY() + getWaterLevel(state), pos.getZ() + 0.5, // Position (center of block)
////                        0.0, 0.0, 0.0// Offset for randomness
////                );
////            }
////        } else {
////            world.setBlockState(pos, state.with(Properties.TRIGGERED, false), 3);
////        }
//    }
//
//    private static boolean hasWaterToPull(BlockState state, World world, BlockPos pos) {
//        return isWaterLogged(world, pos.down()) || isWaterLogged(world, pos.offset(state.get(Properties.HORIZONTAL_FACING)));
//    }
//
//    private static boolean isWaterLogged(World world, BlockPos pos) {
//        return world.getFluidState(pos).getFluid().matchesType(Fluids.WATER);
//    }
//
//    private static float getWaterLevel(BlockState state) {
//        int level = state.get(ModProperties.FILLED_LEVEL_0_3);
//        return switch (level) {
//            case 1 -> 0.438f;
//            case 2 -> 0.688f;
//            case 3 -> 0.938f;
//            default -> 0.125f;
//        };
//    }
//
//    @Override
//    protected void readComponents(ComponentsAccess components) {
//        super.readComponents(components);
//    }
//
//    @Override
//    protected void addComponents(ComponentMap.Builder componentMapBuilder) {
//        super.addComponents(componentMapBuilder);
//    }
//
//    // This Syncs the Client and Server
//    @Nullable
//    @Override
//    public Packet<ClientPlayPacketListener> toUpdatePacket() {
//        return BlockEntityUpdateS2CPacket.create(this);
//    }
//
//    // This Syncs the Client and Server
//    @Override
//    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
//        return createNbt(registryLookup);
//    }
//}
