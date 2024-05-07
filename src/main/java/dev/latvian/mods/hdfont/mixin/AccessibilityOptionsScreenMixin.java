package dev.latvian.mods.hdfont.mixin;

import dev.latvian.mods.hdfont.HDFontsClient;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AccessibilityOptionsScreen.class)
public class AccessibilityOptionsScreenMixin {
	@Inject(method = "getOptions", at = @At("RETURN"), cancellable = true)
	private static void hdfont$getOptions(GameOptions gameOptions, CallbackInfoReturnable<SimpleOption<?>[]> cir) {
		var options = cir.getReturnValue();
		var newOptions = new SimpleOption<?>[options.length + 1];
		System.arraycopy(options, 0, newOptions, 0, options.length);
		newOptions[options.length] = HDFontsClient.OPTION;
		cir.setReturnValue(newOptions);
	}
}
