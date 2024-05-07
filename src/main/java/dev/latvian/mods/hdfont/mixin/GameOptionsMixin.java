package dev.latvian.mods.hdfont.mixin;

import dev.latvian.mods.hdfont.HDFontsClient;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
	@Inject(method = "accept", at = @At("RETURN"))
	private void hdfont$accept(GameOptions.Visitor visitor, CallbackInfo ci) {
		if (HDFontsClient.localOverride != null) {
			visitor.accept("hdfont", HDFontsClient.getOption());
		}
	}
}
