package lg.minecraft.mod.cobblenumermodify.mixin;

import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.pokemon.EVs;
import lg.minecraft.mod.cobblenumermodify.CobbleNumberModify;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author langle__
 * @version 1.0
 */
@Mixin(value = EVs.class, remap = false)
public class EVsMixin {

    @Inject(method = "canSet", at = @At("HEAD"), cancellable = true)
    private void canSet(Stat stat, int value, CallbackInfoReturnable<Boolean> cir) {
        if (CobbleNumberModify.isServer() && !CobbleNumberModify.getConfig().ev) return;
        if (value <= -1) return;
        cir.setReturnValue(true);
        cir.cancel();
    }

}
