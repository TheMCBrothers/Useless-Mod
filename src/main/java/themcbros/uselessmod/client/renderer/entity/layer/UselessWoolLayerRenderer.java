package themcbros.uselessmod.client.renderer.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.client.renderer.entity.model.SheepWoolModel;
import net.minecraft.util.ResourceLocation;
import themcbros.uselessmod.entity.UselessSheepEntity;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class UselessWoolLayerRenderer extends LayerRenderer<UselessSheepEntity, SheepModel<UselessSheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
    private final SheepWoolModel<UselessSheepEntity> sheepModel = new SheepWoolModel<>();

    public UselessWoolLayerRenderer(IEntityRenderer<UselessSheepEntity, SheepModel<UselessSheepEntity>> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int i, UselessSheepEntity sheep, float v, float v1, float v2, float v3, float v4, float v5) {
        if (!sheep.getSheared() && !sheep.isInvisible()) {
            float r = 70f / 255f;
            float g = 139f / 255f;
            float b = 68f / 255f;
            renderCopyCutoutModel(this.getEntityModel(), this.sheepModel, TEXTURE, matrixStack, buffer, i, sheep, v, v1, v3, v4, v5, v2, r, g, b);
        }
    }
}
