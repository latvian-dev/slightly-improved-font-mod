package dev.latvian.mods.hdfont;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.prefs.Preferences;

public class HDFontsClient {
	private static final String OVERRIDE_KEY = "dev_latvian_mods_hdfont_override";
	public static boolean override = Preferences.userRoot().getBoolean(OVERRIDE_KEY, true);

	public static final Map<Identifier, Identifier> OVERRIDES = Map.of(
		MinecraftClient.DEFAULT_FONT_ID, HDFonts.DEFAULT,
		MinecraftClient.ALT_TEXT_RENDERER_ID, HDFonts.SGA,
		new Identifier("illageralt"), HDFonts.ILLAGER
	);

	public static final SimpleOption<Boolean> OPTION = SimpleOption.ofBoolean("options.accessibility.hd_font", SimpleOption.emptyTooltip(), override, value -> {
			if (override != value) {
				override = value;
				Preferences.userRoot().putBoolean(OVERRIDE_KEY, value);
				var mc = MinecraftClient.getInstance();
				((MCAccessor) mc).callInitFont(mc.forcesUnicodeFont());
			}
		}
	);
}
