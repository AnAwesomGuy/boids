package dev.doublekekse.boids.mixin.compat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.doublekekse.boids.duck.BoidsFish;
import koala.fishingreal.FishingReal;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingReal.class)
public class RealFishingMixin {
    @ModifyExpressionValue(method = "convertItemStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"))
    private static Entity disable(Entity original) {
        if (original instanceof BoidsFish)
            ((BoidsFish)original).boids$disableBoids();
        return original;
    }
}
