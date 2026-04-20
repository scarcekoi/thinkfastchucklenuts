package dev.scarcekoi.thinkfastchucklenuts.client;

import dev.scarcekoi.thinkfastchucklenuts.networking.FlashbangPayload;
import dev.scarcekoi.thinkfastchucklenuts.sound.ThinkFastChucklenutsSounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

public class ThinkFastChucklenutsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(FlashbangPayload.TYPE, (payload, context) -> {
			context.client().execute(() -> {
				Minecraft client = Minecraft.getInstance();

				if (client.player != null) {
					client.level.playLocalSound(
							client.player.getX(),
							client.player.getY(),
							client.player.getZ(),
							ThinkFastChucklenutsSounds.FLASHBANG,
							net.minecraft.sounds.SoundSource.MASTER,
							1f,
							1f,
							false
					);
				}

				FlashbangOverlay.trigger();
			});
		});

		HudElementRegistry.attachElementAfter(
				VanillaHudElements.MISC_OVERLAYS,
				Identifier.fromNamespaceAndPath("thinkfastchucklenuts", "flashbang_overlay"),
				(graphics, tickCounter) -> FlashbangOverlay.render(graphics, tickCounter.getGameTimeDeltaTicks())
		);
	}
}