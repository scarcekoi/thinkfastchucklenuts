package dev.scarcekoi.thinkfastchucklenuts;

import dev.scarcekoi.thinkfastchucklenuts.networking.ThinkFastChucklenutsNetworking;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;
import java.util.Random;

public class FlashbangRandomizer {

    private static final Random RANDOM = new Random();

    private static long ticksUntilNext = 0;

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(FlashbangRandomizer::onTick);
        resetTimer();
    }

    private static void onTick(MinecraftServer server) {
        if (ticksUntilNext > 0) {
            ticksUntilNext--;
            return;
        }

        List<ServerPlayer> players = server.getPlayerList().getPlayers();

        if (players.isEmpty()) {
            resetTimer();
            return;
        }

        if (RANDOM.nextFloat() < 0.05f) {
            ServerPlayer target = players.get(RANDOM.nextInt(players.size()));
            ThinkFastChucklenutsNetworking.flashPlayer(target);
        }

        resetTimer();
    }

    private static void resetTimer() {
        ticksUntilNext = 900 + RANDOM.nextInt(900);
    }
}