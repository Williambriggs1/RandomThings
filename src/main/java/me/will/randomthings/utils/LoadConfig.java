package me.will.randomthings.utils;

import com.moandjiezana.toml.Toml;
import me.will.randomthings.RandomThings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class LoadConfig {

    public LoadConfig() {
        if (!RandomThings.getPlugin().getDataFolder().exists()) {
            RandomThings.getPlugin().getDataFolder().mkdirs();
        }

        File file = new File(RandomThings.getPlugin().getDataFolder(), "config.toml");

        if (!file.exists()) {
            try {
                Files.copy(Objects.requireNonNull(RandomThings.getPlugin().getResource("config.toml")), file.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        RandomThings.config = new Toml(
                new Toml().read(RandomThings.getPlugin().getResource("config.toml")
                )).read(file);

    }
}
