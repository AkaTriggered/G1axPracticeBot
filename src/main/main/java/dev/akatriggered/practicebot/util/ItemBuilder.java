package dev.akatriggered.practicebot.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    
    private final ItemStack item;
    
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
    }
    
    public ItemBuilder name(String name) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§f" + name);
            item.setItemMeta(meta);
        }
        return this;
    }
    
    public ItemBuilder lore(String... lore) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> loreList = Arrays.stream(lore).map(s -> "§7" + s).toList();
            meta.setLore(loreList);
            item.setItemMeta(meta);
        }
        return this;
    }
    
    public ItemBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }
    
    public ItemStack build() {
        return item;
    }
}
