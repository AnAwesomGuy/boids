package dev.doublekekse.boids.neoforge;

import dev.doublekekse.boids.Boids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.nio.file.Path;

@Mod(Boids.MOD_ID)
@EventBusSubscriber
public class BoidsImpl {
    @SubscribeEvent
    private static void registerCommands(RegisterCommandsEvent event) {
        Boids.registerCommands(event.getDispatcher());
    }

    public static Path configDir() {
        return FMLPaths.CONFIGDIR.get();
    }
}
