package com.bloomhousemc.jojo;

import com.bloomhousemc.jojo.client.JoJoClientEvents;
import com.bloomhousemc.jojo.client.shader.ZaWarudoShader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.UUID;

public class JoJoClient implements ClientModInitializer {
	ZaWarudoShader zaWarudoShader = new ZaWarudoShader();
	@Override
	public void onInitializeClient() {
		JoJoClientEvents.init();

		ClientPlayNetworking.registerGlobalReceiver(new Identifier(JoJo.MODID,"stop_time"), (client, handler, buf, responseSender) -> {
			UUID uuid = buf.readUuid();
			long ticks = buf.readVarLong();
			client.execute(() -> {
				World world = client.world;
				if (world != null) {
					zaWarudoShader.player = Optional.ofNullable(world.getPlayerByUuid(uuid)).orElse(client.player);
					zaWarudoShader.effectLength = ticks;
					zaWarudoShader.shouldRender = true;
				}
			});
		});
		zaWarudoShader.registerCallbacks();
	}
}
