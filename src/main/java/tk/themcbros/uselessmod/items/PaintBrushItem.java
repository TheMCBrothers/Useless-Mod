package tk.themcbros.uselessmod.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.themcbros.uselessmod.helper.UselessUtils;

public class PaintBrushItem extends Item {
	
	public static final int[] WHITE = new int[] {249,255,254};

	public PaintBrushItem(Properties properties) {
		super(properties);
	}
	
	@Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        int[] rgb = WHITE;
        if(stack.hasTag() && stack.getTag().contains("color")) {
            rgb = UselessUtils.rgbIntToIntArray(stack.getTag().getInt("color"));
        }

        tooltip.add(new TranslationTextComponent(this.getTranslationKey() + ".tooltip", TextFormatting.RED + "" + rgb[0] + TextFormatting.GREEN + " " + rgb[1] + TextFormatting.BLUE + " " + rgb[2]));
    }
    
    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if(this.isInGroup(group)) {
            for(DyeColor color : DyeColor.values()) {
                ItemStack baseColours = new ItemStack(this);
                baseColours.setTag(new CompoundNBT());
                float[] colourComponents = color.getColorComponentValues();
                int colour = (int) (colourComponents[0] * 255F);
                colour = (int) ((colour << 8) + colourComponents[1] * 255F);
                colour = (int) ((colour << 8) + colourComponents[2] * 255F);
                
                
                baseColours.getTag().putInt("color", colour);
                items.add(baseColours);
            }
        }
    }
    
    public boolean hasColor(ItemStack stack) {
        CompoundNBT nbttagcompound = stack.getTag();
        return nbttagcompound != null && nbttagcompound.contains("color", 3);
        
    }

    public int getColor(ItemStack stack) {
       
        CompoundNBT nbttagcompound = stack.getTag();

        if(nbttagcompound != null)    
            return nbttagcompound.getInt("color");

        return 10511680;
      
    }

    public void removeColor(ItemStack stack) {

        CompoundNBT nbttagcompound = stack.getTag();

        if(nbttagcompound != null)
            nbttagcompound.remove("color");
        
    }

    public void setColor(ItemStack stack, int color) {

        CompoundNBT nbttagcompound = stack.getTag();

        if(nbttagcompound == null) {    
            nbttagcompound = new CompoundNBT();
            stack.setTag(nbttagcompound);
        }
            
        nbttagcompound.putInt("color", color);
    }

}
