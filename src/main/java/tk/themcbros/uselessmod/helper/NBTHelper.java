package tk.themcbros.uselessmod.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants.NBT;

public class NBTHelper {

	public static final String DATA_ID = "uselessData";

	public static CompoundNBT getDataMap(ItemStack stack) {
		initStack(stack);

		return stack.getTag().getCompound(DATA_ID);
	}

	public static boolean hasData(ItemStack stack, String key) {
		initStack(stack);

		return getDataMap(stack).contains(key);
	}

	public static void removeData(ItemStack stack, String key) {
		initStack(stack);

		getDataMap(stack).remove(key);
	}

	public static int getInt(ItemStack stack, String key) {
		initStack(stack);

		return getDataMap(stack).getInt(key);
	}

	public static boolean getBoolean(ItemStack stack, String key) {
		initStack(stack);

		return getDataMap(stack).getBoolean(key);
	}

	public static boolean getBooleanFallback(ItemStack stack, String key, boolean fallback) {
		initStack(stack);

		if (!getDataMap(stack).contains(key)) {
			getDataMap(stack).putBoolean(key, fallback);
		}
		return getDataMap(stack).getBoolean(key);
	}

	public static double getDouble(ItemStack stack, String key) {
		initStack(stack);

		return getDataMap(stack).getDouble(key);
	}

	public static String getString(ItemStack stack, String key) {
		initStack(stack);

		return getDataMap(stack).getString(key);
	}

	public static CompoundNBT getCompound(ItemStack stack, String key) {
		initStack(stack);

		return getDataMap(stack).getCompound(key);
	}

	public static ListNBT getList(ItemStack stack, String key) {
		initStack(stack);

		return getDataMap(stack).getList(key, NBT.TAG_COMPOUND);
	}

	public static void setInt(ItemStack stack, String key, int i) {
		initStack(stack);

		getDataMap(stack).putInt(key, i);
	}

	public static void setBoolean(ItemStack stack, String key, boolean b) {
		initStack(stack);

		getDataMap(stack).putBoolean(key, b);
	}

	public static void setDouble(ItemStack stack, String key, double d) {
		initStack(stack);

		getDataMap(stack).putDouble(key, d);
	}

	public static void setString(ItemStack stack, String key, String s) {
		initStack(stack);

		getDataMap(stack).putString(key, s);
	}

	public static void setCompound(ItemStack stack, String key, CompoundNBT tag) {
		initStack(stack);

		getDataMap(stack).put(key, tag);
	}

	public static void setList(ItemStack stack, String key, ListNBT tag) {
		initStack(stack);

		getDataMap(stack).put(key, tag);
	}

	private static void initStack(ItemStack stack) {
		if (stack.getTag() == null) {
			stack.setTag(new CompoundNBT());
		}

		if (!stack.getTag().contains(DATA_ID)) {
			stack.getTag().put(DATA_ID, new CompoundNBT());
		}
	}

}
