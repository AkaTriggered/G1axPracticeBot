package dev.akatriggered.practicebot.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ArmorFactory {
    
    public static ItemStack[] createArmorSet(Material chestplateType, Enchantment protection, int protectionLevel) {
        String materialPrefix = getMaterialPrefix(chestplateType);
        
        ItemStack helmet = createArmorPiece(materialPrefix + "_HELMET", protection, protectionLevel);
        ItemStack chestplate = createArmorPiece(materialPrefix + "_CHESTPLATE", protection, protectionLevel);
        ItemStack leggings = createArmorPiece(materialPrefix + "_LEGGINGS", protection, protectionLevel);
        ItemStack boots = createArmorPiece(materialPrefix + "_BOOTS", protection, protectionLevel);
        
        if (chestplateType == Material.TURTLE_HELMET) {
            helmet = new ItemStack(Material.TURTLE_HELMET);
            helmet.addEnchantment(protection, protectionLevel);
            helmet.addEnchantment(Enchantment.DURABILITY, 3);
            helmet.addEnchantment(Enchantment.MENDING, 1);
        }
        
        return new ItemStack[]{helmet, chestplate, leggings, boots};
    }
    
    private static ItemStack createArmorPiece(String materialName, Enchantment protection, int protectionLevel) {
        Material material = Material.valueOf(materialName);
        ItemStack item = new ItemStack(material);
        
        item.addEnchantment(protection, protectionLevel);
        item.addEnchantment(Enchantment.DURABILITY, 3);
        item.addEnchantment(Enchantment.MENDING, 1);
        
        return item;
    }
    
    private static String getMaterialPrefix(Material chestplateType) {
        return switch (chestplateType) {
            case CHAINMAIL_CHESTPLATE -> "CHAINMAIL";
            case IRON_CHESTPLATE -> "IRON";
            case GOLDEN_CHESTPLATE -> "GOLDEN";
            case DIAMOND_CHESTPLATE -> "DIAMOND";
            case NETHERITE_CHESTPLATE -> "NETHERITE";
            default -> "DIAMOND";
        };
    }
}
