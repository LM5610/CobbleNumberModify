package lg.minecraft.mod.cobblenumermodify.mixin;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokemon.experience.ExperienceGroup;
import com.cobblemon.mod.common.pokemon.Pokemon;
import lg.minecraft.mod.cobblenumermodify.CobbleNumberModify;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author langle__
 * @version 1.0
 */
@Mixin(value = Pokemon.class, remap = false)
public abstract class PokemonMixin {

    @Shadow(remap = false) private int level;

    @Shadow(remap = false) public abstract int getMaxHealth();

    @Shadow(remap = false) public abstract void setCurrentHealth(int value);

    @Shadow(remap = false) public abstract ExperienceGroup getExperienceGroup();

    @Shadow(remap = false) public abstract void setExperience$common(int value);

    @Shadow(remap = false) public abstract int getCurrentHealth();

    @Shadow(remap = false) public abstract int getExperience();

    /**
     * Sets the level of the Pokémon, with a custom maximum cap.
     * @param newLevel The desired level to set.
     */
    @Inject(method = "setLevel(I)V", at = @At("HEAD"), cancellable = true)
    public void setLevel(int newLevel, CallbackInfo ci) {
        if (CobbleNumberModify.isServer() && !CobbleNumberModify.getConfig().level) return;
        int boundedValue = Math.max(1, newLevel);
        float hpRatio = Math.max(0F, Math.min(1F, (float) getCurrentHealth() / (float) getMaxHealth()));
        level = boundedValue;
        if (getExperienceGroup().getLevel(getExperience()) != boundedValue || boundedValue > Cobblemon.config.getMaxPokemonLevel()) {
            setExperience$common(getExperienceGroup().getExperience(boundedValue));
        }

        int maxHealth = getMaxHealth();
        int currentHealth = (int) Math.max(0, Math.min(Math.ceil(hpRatio * maxHealth), maxHealth));

        setCurrentHealth(currentHealth);

        ci.cancel();
    }

    @Inject(method = "getLevel()I", at = @At("HEAD"), cancellable = true)
    private void getLevel(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(level);
    }

}
