package dev.akatriggered.practicebot.gui;

import dev.akatriggered.practicebot.bot.BotEquipment;
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

public class ArmorGUI implements Listener {
    
    private final BotManager botManager;
    
    public ArmorGUI(BotManager botManager) {
        this.botManager = botManager;
    }
    
    public void openMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "Armor Settings");
        
        inv.setItem(10, new ItemBuilder(Material.DIAMOND_HELMET).name("Helmet").build());
        inv.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("Chestplate").build());
        inv.setItem(12, new ItemBuilder(Material.DIAMOND_LEGGINGS).name("Leggings").build());
        inv.setItem(13, new ItemBuilder(Material.DIAMOND_BOOTS).name("Boots").build());
        
        inv.setItem(49, new ItemBuilder(Material.ARROW).name("Back").build());
        
        player.openInventory(inv);
    }
    
    public void openArmorTypeMenu(Player player, BotEquipment.ArmorSlot slot) {
        Inventory inv = Bukkit.createInventory(null, 27, slot.name() + " Types");
        
        Material[] materials = getArmorMaterials(slot);
        
        for (int i = 0; i < materials.length; i++) {
            inv.setItem(10 + i, new ItemBuilder(materials[i]).name(getMaterialName(materials[i])).build());
        }
        
        inv.setItem(22, new ItemBuilder(Material.ARROW).name("Back").build());
        
        player.openInventory(inv);
    }
    
    private Material[] getArmorMaterials(BotEquipment.ArmorSlot slot) {
        return switch (slot) {
            case HELMET -> new Material[]{
                Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.GOLDEN_HELMET,
                Material.DIAMOND_HELMET, Material.NETHERITE_HELMET, Material.TURTLE_HELMET
            };
            case CHESTPLATE -> new Material[]{
                Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE,
                Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE
            };
            case LEGGINGS -> new Material[]{
                Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.GOLDEN_LEGGINGS,
                Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS
            };
            case BOOTS -> new Material[]{
                Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.GOLDEN_BOOTS,
                Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS
            };
        };
    }
    
    private String getMaterialName(Material material) {
        return material.name().toLowerCase().replace("_", " ");
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        
        String title = event.getView().getTitle();
        
        if (title.equals("Armor Settings")) {
            event.setCancelled(true);
            
            PracticeBot bot = botManager.getBot(player);
            if (bot == null) return;
            
            int slot = event.getSlot();
            
            switch (slot) {
                case 10 -> openArmorTypeMenu(player, BotEquipment.ArmorSlot.HELMET);
                case 11 -> openArmorTypeMenu(player, BotEquipment.ArmorSlot.CHESTPLATE);
                case 12 -> openArmorTypeMenu(player, BotEquipment.ArmorSlot.LEGGINGS);
                case 13 -> openArmorTypeMenu(player, BotEquipment.ArmorSlot.BOOTS);
                case 49 -> new BotMenu(botManager).openMenu(player);
            }
        } else if (title.endsWith(" Types")) {
            event.setCancelled(true);
            
            PracticeBot bot = botManager.getBot(player);
            if (bot == null) return;
            
            String slotName = title.replace(" Types", "");
            BotEquipment.ArmorSlot armorSlot = BotEquipment.ArmorSlot.valueOf(slotName);
            
            int slot = event.getSlot();
            
            if (slot == 22) {
                openMenu(player);
                return;
            }
            
            Material[] materials = getArmorMaterials(armorSlot);
            int materialIndex = slot - 10;
            
            if (materialIndex >= 0 && materialIndex < materials.length) {
                bot.getEquipment().setArmorType(armorSlot, materials[materialIndex]);
                bot.updateEquipment();
                player.sendMessage("§a" + armorSlot.name().toLowerCase() + " updated to " + getMaterialName(materials[materialIndex]));
            }
        }
    }
}
