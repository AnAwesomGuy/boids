package dev.doublekekse.boids.fabric;

import dev.doublekekse.boids.Boids;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class BoidsImpl implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, context, selection) -> Boids.registerCommands(dispatcher));
    }

    public static Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
