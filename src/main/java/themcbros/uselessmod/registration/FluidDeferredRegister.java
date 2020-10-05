package themcbros.uselessmod.registration;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;

import java.util.ArrayList;
import java.util.List;

public class FluidDeferredRegister {

    private static final ResourceLocation OVERLAY = new ResourceLocation("minecraft", "block/water_overlay");

    private final List<FluidRegistryObject<?, ?, ?, ?>> allFluids = new ArrayList<>();

    private final String modId;

    private final DeferredRegister<Fluid> fluidRegister;
    private final DeferredRegister<Block> blockRegister;
    private final DeferredRegister<Item> itemRegister;

    public FluidDeferredRegister(String modId) {
        this.modId = modId;
        blockRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, modId);
        fluidRegister = DeferredRegister.create(ForgeRegistries.FLUIDS, modId);
        itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, modId);
    }

    public FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> register(String name, FluidAttributes.Builder builder) {
        String flowingName = "flowing_" + name;
        String bucketName = name + "_bucket";
        //For now all our fluids use the same "overlay" for being against glass as vanilla water.
        builder.overlay(OVERLAY);
        //Create the registry object with dummy entries that we can use as part of the supplier but that works as use in suppliers
        FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> fluidRegistryObject = new FluidRegistryObject<>(modId, name);
        //Pass in suppliers that are wrapped instead of direct references to the registry objects, so that when we update the registry object to
        // point to a new object it gets updated properly.
        ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(fluidRegistryObject::getStillFluid,
                fluidRegistryObject::getFlowingFluid, builder).bucket(fluidRegistryObject::getBucket).block(fluidRegistryObject::getBlock);
        //Update the references to objects that are retrieved from the deferred registers
        fluidRegistryObject.updateStill(fluidRegister.register(name, () -> new ForgeFlowingFluid.Source(properties)));
        fluidRegistryObject.updateFlowing(fluidRegister.register(flowingName, () -> new ForgeFlowingFluid.Flowing(properties)));
        fluidRegistryObject.updateBucket(itemRegister.register(bucketName, () -> new BucketItem(fluidRegistryObject::getStillFluid,
                new Item.Properties().group(UselessMod.GROUP).maxStackSize(1).containerItem(Items.BUCKET))));
        //Note: The block properties used here is a copy of the ones for water
        fluidRegistryObject.updateBlock(blockRegister.register(name, () -> new FlowingFluidBlock(fluidRegistryObject::getStillFluid,
                Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops())));
        allFluids.add(fluidRegistryObject);
        return fluidRegistryObject;
    }

    public void register(IEventBus bus) {
        blockRegister.register(bus);
        fluidRegister.register(bus);
        itemRegister.register(bus);
    }

    public List<FluidRegistryObject<?, ?, ?, ?>> getAllFluids() {
        return allFluids;
    }

}
