package dev.latvian.mods.hdfont.mixin;

import dev.latvian.mods.hdfont.HDFontsClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.FontManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Shadow
	@Final
	private FontManager fontManager;

	/**
	 * @author Lat
	 * @reason HD font override
	 */
	@Inject(method = "initFont", at = @At("RETURN"))
	private void hdfont$initFont(boolean unicode, CallbackInfo ci) {
		if (HDFontsClient.getOption().getValue()) {
			fontManager.setIdOverrides(HDFontsClient.OVERRIDES);
		}
	}
}
