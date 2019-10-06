package tk.themcbros.uselessmod.world.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class UselessFlowersFeature extends FlowersFeature {

	private static final Block[] FLOWERS = new Block[] { ModBlocks.RED_ROSE, ModBlocks.BLUE_ROSE, Blocks.BLUE_ORCHID,
			Blocks.OXEYE_DAISY, Blocks.DANDELION, Blocks.POPPY };

	public UselessFlowersFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer) {
		super(deserializer);
	}

	@Override
	public BlockState getRandomFlower(Random rand, BlockPos pos) {
		double d0 = MathHelper.clamp((1.0D
				+ Biome.INFO_NOISE.getValue((double) pos.getX() / 48.0D, (double) pos.getZ() / 48.0D))
				/ 2.0D, 0.0D, 0.9999D);
		Block block = FLOWERS[(int) (d0 * (double) FLOWERS.length)];
		return block.getDefaultState();
	}

}
