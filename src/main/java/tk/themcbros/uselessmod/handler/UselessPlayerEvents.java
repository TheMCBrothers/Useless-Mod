package tk.themcbros.uselessmod.handler;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class UselessPlayerEvents {

	public static class ItemCrushedEvent extends PlayerEvent {
		@Nonnull
		private final ItemStack crushing;

		public ItemCrushedEvent(PlayerEntity player, @Nonnull ItemStack crafting) {
			super(player);
			this.crushing = crafting;
		}

		@Nonnull
		public ItemStack getCrushing() {
			return this.crushing;
		}
	}
	
	public static class ItemCompressedEvent extends PlayerEvent {
		@Nonnull
		private final ItemStack compressing;

		public ItemCompressedEvent(PlayerEntity player, @Nonnull ItemStack crafting) {
			super(player);
			this.compressing = crafting;
		}

		@Nonnull
		public ItemStack getCompressing() {
			return this.compressing;
		}
	}

}
