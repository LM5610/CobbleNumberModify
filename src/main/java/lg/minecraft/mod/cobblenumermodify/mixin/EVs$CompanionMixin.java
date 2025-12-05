package lg.minecraft.mod.cobblenumermodify.mixin;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.pokemon.EVs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author langle__
 * @version 1.0
 */
@Mixin(value = EVs.Companion.class, remap = false)
public class EVs$CompanionMixin {

    @Inject(method = "getCODEC", at = @At("HEAD"), cancellable = true)
    public void getCODEC(CallbackInfoReturnable<Codec<EVs>> cir) {
        Codec<EVs> customCodec = Codec.unboundedMap(
                Stat.Companion.getPERMANENT_ONLY_CODEC(),
                Codec.intRange(0, Integer.MAX_VALUE)
        ).comapFlatMap(
                map -> {
                    EVs evs = Cobblemon.INSTANCE.getStatProvider().createEmptyEVs();
                    map.forEach(evs::set);
                    return DataResult.success(evs);
                },
                evs -> {
                    Map<Stat, Integer> map = new HashMap<>();
                    evs.forEach(entry -> map.put(entry.getKey(), entry.getValue()));
                    return map;
                }
        );
        cir.setReturnValue(customCodec);
        cir.cancel();
    }

}
