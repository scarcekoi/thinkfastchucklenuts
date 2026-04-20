package dev.scarcekoi.thinkfastchucklenuts.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;
import java.util.Random;

public class ThinkFastChucklenutsNetworking {
    private static final Random RANDOM = new Random();

    public static void register() {
        PayloadTypeRegistry.clientboundPlay().register(FlashbangPayload.TYPE, FlashbangPayload.CODEC);
    }

    public static void flashPlayer(ServerPlayer player) {
        ServerPlayNetworking.send(player, new FlashbangPayload());
    }

    public static void flashRandomPlayer(List<ServerPlayer> players) {
        if (players.isEmpty()) {
            return;
        }

        ServerPlayer randomPlayer = players.get(RANDOM.nextInt(players.size()));
        ServerPlayNetworking.send(randomPlayer, new FlashbangPayload());
    }
}