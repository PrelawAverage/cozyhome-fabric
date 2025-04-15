package net.luckystudio.cozyhome.block.util.interfaces;

import net.luckystudio.cozyhome.block.custom.water_holding_blocks.AbstractWaterHoldingBlockEntity;
import net.luckystudio.cozyhome.block.util.enums.ContainsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsage;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public interface WaterHoldingBlock {

    float getLiquidLevelHeight(BlockState state);

    List<Direction> getDirectionsToPull(BlockState state);

    Direction pullingDirection(BlockState state, World world, BlockPos pos);

    boolean isFull(BlockState state);

    void addLiquid(BlockState state, World world, BlockPos pos, BlockState pullState, Direction pullDirection);

    void removeLiquid(BlockState state, World world, BlockPos pos);

    static boolean trySoup(Item item, World world, BlockPos pos, PlayerEntity player, Hand hand, ContainsBlock contents) {
        Identifier id = Registries.ITEM.getId(item); // or BuiltInRegistries.ITEM.getId(item) in newer versions

        boolean isSupplementariesSoup = id.toString().equals("supplementaries:soap");
        if (isSupplementariesSoup) {
            if (world.getBlockEntity(pos) instanceof AbstractWaterHoldingBlockEntity blockEntity && contents == ContainsBlock.WATER) {
                blockEntity.soupTime = blockEntity.soupTime + 200;
                ItemUsage.consumeHeldItem(world, player, hand);
                return true;
            } else {
                player.sendMessage(Text.translatable("message.cozyhome.needs_liquid"), true);
            }
        }
        return false;
    }
}