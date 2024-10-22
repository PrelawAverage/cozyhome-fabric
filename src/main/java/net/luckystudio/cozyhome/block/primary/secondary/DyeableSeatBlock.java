package net.luckystudio.cozyhome.block.primary.secondary;

import com.mojang.serialization.MapCodec;
import net.luckystudio.cozyhome.block.primary.AbstractDyeableBlock;
import net.luckystudio.cozyhome.block.util.interfaces.SeatBlock;
import net.luckystudio.cozyhome.entity.ModEntities;
import net.luckystudio.cozyhome.entity.custom.SeatEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.List;

public abstract class DyeableSeatBlock extends AbstractDyeableBlock implements SeatBlock {

    public DyeableSeatBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState());
    }

    @Override
    protected abstract MapCodec<? extends DyeableSeatBlock> getCodec();

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ItemActionResult.SUCCESS;
        if (stack.getItem() instanceof DyeItem item) {
            super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        } else {
            SeatBlock.sitDown(state, world, pos, player);
        }
        return ItemActionResult.SUCCESS;
    }

    public float getSeatHeight(BlockState state) {
        return 0.5f;
    }

    public float setRiderRotation(Entity entity) {
        return entity.getYaw();
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);

        if (!world.isClient()) {
            // Define the search radius
            double radius = 5.0;

            // Create an AABB (Axis-Aligned Bounding Box) to search around the block position
            Box searchBox = new Box(pos).expand(radius);

            // Get all entities
            List<SeatEntity> entities = world.getEntitiesByClass(SeatEntity.class, searchBox, entity -> true);

            // Loop through the list and remove each entity
            for (SeatEntity seatEntity : entities) {
                seatEntity.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    // This is needed or the block will just be invisible
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
