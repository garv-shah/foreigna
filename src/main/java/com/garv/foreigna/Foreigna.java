package com.garv.foreigna;

import com.garv.foreigna.command.talk;
import com.garv.foreigna.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.C2SPlayChannelEvents;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.network.C2SPacketTypeCallback;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foreigna implements ModInitializer {
	public static final String MOD_ID = "foreigna";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerModItems();

		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			talk.register(dispatcher);
		});

		// check the minecraft chat and see message if "hello" was sent, and if so, reply with "hi"

	}
}
