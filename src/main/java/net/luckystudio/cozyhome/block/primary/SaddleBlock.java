package net.luckystudio.cozyhome.block.primary;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.LoomBlock;

public class SaddleBlock extends HorizontalFacingBlock {
    public static final MapCodec<SaddleBlock> CODEC = createCodec(SaddleBlock::new);
    public SaddleBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<SaddleBlock> getCodec() {
        return CODEC;
    }
}
