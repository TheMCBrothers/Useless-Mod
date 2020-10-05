package themcbros.uselessmod.color;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import themcbros.uselessmod.helpers.ColorUtils;

/**
 * @author TheMCBrothers
 */
public class ColorFluid extends Fluid {

    private final FluidAttributes.Builder builder;

    public ColorFluid(FluidAttributes.Builder builder) {
        this.builder = builder;
    }

    @Override
    protected FluidAttributes createAttributes() {
        return new ColorAttributes(this.builder, this);
    }

    @Override
    public Item getFilledBucket() {
        return ColorModule.BUCKET_PAINT.get();
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
        public int getColor(FluidStack stack) {
            if (stack.hasTag() && stack.getOrCreateTag().contains("Color", Constants.NBT.TAG_ANY_NUMERIC)) {
                int color = stack.getOrCreateTag().getInt("Color");
                return ColorUtils.fullAlpha(color);
            }
            return super.getColor(stack);
        }

        @Override
        public ItemStack getBucket(FluidStack stack) {
            return ColorModule.BUCKET_PAINT.get().fillBucket(stack);
        }
    }

}
