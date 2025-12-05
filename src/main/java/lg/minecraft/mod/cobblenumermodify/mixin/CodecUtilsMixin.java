package lg.minecraft.mod.cobblenumermodify.mixin;

import com.cobblemon.mod.common.util.codec.CodecUtils;
import com.mojang.serialization.DataResult;
import kotlin.jvm.functions.Function0;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author langle__
 * @version 1.0
 */
@Mixin(value = CodecUtils.class, remap = false)
public abstract class CodecUtilsMixin {

    @Inject(
            method = "dynamicRangeChecker$lambda$0",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void dynamicRangeChecker$lambda$18(Function0<Integer> min, Function0<Integer> max, int number, CallbackInfoReturnable<DataResult<?>> cir) {
        int minAsNum = -1;
        if (minAsNum <= number) {
            DataResult<Integer> success = DataResult.success(number);
            cir.setReturnValue(success);
            cir.cancel();
        } else {
            cir.setReturnValue(DataResult.error(() -> number + " is not in range [" + minAsNum + ":null]"));
            cir.cancel();
        }
    }

}

