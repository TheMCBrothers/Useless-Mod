package themcbros.uselessmod.registration;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class FluidRegistryObject<STILL extends Fluid, FLOWING extends Fluid,
        BLOCK extends FlowingFluidBlock, BUCKET extends BucketItem> {

    private RegistryObject<STILL> still;
    private RegistryObject<FLOWING> flowing;
    private RegistryObject<BLOCK> block;
    private RegistryObject<BUCKET> bucket;

    public FluidRegistryObject(String modId, String name) {
        this.still = RegistryObject.of(new ResourceLocation(modId, name), ForgeRegistries.FLUIDS);
        this.flowing = RegistryObject.of(new ResourceLocation(modId, "flowing_" + name), ForgeRegistries.FLUIDS);
        this.block = RegistryObject.of(new ResourceLocation(modId, name), ForgeRegistries.BLOCKS);
        this.bucket = RegistryObject.of(new ResourceLocation(modId, name + "_bucket"), ForgeRegistries.ITEMS);
    }

    void updateStill(RegistryObject<STILL> still) {
        this.still = Objects.requireNonNull(still);
    }

    void updateFlowing(RegistryObject<FLOWING> flowing) {
        this.flowing = Objects.requireNonNull(flowing);
    }

    void updateBlock(RegistryObject<BLOCK> block) {
        this.block = Objects.requireNonNull(block);
    }

    void updateBucket(RegistryObject<BUCKET> bucket) {
        this.bucket = Objects.requireNonNull(bucket);
    }

    public STILL getStillFluid() {
        return this.still.get();
    }

    public FLOWING getFlowingFluid() {
        return this.flowing.get();
    }

    public BLOCK getBlock() {
        return this.block.get();
    }

    public BUCKET getBucket() {
        return this.bucket.get();
    }
}
