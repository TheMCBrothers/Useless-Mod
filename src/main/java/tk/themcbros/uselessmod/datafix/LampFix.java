package tk.themcbros.uselessmod.datafix;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;

public class LampFix extends DataFix {

	public LampFix(Schema outputSchema, boolean changesType) {
		super(outputSchema, changesType);
	}

	@Override
	protected TypeRewriteRule makeRule() {
		return null;
	}

}
