package net.luckystudio.cozyhome.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.luckystudio.cozyhome.block.entity.ChairBlockEntity;
import net.luckystudio.cozyhome.block.custom.abstracts.AbstractSeatBlock;
import net.luckystudio.cozyhome.block.util.ModProperties;
import net.luckystudio.cozyhome.block.util.interfaces.TuckableBlock;
import net.luckystudio.cozyhome.components.ModDataComponents;
import net.luckystudio.cozyhome.item.ModItems;
import net.luckystudio.cozyhome.item.custom.CushionItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class StoolBlock extends AbstractSeatBlock implements TuckableBlock {
    public static final MapCodec<StoolBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    StoolBlock.StoolType.CODEC.fieldOf("kind").forGetter(StoolBlock::getStoolType),
                    createSettingsCodec() // Ensure this exists and works as expected
            ).apply(instance, StoolBlock::new)
    );

    public static final BooleanProperty TUCKED = ModProperties.TUCKED;
    private static final VoxelShape BASE_SHAPE = StoolBlock.createCuboidShape(2,0,2,14,10,14);
    private final StoolType type;
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    public int color;

    public StoolBlock(StoolType stoolType, Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(ROTATION, 0));
        this.type = stoolType;
        this.color = 0xFFFFFF;
    }

    @Override
    protected MapCodec<? extends StoolBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, TUCKED);
    }

    // This is the hit-box of the block, we are applying our VoxelShape to it.
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                return BASE_SHAPE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx)
                .with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw()))
                .with(TUCKED, false);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Check if the BlockEntity at the position is a ChairBlockEntity
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ChairBlockEntity chairBlockEntity) {
            // Handle interactions with items
            if (stack.getItem() instanceof CushionItem) {
                return handleCushionItem(stack, chairBlockEntity, state, world, pos, player);
            } else if (stack.getItem() == Items.SHEARS) {
                return handleShearsItem(stack, chairBlockEntity, state, world, pos, player);
            } else if (stack.getItem() instanceof DyeItem dyeItem) {
                return handleDyeItem(stack, chairBlockEntity, dyeItem, state, world, pos, player);
            }

            // Call tuckable logic or fallback to super
            TuckableBlock.tryTuck(state, world, pos, player);
        } else {
            // Log unexpected block entities to debug the issue
            System.out.println("Unexpected BlockEntity at position " + pos + ": " + blockEntity);
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }



    private ItemActionResult handleCushionItem(ItemStack stack, ChairBlockEntity chairBlockEntity, BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (chairBlockEntity.cushion_type.isEmpty()) {
            chairBlockEntity.color = stack.getOrDefault(ModDataComponents.COLOR, 0xFFFFFF);
            stack.decrementUnlessCreative(1, player);
            chairBlockEntity.cushion_type = getCushionType(stack.getItem());
            updateChairBlockEntity(world, pos, chairBlockEntity, state);
            world.playSound(player, pos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            player.sendMessage(Text.translatable("message.cozyhome.remove_with_shear"), true);
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public String getCushionType(Item item) {
        if (item == ModItems.CUSHION) {
            return "generic";
        } else if (item == ModItems.HAY_CUSHION) {
            return "hay";
        } else if (item == ModItems.TRADER_CUSHION) {
            return "trader";
        } else {
            return "";
        }
    }

    private ItemActionResult handleShearsItem(ItemStack stack, ChairBlockEntity chairBlockEntity, BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!chairBlockEntity.cushion_type.isEmpty()) {
            stack.damage(1, player, EquipmentSlot.MAINHAND);
            ItemStack cushionStack = new ItemStack(getCushionItem(chairBlockEntity.cushion_type));

            spawnItemEntity(world, pos, cushionStack);
            world.playSound(player, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);

            chairBlockEntity.cushion_type = "";
            chairBlockEntity.color = 0xFFFFFF;
            updateChairBlockEntity(world, pos, chairBlockEntity, state);

            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public Item getCushionItem(String type) {
        return switch (type) {
            case "hay" -> ModItems.HAY_CUSHION;
            case "trader" -> ModItems.TRADER_CUSHION;
            default -> ModItems.CUSHION;
        };
    }

    private ItemActionResult handleDyeItem(ItemStack stack, ChairBlockEntity chairBlockEntity, DyeItem dyeItem, BlockState state, World world, BlockPos pos, PlayerEntity player) {
        int newColor = dyeItem.getColor().getEntityColor();
        chairBlockEntity.color = ColorHelper.Argb.averageArgb(newColor, chairBlockEntity.color);
        stack.decrementUnlessCreative(1, player);
        updateChairBlockEntity(world, pos, chairBlockEntity, state);
        return ItemActionResult.SUCCESS;
    }

    private void updateChairBlockEntity(World world, BlockPos pos, ChairBlockEntity chairBlockEntity, BlockState state) {
        chairBlockEntity.markDirty();
        world.updateListeners(pos, state, state, 3);
    }

    private void spawnItemEntity(World world, BlockPos pos, ItemStack itemStack) {
        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.75, pos.getZ() + 0.5, itemStack));
    }

    public enum Type implements StoolType {
        OAK("oak"),
        SPRUCE("spruce"),
        BIRCH("birch"),
        JUNGLE("jungle"),
        ACACIA("acacia"),
        DARK_OAK("dark_oak"),
        MANGROVE("mangrove"),
        CHERRY("cherry"),
        BAMBOO("bamboo"),
        CRIMSON("crimson"),
        WARPED("warped"),
        PRINCESS("princess"),
        IRON("iron"),
        GLASS("iron"),
        UNDEAD("undead"),
        OMINOUS("ominous");

        private final String id;

        Type(final String id) {
            this.id = id;
            TYPES.put(id, this);
        }

        @Override
        public String asString() {
            return this.id;
        }
    }

    public StoolType getStoolType() {
        return this.type;
    }

    public interface StoolType extends StringIdentifiable {
        Map<String, StoolType> TYPES = new Object2ObjectArrayMap<>();
        Codec<StoolType> CODEC = Codec.stringResolver(StringIdentifiable::asString, TYPES::get);
    }

    @Override
    public float getSeatHeight(BlockState state) {
        return 0.8f;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        String type = stack.getOrDefault(ModDataComponents.CUSHION_TYPE, "");
        if (!type.isEmpty()) {
            tooltip.add(ScreenTexts.EMPTY);
            tooltip.add(Text.translatable("tooltip.cozyhome.has").formatted(TITLE_FORMATTING));
            if (type.equals("generic")) {
                tooltip.add(ScreenTexts.space().append(Text.translatable("item.cozyhome.cushion").formatted(TITLE_FORMATTING)));
            }
        }
    }
}
