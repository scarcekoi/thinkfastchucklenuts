package dev.scarcekoi.thinkfastchucklenuts.networking;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;

public class FlashbangCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("flashbang")
                        .requires(source ->
                                source.permissions().hasPermission(
                                        new Permission.HasCommandLevel(PermissionLevel.ALL)
                                )
                        )
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayer();
                            if (player != null) {
                                ThinkFastChucklenutsNetworking.flashPlayer(player);
                            }
                            return 1;
                        })
        );
    }
}
