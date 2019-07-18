package tk.themcbros.uselessmod.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.IShapedRecipe;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.closet.IClosetMaterial;

public class ClosetRecipe extends SpecialRecipe implements IShapedRecipe<CraftingInventory> {

	public ClosetRecipe(ResourceLocation p_i48169_1_) {
		super(p_i48169_1_);
	}

	@Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        IClosetMaterial beddingId = IClosetMaterial.NULL;
        IClosetMaterial casingId = IClosetMaterial.NULL;
        
        for(int col = 0; col < 3; ++col) {
            for(int row = 0; row < 3; ++row) {
                if((col == 1 && row == 1)) {
                    IClosetMaterial id = ClosetRegistry.BEDDINGS.getFromStack(inv.getStackInSlot(col + row * inv.getWidth()));
                    if(id == IClosetMaterial.NULL || (beddingId != IClosetMaterial.NULL && id != beddingId))
                        return false;
                        
                    beddingId = id;
                }
                else {
                    IClosetMaterial id = ClosetRegistry.CASINGS.getFromStack(inv.getStackInSlot(col + row * inv.getWidth()));
                    if(id == IClosetMaterial.NULL || (beddingId != IClosetMaterial.NULL && id != casingId))
                        return false;
                    
                    casingId = id;
                }
            }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        IClosetMaterial beddingId = ClosetRegistry.BEDDINGS.getFromStack(inv.getStackInSlot(4));
        IClosetMaterial casingId = ClosetRegistry.CASINGS.getFromStack(inv.getStackInSlot(0));
        
        return ClosetRegistry.createItemStack(casingId, beddingId);
    }
    
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for(int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }

        return nonnulllist;
    }

    //Is on a 3x3 grid or bigger
    @Override
    public boolean canFit(int width, int height) {
        return width >= 3 && height >= 3;
    }


    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializers.CLOSET;
    }

    @Override
    public int getRecipeWidth() {
        return 3;
    }

    @Override
    public int getRecipeHeight() {
        return 3;
    }
	
}
