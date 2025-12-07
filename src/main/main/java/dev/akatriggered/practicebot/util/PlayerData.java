package dev.akatriggered.practicebot.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

public class PlayerData {
    
    private Material armorType = Material.DIAMOND_CHESTPLATE;
    private Enchantment protection = Enchantment.PROTECTION_ENVIRONMENTAL;
    private int protectionLevel = 4;
    private Set<PotionEffectType> effects = new HashSet<>();
    private boolean useShield = true;
    private boolean pearlToOwner = false;
    private boolean aggressive = true;
    private boolean defensive = false;
    private boolean standStill = false;
    private boolean autoTotem = true;
    
    public Material getArmorType() {
        return armorType;
    }
    
    public void setArmorType(Material armorType) {
        this.armorType = armorType;
    }
    
    public Enchantment getProtection() {
        return protection;
    }
    
    public void setProtection(Enchantment protection) {
        this.protection = protection;
    }
    
    public int getProtectionLevel() {
        return protectionLevel;
    }
    
    public void setProtectionLevel(int protectionLevel) {
        this.protectionLevel = protectionLevel;
    }
    
    public Set<PotionEffectType> getEffects() {
        return effects;
    }
    
    public void setEffects(Set<PotionEffectType> effects) {
        this.effects = effects;
    }
    
    public boolean isUseShield() {
        return useShield;
    }
    
    public void setUseShield(boolean useShield) {
        this.useShield = useShield;
    }
    
    public boolean isPearlToOwner() {
        return pearlToOwner;
    }
    
    public void setPearlToOwner(boolean pearlToOwner) {
        this.pearlToOwner = pearlToOwner;
    }
    
    public boolean isAggressive() {
        return aggressive;
    }
    
    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }
    
    public boolean isDefensive() {
        return defensive;
    }
    
    public void setDefensive(boolean defensive) {
        this.defensive = defensive;
    }
    
    public boolean isStandStill() {
        return standStill;
    }
    
    public void setStandStill(boolean standStill) {
        this.standStill = standStill;
    }
    
    public boolean isAutoTotem() {
        return autoTotem;
    }
    
    public void setAutoTotem(boolean autoTotem) {
        this.autoTotem = autoTotem;
    }
}
