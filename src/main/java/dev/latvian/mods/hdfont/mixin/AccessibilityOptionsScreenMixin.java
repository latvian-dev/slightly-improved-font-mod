package dev.latvian.mods.hdfont.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.latvian.mods.hdfont.HDFontsClient;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AccessibilityOptionsScreen.class)
public class AccessibilityOptionsScreenMixin {
	@ModifyReturnValue(method = "getOptions", at = @At("RETURN"))
	private static SimpleOption<?>[] hdfont$getOptions(SimpleOption<?>[] options) {
		var newOptions = new SimpleOption<?>[options.length + 1];
		System.arraycopy(options, 0, newOptions, 0, options.length);
		newOptions[options.length] = HDFontsClient.getOption();
		return newOptions;
	}
}
