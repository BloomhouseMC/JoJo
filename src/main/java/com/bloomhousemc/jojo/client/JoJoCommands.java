package com.bloomhousemc.jojo.client;

import com.bloomhousemc.jojo.JoJo;
import com.bloomhousemc.jojo.common.hamon.Hamon;
import com.bloomhousemc.jojo.common.hamon.HamonUtils;
import com.bloomhousemc.jojo.common.stand.Stand;
import com.bloomhousemc.jojo.common.stand.StandUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.server.command.CommandManager.argument;

public class JoJoCommands implements CommandRegistrationCallback {
    public static void init(){
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            LiteralCommandNode<ServerCommandSource> phantombloodNode = CommandManager
            .literal("phantomblood").requires(source -> source.hasPermissionLevel(2)).build();
            LiteralCommandNode<ServerCommandSource> standNode = CommandManager
            .literal("stand").build();
            LiteralCommandNode<ServerCommandSource> removeNode = CommandManager
            .literal("remove")
            .executes(context -> JoJoCommand.removeStand(context, context.getSource().getPlayer())).build();
            LiteralCommandNode<ServerCommandSource> setNode = CommandManager
            .literal("set").build();
            LiteralCommandNode<ServerCommandSource> setHamonNode = CommandManager
            .literal("hamon")
            .executes(context -> JoJoCommand.hamon(context, context.getSource().getPlayer())).build();
            ArgumentCommandNode<ServerCommandSource, EntitySelector> hamonNode =
            argument("player", EntityArgumentType.player())
            .executes(context -> JoJoCommand.hamon(context, EntityArgumentType.getPlayer(context, "player"))).build();
            ArgumentCommandNode<ServerCommandSource, EntitySelector> playerRemoveNode =
            argument("player", EntityArgumentType.player())
            .executes(context -> JoJoCommand.removeStand(context, EntityArgumentType.getPlayer(context, "player"))).build();
            ArgumentCommandNode<ServerCommandSource, EntitySelector> playerSetNode =
            argument("player", EntityArgumentType.player()).build();
            LiteralCommandNode<ServerCommandSource> setTheWorld = CommandManager.literal("the_world").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.THE_WORLD)).build();
            LiteralCommandNode<ServerCommandSource> setTheSun = CommandManager.literal("the_sun").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.THE_SUN)).build();
            LiteralCommandNode<ServerCommandSource> setStarsPlatinum = CommandManager.literal("star_platinum").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.STAR_PLATINUM)).build();
            LiteralCommandNode<ServerCommandSource> setWeatherReport = CommandManager.literal("weather_report").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.WEATHER_REPORT)).build();
            LiteralCommandNode<ServerCommandSource> setKillerQueen = CommandManager.literal("killer_queen").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.KILLER_QUEEN)).build();
            LiteralCommandNode<ServerCommandSource> setDarkBlueMoon = CommandManager.literal("dark_blue_moon").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.DARK_BLUE_MOON)).build();
            LiteralCommandNode<ServerCommandSource> setCrazyDiamond = CommandManager.literal("crazy_diamond").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.CRAZY_DIAMOND)).build();
            LiteralCommandNode<ServerCommandSource> setKingCrimson = CommandManager.literal("king_crimson").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.KING_CRIMSON)).build();
            LiteralCommandNode<ServerCommandSource> setTwentyCenturyBoy = CommandManager.literal("20th_century_boy").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.TWENTY_CENTURY_BOY)).build();
            LiteralCommandNode<ServerCommandSource> setHierophantGreen = CommandManager.literal("hierophant_green").executes(context -> JoJoCommand.setStand(context, EntityArgumentType.getPlayer(context, "player"), Stand.HIEROPHANT_GREEN)).build();



            dispatcher.getRoot().addChild(phantombloodNode);
            phantombloodNode.addChild(standNode);
            phantombloodNode.addChild(setHamonNode);
            setHamonNode.addChild(hamonNode);
            standNode.addChild(removeNode);
            standNode.addChild(setNode);
            removeNode.addChild(playerRemoveNode);
            setNode.addChild(playerSetNode);
            playerSetNode.addChild(setTheWorld);
            playerSetNode.addChild(setTheSun);
            playerSetNode.addChild(setStarsPlatinum);
            playerSetNode.addChild(setWeatherReport);
            playerSetNode.addChild(setKillerQueen);
            playerSetNode.addChild(setDarkBlueMoon);
            playerSetNode.addChild(setCrazyDiamond);
            playerSetNode.addChild(setKingCrimson);
            playerSetNode.addChild(setTwentyCenturyBoy);
            playerSetNode.addChild(setHierophantGreen);
        });
    }
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {

    }
    public class JoJoCommand {
        public static class StandArgumentType implements ArgumentType<Stand> {

            @Override
            public Stand parse(StringReader reader) throws CommandSyntaxException {
                Identifier identifier = Identifier.fromCommandInput(reader);
                return null;
            }
            public static StandArgumentType stand() {
                return new StandArgumentType();
            }
            public static Stand getStand(CommandContext<ServerCommandSource> commandContext, String string) {
                return commandContext.getArgument(string, Stand.class);
            }
            @Override
            public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
                return CommandSource.suggestIdentifiers(STAND.getIds(), builder);
            }
        }
        public static void init() {
            ArgumentTypes.register("mrsterner.phantomblood:stand", StandArgumentType.class, new ConstantArgumentSerializer<>(StandArgumentType::stand));
        }
        public static int setStand(CommandContext<ServerCommandSource> context, ServerPlayerEntity player, Stand stand) throws CommandSyntaxException {
            context.getSource().sendFeedback(new TranslatableText("command.phantomblood.setstand."+stand, player.getDisplayName()).setStyle(Style.EMPTY.withBold(true)), false);
            StandUtils.setStand(player, stand);
            StandUtils.setStandLevel(player, 1);
            return 0;
        }
        public static int removeStand(CommandContext<ServerCommandSource> context, ServerPlayerEntity player) throws CommandSyntaxException {
            context.getSource().sendFeedback(new TranslatableText("command.phantomblood.removestand", player.getDisplayName()).setStyle(Style.EMPTY.withBold(true)), false);
            StandUtils.setStand(player, Stand.NONE);
            StandUtils.setStandLevel(player, 0);
            StandUtils.setStandActive(player, false);
            return 0;
        }
        public static int hamon(CommandContext<ServerCommandSource> context, ServerPlayerEntity player) throws CommandSyntaxException {
            context.getSource().sendFeedback(new TranslatableText("command.phantomblood.hamon", player.getDisplayName()).setStyle(Style.EMPTY.withBold(true)), false);
            HamonUtils.setHamon(player, Hamon.HAMON);
            HamonUtils.setHamonLevel(player, 0);
            return 0;
        }


        public static final Registry<Stand> STAND = FabricRegistryBuilder.createSimple(Stand.class, new Identifier(JoJo.MODID, "stand")).buildAndRegister();
    }

}