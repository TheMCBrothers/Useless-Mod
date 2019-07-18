package tk.themcbros.uselessmod.world.structures;

import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;

public class UselessHutStructure extends ScatteredStructure<NoFeatureConfig> {

	public UselessHutStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> uselessobject) {
		super(uselessobject);
	}

	@Override
	protected int getSeedModifier() {
		return 128;
	}

	@Override
	public int getSize() {
		return 3;
	}

	@Override
	public IStartFactory getStartFactory() {
		return (structure, i, j, biome, box, k, l) -> {
			return null;
		};
	}

	@Override
	public String getStructureName() {
		return "Useless_Hut";
	}

}
