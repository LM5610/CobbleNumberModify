package lg.minecraft.mod.cobblenumermodify;

import lg.minecraft.mod.cobblenumermodify.config.ModConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CobbleNumberModify implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(CobbleNumberModify.class);

    private static ModConfig config;

    @Override
    public void onInitialize() {
        if (isClient()) {
            config = new ModConfig();
        } else {
            config = ModConfig.loadConfig(new File(FabricLoader.getInstance().getConfigDir().toFile(), "CobbleNumberModify.json"));
        }
    }

    public static ModConfig getConfig() {
        return config;
    }

    public static boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    public static boolean isServer() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
    }

}
