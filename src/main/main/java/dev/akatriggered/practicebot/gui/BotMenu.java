package dev.akatriggered.practicebot.gui;

import dev.akatriggered.practicebot.bot.BotManager;
import dev.akatriggered.practicebot.bot.PracticeBot;
import dev.akatriggered.practicebot.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class BotMenu implements Listener {
    
    private final BotManager botManager;
    
    public BotMenu(BotManager botManager) {
        this.botManager = botManager;
    }
    
    public void openMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "Bot Configuration");
        
        inv.setItem(10, new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("Armor Settings").build());
        inv.setItem(12, new ItemBuilder(Material.ENCHANTED_BOOK).name("Enchant Settings").build());
        inv.setItem(14, new ItemBuilder(Material.POTION).name("Potion Effects").build());
        inv.setItem(16, new ItemBuilder(Material.REDSTONE).name("Behaviour").build());
        
        player.openInventory(inv);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!event.getView().getTitle().equals("Bot Configuration")) return;
        
        event.setCancelled(true);
        
        PracticeBot bot = botManager.getBot(player);
        if (bot == null) return;
        
        int slot = event.getSlot();
        
        switch (slot) {
            case 10 -> new ArmorGUI(botManager).openMenu(player);
            case 12 -> new EnchantGUI(botManager).openMenu(player);
            case 14 -> new EffectGUI(botManager).openMenu(player);
            case 16 -> new BehaviourGUI(botManager).openMenu(player);
        }
    }
}
