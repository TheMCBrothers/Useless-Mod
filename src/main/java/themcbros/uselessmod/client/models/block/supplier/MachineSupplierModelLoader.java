package themcbros.uselessmod.client.models.block.supplier;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class MachineSupplierModelLoader implements IModelLoader<MachineSupplierModelGeometry> {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public MachineSupplierModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new MachineSupplierModelGeometry();
    }
}
