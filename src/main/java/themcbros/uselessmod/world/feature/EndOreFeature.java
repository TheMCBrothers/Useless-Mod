package themcbros.uselessmod.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Predicate;

public class EndOreFeature extends OreFeature {

    private final Predicate<BlockState> target = state -> state.isIn(Tags.Blocks.END_STONES);

    public EndOreFeature(Codec<OreFeatureConfig> featureConfigCodec) {
        super(featureConfigCodec);
    }

    @Override
    protected boolean func_207803_a(@Nonnull IWorld worldIn, @Nonnull Random random, OreFeatureConfig config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) {
        int i = 0;
        BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
        BlockPos.Mutable blockPos = new BlockPos.Mutable();
        double[] d = new double[config.size * 4];

        for(int j = 0; j < config.size; ++j) {
            float f = (float)j / (float)config.size;
            double d0 = MathHelper.lerp(f, p_207803_4_, p_207803_6_);
            double d2 = MathHelper.lerp(f, p_207803_12_, p_207803_14_);
            double d4 = MathHelper.lerp(f, p_207803_8_, p_207803_10_);
            double d6 = random.nextDouble() * (double)config.size / 16.0D;
            double d7 = ((double)(MathHelper.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
            d[j * 4] = d0;
            d[j * 4 + 1] = d2;
            d[j * 4 + 2] = d4;
            d[j * 4 + 3] = d7;
        }

        for(int l2 = 0; l2 < config.size - 1; ++l2) {
            if (!(d[l2 * 4 + 3] <= 0.0D)) {
                for(int j3 = l2 + 1; j3 < config.size; ++j3) {
                    if (!(d[j3 * 4 + 3] <= 0.0D)) {
                        double d12 = d[l2 * 4] - d[j3 * 4];
                        double d13 = d[l2 * 4 + 1] - d[j3 * 4 + 1];
                        double d14 = d[l2 * 4 + 2] - d[j3 * 4 + 2];
                        double d15 = d[l2 * 4 + 3] - d[j3 * 4 + 3];
                        if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
                            if (d15 > 0.0D) {
                                d[j3 * 4 + 3] = -1.0D;
                            } else {
                                d[l2 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for(int i3 = 0; i3 < config.size; ++i3) {
            double d11 = d[i3 * 4 + 3];
            if (!(d11 < 0.0D)) {
                double d1 = d[i3 * 4];
                double d3 = d[i3 * 4 + 1];
                double d5 = d[i3 * 4 + 2];
                int k = Math.max(MathHelper.floor(d1 - d11), p_207803_16_);
                int k3 = Math.max(MathHelper.floor(d3 - d11), p_207803_17_);
                int l = Math.max(MathHelper.floor(d5 - d11), p_207803_18_);
                int i1 = Math.max(MathHelper.floor(d1 + d11), k);
                int j1 = Math.max(MathHelper.floor(d3 + d11), k3);
                int k1 = Math.max(MathHelper.floor(d5 + d11), l);

                for(int l1 = k; l1 <= i1; ++l1) {
                    double d8 = ((double)l1 + 0.5D - d1) / d11;
                    if (d8 * d8 < 1.0D) {
                        for(int i2 = k3; i2 <= j1; ++i2) {
                            double d9 = ((double)i2 + 0.5D - d3) / d11;
                            if (d8 * d8 + d9 * d9 < 1.0D) {
                                for(int j2 = l; j2 <= k1; ++j2) {
                                    double d10 = ((double)j2 + 0.5D - d5) / d11;
                                    if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) {
                                        int k2 = l1 - p_207803_16_ + (i2 - p_207803_17_) * p_207803_19_ + (j2 - p_207803_18_) * p_207803_19_ * p_207803_20_;
                                        if (!bitset.get(k2)) {
                                            bitset.set(k2);
                                            blockPos.setPos(l1, i2, j2);
                                            if (this.target.test(worldIn.getBlockState(blockPos))) {
                                                worldIn.setBlockState(blockPos, config.state, 2);
                                                ++i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}
