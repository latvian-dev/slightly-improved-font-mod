package dev.latvian.mods.hdfont.mixin;

import dev.latvian.mods.hdfont.MCAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientAccessor implements MCAccessor {
	@Override
	@Invoker
	public abstract void callInitFont(boolean forcesUnicode);
}
