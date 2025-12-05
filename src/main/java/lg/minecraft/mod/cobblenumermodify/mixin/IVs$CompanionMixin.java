package lg.minecraft.mod.cobblenumermodify.mixin;

import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.util.DataKeys;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author langle__
 * @version 1.0
 */
@Mixin(value = IVs.Companion.class, remap = false)
public class IVs$CompanionMixin {

    @Inject(method = "getCODEC", at = @At("HEAD"), cancellable = true)
    public void getCODEC(CallbackInfoReturnable<Codec<IVs>> cir) {
        Codec<IVs> codec = RecordCodecBuilder.create(instance -> instance.group(
                Codec.unboundedMap(
                        Stat.getPERMANENT_ONLY_CODEC(), Codec.intRange(0, Integer.MAX_VALUE)
                ).fieldOf(DataKeys.POKEMON_IVS_BASE).forGetter(IVs$CompanionMixin::getStats),
                Codec.unboundedMap(
                        Stat.getPERMANENT_ONLY_CODEC(),
                        Codec.intRange(0, Integer.MAX_VALUE)
                ).fieldOf(DataKeys.POKEMON_IVS_HYPERTRAINED).forGetter(IVs::getHyperTrainedIVs)
        ).apply(instance, IVs$CompanionMixin::createIVsFromMaps));
        cir.setReturnValue(codec);
        cir.cancel();
    }

    @Unique
    private static Map<Stat, Integer> getStats(IVs ivs) {
        Map<Stat, Integer> map = new HashMap<>();
        ivs.forEach(iv -> map.put(iv.getKey(), iv.getValue()));
        return map;
    }

    @Unique
    private static IVs createIVsFromMaps(Map<Stat, Integer> stats, Map<Stat, Integer> hyperTrained) {
        IVs ivs = new IVs();
        stats.forEach(ivs::set);
        hyperTrained.forEach(ivs::setHyperTrainedIV);
        return ivs;
    }

}
