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

public class BehaviourGUI implements Listener {
    
    private final BotManager botManager;
    
    public BehaviourGUI(BotManager botManager) {
        this.botManager = botManager;
    }
    
    public void openMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "Behaviour Settings");
        
        PracticeBot bot = botManager.getBot(player);
        if (bot == null) return;
        
        inv.setItem(10, new ItemBuilder(Material.SHIELD)
            .name("Shield Usage " + (bot.getBehaviour().isUseShield() ? "§aON" : "§cOFF")).build());
        inv.setItem(11, new ItemBuilder(Material.ENDER_PEARL)
            .name("Pearl to Owner " + (bot.getBehaviour().isPearlToOwner() ? "§aON" : "§cOFF")).build());
        inv.setItem(12, new ItemBuilder(Material.DIAMOND_SWORD)
            .name("Aggressive Mode " + (bot.getBehaviour().isAggressive() ? "§aON" : "§cOFF")).build());
        inv.setItem(13, new ItemBuilder(Material.IRON_CHESTPLATE)
            .name("Defensive Mode " + (bot.getBehaviour().isDefensive() ? "§aON" : "§cOFF")).build());
        inv.setItem(14, new ItemBuilder(Material.BARRIER)
            .name("Stand Still " + (bot.getBehaviour().isStandStill() ? "§aON" : "§cOFF")).build());
        inv.setItem(15, new ItemBuilder(Material.TOTEM_OF_UNDYING)
            .name("Auto Totem " + (bot.getBehaviour().isAutoTotem() ? "§aON" : "§cOFF")).build());
        
        inv.setItem(22, new ItemBuilder(Material.ARROW).name("Back").build());
        
        player.openInventory(inv);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!event.getView().getTitle().equals("Behaviour Settings")) return;
        
        event.setCancelled(true);
        
        PracticeBot bot = botManager.getBot(player);
        if (bot == null) return;
        
        int slot = event.getSlot();
        
        switch (slot) {
            case 10 -> bot.getBehaviour().setUseShield(!bot.getBehaviour().isUseShield());
            case 11 -> bot.getBehaviour().setPearlToOwner(!bot.getBehaviour().isPearlToOwner());
            case 12 -> bot.getBehaviour().setAggressive(!bot.getBehaviour().isAggressive());
            case 13 -> bot.getBehaviour().setDefensive(!bot.getBehaviour().isDefensive());
            case 14 -> bot.getBehaviour().setStandStill(!bot.getBehaviour().isStandStill());
            case 15 -> bot.getBehaviour().setAutoTotem(!bot.getBehaviour().isAutoTotem());
            case 22 -> {
                new BotMenu(botManager).openMenu(player);
                return;
            }
        }
        
        bot.updateBehaviour();
        openMenu(player);
    }
}
