package dev.doublekekse.boids.config;

import blue.endless.jankson.Comment;
import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonGrammar;
import blue.endless.jankson.api.SyntaxError;
import dev.doublekekse.boids.Boids;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BoidsConfig {
    private static final Jankson JANKSON = new Jankson.Builder().build();
    private static final Path FILE = Boids.configDir().resolve(Boids.MOD_ID + ".json5");

    @Comment("The influence of separation on the overall velocity")
    public float separationInfluence = 0.5f;
    @Comment("The range within which separation affects the boids")
    public float separationRange = 0.9f;

    @Comment("The influence of alignment on the overall velocity")
    public float alignmentInfluence = 8 / 20f;

    @Comment("The influence of cohesion on the overall velocity")
    public float cohesionInfluence = 1 / 20f;


    @Comment("The minimum speed of the boids")
    public float minSpeed = 0.3f;
    @Comment("The maximum speed of the boids")
    public float maxSpeed = 0.8f;

    @Comment("Exclude entities, e.g. 'entity.minecraft.salmon'")
    public List<String> excludeEntities = new ArrayList<>();

    private static BoidsConfig loadFromJSON(String json) throws SyntaxError {
        return JANKSON.fromJson(json, BoidsConfig.class);
    }

    public static BoidsConfig load() {
        if (!Files.exists(FILE)) {
            var config = new BoidsConfig();
            config.save();

            return config;
        }

        try {
            var json = Files.readString(FILE, StandardCharsets.UTF_8);
            return loadFromJSON(json);
        } catch (IOException | SyntaxError e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try {
            //noinspection ResultOfMethodCallIgnored
            FILE.getParent().toFile().mkdirs();
            Files.writeString(FILE, toJSON(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toJSON() {
        return JANKSON.toJson(this).toJson(JsonGrammar.JANKSON);
    }
}
