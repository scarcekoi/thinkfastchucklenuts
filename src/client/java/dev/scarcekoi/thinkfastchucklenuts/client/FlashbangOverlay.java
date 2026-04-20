package dev.scarcekoi.thinkfastchucklenuts.client;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Util;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class FlashbangOverlay {

    private static class Flash {
        long startTime;

        Flash(long startTime) {
            this.startTime = startTime;
        }
    }

    private static final CopyOnWriteArrayList<Flash> flashes = new CopyOnWriteArrayList<>();

    private static final long DELAY_MS = 1240;
    private static final long TOTAL_MS = 5851;
    private static final long FADE_MS = TOTAL_MS - DELAY_MS;

    public static void trigger() {
        flashes.add(new Flash(Util.getMillis()));
    }

    public static void render(GuiGraphicsExtractor graphics, float tickDelta) {
        if (flashes.isEmpty()) {
            return;
        }

        long now = Util.getMillis();
        Window window = Minecraft.getInstance().getWindow();

        int width = window.getGuiScaledWidth();
        int height = window.getGuiScaledHeight();

        float strongestAlpha = 0f;

        Iterator<Flash> it = flashes.iterator();

        while (it.hasNext()) {
            Flash f = it.next();

            long elapsed = now - f.startTime;

            if (elapsed < DELAY_MS) {
                continue;
            }

            long fadeElapsed = elapsed - DELAY_MS;

            if (fadeElapsed >= FADE_MS) {
                flashes.remove(f);
                continue;
            }

            float progress = fadeElapsed / (float) FADE_MS;
            float alpha = 1f - progress;

            if (alpha > strongestAlpha) {
                strongestAlpha = alpha;
            }
        }

        if (strongestAlpha <= 0f) {
            return;
        }

        int color = ((int) (strongestAlpha * 255) << 24) | 0xFFFFFF;

        graphics.fill(0, 0, width, height, color);
    }
}