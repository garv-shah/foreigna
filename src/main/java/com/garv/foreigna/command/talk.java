package com.garv.foreigna.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class talk {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("foo").executes(context -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            assert mc.player != null;
            mc.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of("hello player, welcome to bean"), mc.player.getUuid());
            //teleport the player five blocks up
            mc.player.setPos(mc.player.getX(), mc.player.getY() + 5, mc.player.getZ());
            return 1;
        }));
    }
}
