package me.will.randomthings;

import com.moandjiezana.toml.Toml;
import me.will.randomthings.commands.ReloadCommand;
import me.will.randomthings.listeners.BlockBreak;
import org.bukkit.plugin.PluginManager;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class RandomThings extends JavaPlugin {

    private static RandomThings plugin;
    public static Toml config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        registerListeners();

        config = loadConfig();


        this.getCommand("randomthings").setExecutor(new ReloadCommand());

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Toml loadConfig() {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdirs(); // Creating the directory as it may not exist
        }
        File file = new File(getDataFolder(), "config.toml"); // Assign a variable to the file
        if(!file.exists()) {
            try {
                Files.copy(getResource("config.toml"), file.toPath()); // Copy the file out of our jar into our plugins Data Folder
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return new Toml(
                new Toml().read(getResource("config.toml"))) // We'll use our internal file as default to prevent errors
                .read(file); // Reads the file from the Data Folder of our plugin
    }

    public Toml getTomlConfig() { return config; }

    public Toml reloadPluginConfig() {
        File file = new File(getDataFolder(), "config.toml");
        return new Toml(
                new Toml().read(getResource("config.toml"))) // We'll use our internal file as default to prevent errors
                .read(file);
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new BlockBreak(), this);
    }

    public static RandomThings getPlugin() {
        return plugin;
    }


}
