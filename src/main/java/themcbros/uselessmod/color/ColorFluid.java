package themcbros.uselessmod.color;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

/**
 * @author TheMCBrothers
 */
public class ColorFluid extends Fluid {

    private final Supplier<? extends IItemProvider> bucket;
    private final FluidAttributes.Builder builder;

    public ColorFluid(Supplier<? extends IItemProvider> bucket, FluidAttributes.Builder builder) {
        this.bucket = bucket;
        this.builder = builder;
    }

    @Override
    protected FluidAttributes createAttributes() {
        return new ColorAttributes(this.builder, this);
    }

    @Override
    public Item getFilledBucket() {
        return this.bucket.get().asItem();
    }

    @Override
    protected boolean canDisplace(FluidState fluidStateIn, IBlockReader blockReader, BlockPos pos, Fluid fluidIn, Direction directionIn) {
        return false;
    }

    @Override
    protected Vector3d getFlow(IBlockReader world, BlockPos pos, FluidState fluidStateIn) {
        return Vector3d.ZERO;
    }

    @Override
    public int getTickRate(IWorldReader p_205569_1_) {
        return 0;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    public float getActualHeight(FluidState fluidState, IBlockReader world, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public float getHeight(FluidState fluidState) {
        return 1.0F;
    }

    @Override
    protected BlockState getBlockState(FluidState state) {
        return Blocks.AIR.getDefaultState();
    }

    @Override
    public boolean isSource(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState fluidState) {
        return 0;
    }

    @Override
    public VoxelShape func_215664_b(FluidState fluidState, IBlockReader world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    private static class ColorAttributes extends FluidAttributes {
        protected ColorAttributes(Builder builder, Fluid fluid) {
            super(builder, fluid);
        }

        @Override
        public ITextComponent getDisplayName(FluidStack stack) {
            if (stack.hasTag() && stack.getOrCreateTag().contains("Color", Constants.NBT.TAG_ANY_NUMERIC)) {
                return new TranslationTextComponent("color.minecraft.blue").appendString(" ").append(super.getDisplayName(stack));
            }
            return super.getDisplayName(stack);
        }

        @Override
        public int getColor(FluidStack stack) {
            if (stack.hasTag() && stack.getOrCreateTag().contains("Color", Constants.NBT.TAG_ANY_NUMERIC)) {
                return stack.getOrCreateTag().getInt("Color");
            }
            return super.getColor(stack);
        }
    }

}
