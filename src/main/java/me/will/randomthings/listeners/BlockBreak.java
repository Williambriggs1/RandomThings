package me.will.randomthings.listeners;

import com.moandjiezana.toml.Toml;
import me.will.randomthings.RandomThings;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreak implements Listener {

    @EventHandler
    public void playerBreak(BlockBreakEvent event) {

        Toml toml = RandomThings.getPlugin().getTomlConfig();

        if (toml.getList("Botany.List").contains(event.getBlock().getBiome().toString().toUpperCase() + "_" + event.getBlock().getType().toString().toUpperCase())) {

            String id = "Botany." + event.getBlock().getBiome().toString().toUpperCase() + "_" + event.getBlock().getType().toString().toUpperCase();
            Object[] data = toml.getList(id).toArray();


            if (!(Math.random() < (Float.parseFloat((String) data[1])))) { event.setDropItems(true); } else {
                MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get("CONSUMABLE"), (String) data[0]);
                if (mmoitem == null) { return; }
                ItemStack item = mmoitem.newBuilder().build();
                if (item == null) { return; }
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), item);
                event.setDropItems(false);
            }

        }


    }


}
