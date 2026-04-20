package dev.scarcekoi.thinkfastchucklenuts;

import dev.scarcekoi.thinkfastchucklenuts.networking.ThinkFastChucklenutsNetworking;
import dev.scarcekoi.thinkfastchucklenuts.sound.ThinkFastChucklenutsSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThinkFastChucklenuts implements ModInitializer {
	public static final String MOD_ID = "thinkfastchucklenuts";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ThinkFastChucklenutsSounds.register();
		ThinkFastChucklenutsNetworking.register();
		ThinkFastChucklenutsCommands.register();
		FlashbangRandomizer.register();
	}
}