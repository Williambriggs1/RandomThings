package me.will.randomthings.utils;

import com.moandjiezana.toml.Toml;
import me.will.randomthings.RandomThings;

import java.io.File;

public class ReloadConfig {

    public ReloadConfig(){
        File file = new File(RandomThings.getPlugin().getDataFolder(), "config.toml");
        RandomThings.config =  new Toml(
                new Toml().read(RandomThings.getPlugin().getResource("config.toml"))
        ).read(file);
    }

}
