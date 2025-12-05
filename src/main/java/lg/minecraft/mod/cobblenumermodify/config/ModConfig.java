package lg.minecraft.mod.cobblenumermodify.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lg.minecraft.mod.cobblenumermodify.CobbleNumberModify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author langle__
 * @version 1.0
 */
public class ModConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(CobbleNumberModify.class);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public boolean level = true;
    public boolean iv = true;
    public boolean ev = true;

    public static ModConfig loadConfig(File file) {
        ModConfig config;

        if (file.exists() && file.isFile()) {
            try (
                    FileInputStream fileInputStream = new FileInputStream(file);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ) {
                config = GSON.fromJson(bufferedReader, ModConfig.class);
            } catch (IOException e) {
                LOGGER.error("Failed to load config", e);
                return new ModConfig();
            }
        } else {
            config = new ModConfig();
        }

        config.saveConfig(file);

        return config;
    }

    public void saveConfig(File config) {
        try (
                FileOutputStream stream = new FileOutputStream(config);
                Writer writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
        ) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            LOGGER.error("Failed to save config", e);
        }
    }

}
