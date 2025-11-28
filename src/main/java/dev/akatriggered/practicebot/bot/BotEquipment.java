package dev.akatriggered.practicebot.bot;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class BotEquipment {
    
    private final Map<ArmorSlot, Material> armorTypes = new HashMap<>();
    private final Map<ArmorSlot, Enchantment> protections = new HashMap<>();
    private final Map<ArmorSlot, Integer> protectionLevels = new HashMap<>();
    
    public enum ArmorSlot {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS
    }
    
    public BotEquipment() {
        for (ArmorSlot slot : ArmorSlot.values()) {
            armorTypes.put(slot, getDefaultMaterial(slot));
            protections.put(slot, Enchantment.PROTECTION_ENVIRONMENTAL);
            protectionLevels.put(slot, 4);
        }
    }
    
    private Material getDefaultMaterial(ArmorSlot slot) {
        return switch (slot) {
            case HELMET -> Material.DIAMOND_HELMET;
            case CHESTPLATE -> Material.DIAMOND_CHESTPLATE;
            case LEGGINGS -> Material.DIAMOND_LEGGINGS;
            case BOOTS -> Material.DIAMOND_BOOTS;
        };
    }
    
    public void apply(Zombie entity) {
        ItemStack helmet = createArmorPiece(ArmorSlot.HELMET);
        ItemStack chestplate = createArmorPiece(ArmorSlot.CHESTPLATE);
        ItemStack leggings = createArmorPiece(ArmorSlot.LEGGINGS);
        ItemStack boots = createArmorPiece(ArmorSlot.BOOTS);
        
        entity.getEquipment().setHelmet(helmet);
        entity.getEquipment().setChestplate(chestplate);
        entity.getEquipment().setLeggings(leggings);
        entity.getEquipment().setBoots(boots);
        
        entity.getEquipment().setHelmetDropChance(0.0f);
        entity.getEquipment().setChestplateDropChance(0.0f);
        entity.getEquipment().setLeggingsDropChance(0.0f);
        entity.getEquipment().setBootsDropChance(0.0f);
        
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DURABILITY, 3);
        sword.addEnchantment(Enchantment.MENDING, 1);
        entity.getEquipment().setItemInMainHand(sword);
        entity.getEquipment().setItemInMainHandDropChance(0.0f);
        
        ItemStack shield = new ItemStack(Material.SHIELD);
        shield.addEnchantment(Enchantment.DURABILITY, 3);
        shield.addEnchantment(Enchantment.MENDING, 1);
        entity.getEquipment().setItemInOffHand(shield);
        entity.getEquipment().setItemInOffHandDropChance(0.0f);
    }
    
    private ItemStack createArmorPiece(ArmorSlot slot) {
        Material material = armorTypes.get(slot);
        ItemStack item = new ItemStack(material);
        
        Enchantment protection = protections.get(slot);
        int level = protectionLevels.get(slot);
        
        item.addEnchantment(protection, level);
        item.addEnchantment(Enchantment.DURABILITY, 3);
        item.addEnchantment(Enchantment.MENDING, 1);
        
        return item;
    }
    
    public void setArmorType(ArmorSlot slot, Material material) {
        armorTypes.put(slot, material);
    }
    
    public void setProtection(ArmorSlot slot, Enchantment protection, int level) {
        protections.put(slot, protection);
        protectionLevels.put(slot, level);
    }
    
    public Material getArmorType(ArmorSlot slot) {
        return armorTypes.get(slot);
    }
    
    public Enchantment getProtection(ArmorSlot slot) {
        return protections.get(slot);
    }
    
    public int getProtectionLevel(ArmorSlot slot) {
        return protectionLevels.get(slot);
    }
}
