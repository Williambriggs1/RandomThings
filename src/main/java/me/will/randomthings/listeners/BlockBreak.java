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
        Toml config = RandomThings.config;

        if (config.getList("List").contains(event.getBlock().getBiome() + "-" + event.getBlock().getType())) {

            String id = event.getBlock().getBiome() + "-" + event.getBlock().getType();
            Object[] data = config.getList(id).toArray();


            if (!(Math.random() < (Float.parseFloat((String) data[1])))) { event.setDropItems(true); } else {
                MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get("CONSUMABLE"), (String) data[0]);
                if (mmoitem == null) { return; }
                ItemStack item = mmoitem.newBuilder().build();
                if (item == null) { return; }
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
                event.setDropItems(false);
            }

        }


    }


}