package me.will.randomthings.commands;

import me.will.randomthings.utils.ReloadConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) { return true; }

            if (args.length > 0 && args[0].equalsIgnoreCase("reload")){

                new ReloadConfig();

                player.sendMessage("Plugin Reloaded!");

            } else {
                player.sendMessage("Usage: /randomthings reload");
            }


        return true;
    }
}
