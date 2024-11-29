package net.luckystudio.cozyhome.block.util.interfaces;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface Strippable {

    // Method to define the stripped version of the block
    static Block getStrippedVersion(BlockState state) {

        // Get the registry name of the original block using the new method for Fabric 1.21
        Identifier originalBlockId = Registries.BLOCK.getId(state.getBlock());

        // Construct the stripped version's registry name by adding the prefix "stripped_"
        Identifier strippedBlockId = Identifier.of(originalBlockId.getNamespace(), "stripped_" + originalBlockId.getPath());

        // Look up the stripped block from the registry
        Block strippedBlock = Registries.BLOCK.get(strippedBlockId);

        // If the stripped block exists, return it, otherwise default to a safe block
        return strippedBlock;  // Default to a safe block (like oak wood) in case not found
    }

    // Method that is called when an axe is used on the block
    static ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Block strippedBlock = getStrippedVersion(state);
        if (strippedBlock != Blocks.AIR) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.getItem() instanceof AxeItem) {
                if (!world.isClient) {
                    // Set the block state to the stripped block while keeping the original state
                    world.setBlockState(pos, strippedBlock.getStateWithProperties(state), 11);  // 11 is for notifying neighbors and updating the world
                    world.playSound(null, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1, 1);
                    // Damage the axe
                    itemStack.damage(1, player, LivingEntity.getSlotForHand(hand));  // Axe durability damage
                }
                return ItemActionResult.SUCCESS;
            }
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}

