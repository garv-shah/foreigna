package com.garv.foreigna.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class talk {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        KeyBinding keyBinding;

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.foreigna.jump", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "teleport.foreigna" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                assert client.player != null;
                client.player.sendMessage(new LiteralText("Key 1 was pressed!"), false);

                MinecraftClient mc = MinecraftClient.getInstance();
                assert mc.player != null;
                mc.player.setPos(mc.player.getX(), mc.player.getY() + 5, mc.player.getZ());
            }
        });

        dispatcher.register(CommandManager.literal("foo").executes(context -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            assert mc.player != null;
            mc.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of("hello player, welcome to bean"), mc.player.getUuid());
            java.util.List<String> chatHistory = mc.inGameHud.getChatHud().getMessageHistory();
            mc.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of(String.valueOf(chatHistory.get(chatHistory.size() - 1))), mc.player.getUuid());
            //teleport the player five blocks up
            mc.player.setPos(mc.player.getX(), mc.player.getY() + 5, mc.player.getZ());

            return 1;
        }));
    }
}
