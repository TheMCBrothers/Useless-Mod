package themcbros.uselessmod.api.wall_closet;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.registries.ForgeRegistryEntry;
import themcbros.uselessmod.api.UselessRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * @author TheMCBrothers
 */
public class ClosetMaterial extends ForgeRegistryEntry<ClosetMaterial> {

    private final Supplier<Block> block;
    @Nullable
    private final ResourceLocation textureLocationOverride;

    public ClosetMaterial(@Nonnull Supplier<Block> block) {
        this.block = block;
        this.textureLocationOverride = null;
    }

    public ClosetMaterial(@Nonnull Supplier<Block> block, @Nullable ResourceLocation textureLocationOverride) {
        this.block = block;
        this.textureLocationOverride = textureLocationOverride;
    }

    public Block getBlock() {
        return block.get();
    }

    public ResourceLocation getTextureLocation() {
        if (this.textureLocationOverride == null) {
            return Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(this.block.get().getDefaultState())
                    .getParticleTexture(EmptyModelData.INSTANCE).getName();
        }
        return this.textureLocationOverride;
    }

    @Nullable
    public static ClosetMaterial byId(String id) {
        return UselessRegistries.CLOSET_MATERIALS.getValue(ResourceLocation.tryCreate(id));
    }

}
