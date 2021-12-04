package com.bloomhousemc.jojo;

import com.bloomhousemc.jojo.client.JoJoCommands;
import com.bloomhousemc.jojo.common.JoJoEvents;
import com.bloomhousemc.jojo.common.hamon.HamonUtils;
import com.bloomhousemc.jojo.common.registry.JoJoEnchantments;
import com.bloomhousemc.jojo.common.registry.JoJoObjects;
import com.bloomhousemc.jojo.common.registry.JoJoSoundEvents;
import com.bloomhousemc.jojo.common.registry.JoJoStatusEffects;
import com.bloomhousemc.jojo.common.stand.StandPunchHandler;
import com.bloomhousemc.jojo.common.stand.StandUtils;
import com.bloomhousemc.jojo.common.timestop.StoppedTimeDamageHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JoJo implements ModInitializer {
	public static final String MODID = "jojo";
	public static final ItemGroup JOJO_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, MODID), () -> new ItemStack(JoJoObjects.ARROW_HEAD));
	public static JoJoConfig config;
	@Override
	public void onInitialize() {
		AutoConfig.register(JoJoConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(JoJoConfig.class).getConfig();
		JoJoObjects.init();
		JoJoStatusEffects.init();
		JoJoSoundEvents.init();
		JoJoCommands.init();
		JoJoEvents.init();
		JoJoEnchantments.init();

		//Keybind Serverside
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("phantomblood:use_ability"), (server, player, handler, buf, response) -> {
			StandUtils.getStand(player).handler.receive(server, player, handler, buf, response);
		});
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("phantomblood:toggle_stand"), (server, player, handler, buf, response) -> {
			server.execute(() -> StandUtils.toggleStandActive(player));
		});
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("phantomblood:toggle_hamon"), (server, player, handler, buf, response) -> {
			server.execute(() -> HamonUtils.toggleHamonActive(player));
		});
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("phantomblood:change_stand_mode"), (server, player, handler, buf, response) -> {
			server.execute(() -> StandUtils.setToNextStandMode(player));
		});

		new StoppedTimeDamageHandler().registerCallbacks();

		ServerTickEvents.START_WORLD_TICK.register((new StandPunchHandler()));
	}
}
