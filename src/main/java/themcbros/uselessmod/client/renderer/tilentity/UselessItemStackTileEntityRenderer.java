package themcbros.uselessmod.client.renderer.tilentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.ItemInit;
import themcbros.uselessmod.item.UselessShieldItem;
import themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;
import themcbros.uselessmod.tileentity.UselessBedTileEntity;
import themcbros.uselessmod.tileentity.UselessChestTileEntity;

public class UselessItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {
    private static final RenderMaterial USELESS_SHIELD_MATERIAL = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, UselessMod.rl("entity/shield_useless"));
    private static final RenderMaterial SUPER_USELESS_SHIELD_MATERIAL = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, UselessMod.rl("entity/shield_super_useless"));

    private final ShieldModel modelShield = new ShieldModel();

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof AbstractSkullBlock) {
                UselessSkullTileEntityRenderer.render(null, 180.0F,
                        ((AbstractSkullBlock)block).getSkullType(), 0.0F, matrixStack, buffer, combinedLight);
            } else {
                TileEntity tileentity;
                if (block instanceof BedBlock) {
                    tileentity = new UselessBedTileEntity();
                } else if (block == BlockInit.COFFEE_MACHINE.get()) {
                    BlockState state = BlockInit.COFFEE_MACHINE.get().getDefaultState();
                    Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(state, matrixStack, buffer, combinedLight, combinedOverlay, net.minecraftforge.client.model.data.EmptyModelData.INSTANCE);
                    CoffeeMachineTileEntity coffeeMachine = new CoffeeMachineTileEntity();
                    if (stack.hasTag()) {
                        CompoundNBT fluidTag = stack.getOrCreateChildTag("Fluid");
                        coffeeMachine.waterTank.setFluid(FluidStack.loadFluidStackFromNBT(fluidTag));
                        CompoundNBT tag = stack.getOrCreateTag();
                        coffeeMachine.energyStorage.setEnergyStored(tag.getInt("EnergyStored"));
                        ItemStackHelper.loadAllItems(tag, coffeeMachine.coffeeStacks);
                    }
                    tileentity = coffeeMachine;
                } else if (block == BlockInit.USELESS_CHEST.get()) {
                    tileentity = new UselessChestTileEntity();
                } else {
                    tileentity = new UselessChestTileEntity();
                }

                TileEntityRendererDispatcher.instance.renderItem(tileentity, matrixStack, buffer, combinedLight, combinedOverlay);
            }
        } else if (item instanceof UselessShieldItem) {
            matrixStack.push();
            matrixStack.scale(1.0F, -1.0F, -1.0F);
            boolean flag = item == ItemInit.USELESS_SHIELD.get();
            RenderMaterial material = flag ? USELESS_SHIELD_MATERIAL : SUPER_USELESS_SHIELD_MATERIAL;
            IVertexBuilder ivertexbuilder = material.getSprite().wrapBuffer(ItemRenderer.getBuffer(buffer, this.modelShield.getRenderType(material.getAtlasLocation()), false, stack.hasEffect()));
            this.modelShield.func_228294_b_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            this.modelShield.func_228293_a_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();
        }
    }
}
