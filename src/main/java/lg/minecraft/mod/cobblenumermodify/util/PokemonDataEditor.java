package lg.minecraft.mod.cobblenumermodify.util;

import com.cobblemon.mod.common.pokemon.EVs;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import kotlin.ranges.IntRange;

import java.lang.reflect.Field;

/**
 * @author langle__
 * @version 1.0
 */
public class PokemonDataEditor {

    public static boolean setPokemonLevel(Pokemon pokemon, int newLevel) {
        try {
            Field levelField = pokemon.getClass().getDeclaredField("level");
            levelField.setAccessible(true);
            levelField.setInt(pokemon, newLevel);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean setPokemonIV(Pokemon pokemon, int newMaxValue) {
        IVs ivs = pokemon.getIvs();
        try {
            Field acceptableRangeField = ivs.getClass().getDeclaredField("acceptableRange");
            acceptableRangeField.setAccessible(true);
            acceptableRangeField.set(ivs, new IntRange(0, newMaxValue));
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean setPokemonEV(Pokemon pokemon, int newMaxValue) {
        EVs evs = pokemon.getEvs();
        try {
            Field acceptableRangeField = evs.getClass().getDeclaredField("acceptableRange");
            acceptableRangeField.setAccessible(true);
            acceptableRangeField.set(evs, new IntRange(0, newMaxValue));
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

}
