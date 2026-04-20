package dev.scarcekoi.thinkfastchucklenuts;

import dev.scarcekoi.thinkfastchucklenuts.networking.FlashbangCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ThinkFastChucklenutsCommands {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            FlashbangCommand.register(dispatcher);
        });
    }
}