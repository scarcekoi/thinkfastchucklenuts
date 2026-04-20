package dev.scarcekoi.thinkfastchucklenuts.networking;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record FlashbangPayload() implements CustomPacketPayload {
    public static final Type<FlashbangPayload> TYPE =
            new Type<>(ThinkFastChucklenutsPackets.FLASHBANG);

    public static final StreamCodec<RegistryFriendlyByteBuf, FlashbangPayload> CODEC =
            StreamCodec.unit(new FlashbangPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}