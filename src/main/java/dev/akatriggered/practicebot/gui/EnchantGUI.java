package dev.akatriggered.practicebot.gui;

import dev.akatriggered.practicebot.bot.BotEquipment;
import dev.akatriggered.practicebot.bot.BotManager;
import dev.akatriggered.practicebot.bot.PracticeBot;
import dev.akatriggered.practicebot.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class EnchantGUI implements Listener {
    
    private final BotManager botManager;
    
    public EnchantGUI(BotManager botManager) {
        this.botManager = botManager;
    }
    
    public void openMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "Enchant Settings");
        
        inv.setItem(10, new ItemBuilder(Material.DIAMOND_HELMET).name("Helmet Enchants").build());
        inv.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("Chestplate Enchants").build());
        inv.setItem(12, new ItemBuilder(Material.DIAMOND_LEGGINGS).name("Leggings Enchants").build());
        inv.setItem(13, new ItemBuilder(Material.DIAMOND_BOOTS).name("Boots Enchants").build());
        
        inv.setItem(49, new ItemBuilder(Material.ARROW).name("Back").build());
        
        player.openInventory(inv);
    }
    
    public void openEnchantTypeMenu(Player player, BotEquipment.ArmorSlot slot) {
        Inventory inv = Bukkit.createInventory(null, 27, slot.name() + " Enchants");
        
        inv.setItem(10, new ItemBuilder(Material.ENCHANTED_BOOK).name("Protection IV").build());
        inv.setItem(11, new ItemBuilder(Material.ENCHANTED_BOOK).name("Blast Protection IV").build());
        inv.setItem(12, new ItemBuilder(Material.ENCHANTED_BOOK).name("Projectile Protection IV").build());
        inv.setItem(13, new ItemBuilder(Material.ENCHANTED_BOOK).name("Fire Protection IV").build());
        
        inv.setItem(22, new ItemBuilder(Material.ARROW).name("Back").build());
        
        player.openInventory(inv);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        
        String title = event.getView().getTitle();
        
        if (title.equals("Enchant Settings")) {
            event.setCancelled(true);
            
            PracticeBot bot = botManager.getBot(player);
            if (bot == null) return;
            
            int slot = event.getSlot();
            
            switch (slot) {
                case 10 -> openEnchantTypeMenu(player, BotEquipment.ArmorSlot.HELMET);
                case 11 -> openEnchantTypeMenu(player, BotEquipment.ArmorSlot.CHESTPLATE);
                case 12 -> openEnchantTypeMenu(player, BotEquipment.ArmorSlot.LEGGINGS);
                case 13 -> openEnchantTypeMenu(player, BotEquipment.ArmorSlot.BOOTS);
                case 49 -> new BotMenu(botManager).openMenu(player);
            }
        } else if (title.endsWith(" Enchants")) {
            event.setCancelled(true);
            
            PracticeBot bot = botManager.getBot(player);
            if (bot == null) return;
            
            String slotName = title.replace(" Enchants", "");
            BotEquipment.ArmorSlot armorSlot = BotEquipment.ArmorSlot.valueOf(slotName);
            
            int slot = event.getSlot();
            Enchantment enchant = null;
            
            switch (slot) {
                case 10 -> enchant = Enchantment.PROTECTION_ENVIRONMENTAL;
                case 11 -> enchant = Enchantment.PROTECTION_EXPLOSIONS;
                case 12 -> enchant = Enchantment.PROTECTION_PROJECTILE;
                case 13 -> enchant = Enchantment.PROTECTION_FIRE;
                case 22 -> {
                    openMenu(player);
                    return;
                }
            }
            
            if (enchant != null) {
                bot.getEquipment().setProtection(armorSlot, enchant, 4);
                bot.updateEquipment();
                player.sendMessage("§a" + armorSlot.name().toLowerCase() + " protection enchantment updated");
            }
        }
    }
}
