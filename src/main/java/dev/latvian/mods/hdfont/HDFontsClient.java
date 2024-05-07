package dev.latvian.mods.hdfont;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.prefs.Preferences;

public class HDFontsClient {
	private static final String OVERRIDE_KEY = "dev_latvian_mods_hdfont_override";
	public static final Boolean localOverride = Optional.ofNullable(System.getProperty("dev.latvian.mods.hdfont.local_override")).map(Boolean::parseBoolean).orElse(null);

	public static final Map<Identifier, Identifier> OVERRIDES = Map.of(
		MinecraftClient.DEFAULT_FONT_ID, HDFonts.DEFAULT,
		MinecraftClient.ALT_TEXT_RENDERER_ID, HDFonts.SGA,
		new Identifier("illageralt"), HDFonts.ILLAGER
	);

	private static SimpleOption<Boolean> option;

	public static SimpleOption<Boolean> getOption() {
		if (option == null) {
			option = SimpleOption.ofBoolean("options.accessibility.hd_font", SimpleOption.emptyTooltip(), localOverride == null || localOverride, value -> {
					if (localOverride == null) {
						Preferences.userRoot().putBoolean(OVERRIDE_KEY, value);
					}

					var mc = MinecraftClient.getInstance();
					((MCAccessor) mc).callInitFont(mc.forcesUnicodeFont());
				}
			);

			if (localOverride == null) {
				option.setValue(Preferences.userRoot().getBoolean(OVERRIDE_KEY, true));
			}
		}

		return option;
	}
}
