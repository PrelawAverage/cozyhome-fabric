package net.luckystudio.cozyhome.block.type;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;


public class WallClockBlock extends WallMountedBlock {
    public static final MapCodec<WallClockBlock> CODEC = createCodec(WallClockBlock::new);

    @Override
    public MapCodec<WallClockBlock> getCodec() {
        return CODEC;
    }

    public WallClockBlock(Settings settings) {
        super(settings);
    }
}
