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
import org.bukkit.potion.PotionEffectType;

public class EffectGUI implements Listener {
    
    private final BotManager botManager;
    
    public EffectGUI(BotManager botManager) {
        this.botManager = botManager;
    }
    
    public void openMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "Effect Settings");
        
        PracticeBot bot = botManager.getBot(player);
        if (bot == null) return;
        
        inv.setItem(10, new ItemBuilder(Material.POTION)
            .name("Invisibility " + (bot.getEffects().hasEffect(PotionEffectType.INVISIBILITY) ? "§aON" : "§cOFF")).build());
        inv.setItem(11, new ItemBuilder(Material.POTION)
            .name("Speed " + (bot.getEffects().hasEffect(PotionEffectType.SPEED) ? "§aON" : "§cOFF")).build());
        inv.setItem(12, new ItemBuilder(Material.POTION)
            .name("Jump Boost " + (bot.getEffects().hasEffect(PotionEffectType.JUMP) ? "§aON" : "§cOFF")).build());
        inv.setItem(13, new ItemBuilder(Material.POTION)
            .name("Strength " + (bot.getEffects().hasEffect(PotionEffectType.INCREASE_DAMAGE) ? "§aON" : "§cOFF")).build());
        inv.setItem(14, new ItemBuilder(Material.POTION)
            .name("Resistance " + (bot.getEffects().hasEffect(PotionEffectType.DAMAGE_RESISTANCE) ? "§aON" : "§cOFF")).build());
        inv.setItem(15, new ItemBuilder(Material.POTION)
            .name("Absorption " + (bot.getEffects().hasEffect(PotionEffectType.ABSORPTION) ? "§aON" : "§cOFF")).build());
        
        inv.setItem(22, new ItemBuilder(Material.ARROW).name("Back").build());
        
        player.openInventory(inv);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!event.getView().getTitle().equals("Effect Settings")) return;
        
        event.setCancelled(true);
        
        PracticeBot bot = botManager.getBot(player);
        if (bot == null) return;
        
        int slot = event.getSlot();
        PotionEffectType effect = null;
        
        switch (slot) {
            case 10 -> effect = PotionEffectType.INVISIBILITY;
            case 11 -> effect = PotionEffectType.SPEED;
            case 12 -> effect = PotionEffectType.JUMP;
            case 13 -> effect = PotionEffectType.INCREASE_DAMAGE;
            case 14 -> effect = PotionEffectType.DAMAGE_RESISTANCE;
            case 15 -> effect = PotionEffectType.ABSORPTION;
            case 22 -> {
                new BotMenu(botManager).openMenu(player);
                return;
            }
        }
        
        if (effect != null) {
            bot.getEffects().toggleEffect(effect);
            bot.updateEffects();
            openMenu(player);
        }
    }
}
