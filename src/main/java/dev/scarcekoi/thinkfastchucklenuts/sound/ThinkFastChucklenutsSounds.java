package dev.scarcekoi.thinkfastchucklenuts.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ThinkFastChucklenutsSounds {
    public static final Identifier FLASHBANG_ID =
            Identifier.fromNamespaceAndPath("thinkfastchucklenuts", "flashbang");

    public static final SoundEvent FLASHBANG = Registry.register(
            BuiltInRegistries.SOUND_EVENT,
            FLASHBANG_ID,
            SoundEvent.createVariableRangeEvent(FLASHBANG_ID)
    );

    public static void register() {}
}