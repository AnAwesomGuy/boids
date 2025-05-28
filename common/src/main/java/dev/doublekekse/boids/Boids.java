package dev.doublekekse.boids;

import com.mojang.brigadier.CommandDispatcher;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.doublekekse.boids.config.BoidsConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.nio.file.Path;

import static net.minecraft.commands.Commands.literal;

public class Boids {
    public static final String MOD_ID = "boids";

    public static BoidsConfig CONFIG = BoidsConfig.load();

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal(Boids.MOD_ID).requires(source -> source.hasPermission(2)).then(literal("config").then(literal("reload").executes(ctx -> {
                    CONFIG = BoidsConfig.load();

                    ctx.getSource().sendSuccess(() -> Component.translatable("commands.boids.config.reload"), true);

                    return 1;
                }))));
    }

    @ExpectPlatform
    public static Path configDir() {
        throw new AssertionError();
    }
}
