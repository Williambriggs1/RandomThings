package me.will.randomthings;

import com.moandjiezana.toml.Toml;
import me.will.randomthings.commands.ReloadCommand;
import me.will.randomthings.listeners.BlockBreak;
import me.will.randomthings.utils.LoadConfig;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RandomThings extends JavaPlugin {

    private static RandomThings plugin;
    public static Toml config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        registerListeners();

        new LoadConfig();



        Objects.requireNonNull(this.getCommand("randomthings")).setExecutor(new ReloadCommand());

    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new BlockBreak(), this);
    }

    public static RandomThings getPlugin() {
        return plugin;
    }


}
