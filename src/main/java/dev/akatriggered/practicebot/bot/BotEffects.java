package dev.akatriggered.practicebot.bot;

import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

public class BotEffects {
    
    private final Set<PotionEffectType> activeEffects = new HashSet<>();
    
    public void apply(Zombie entity) {
        entity.clearActivePotionEffects();
        
        for (PotionEffectType effect : activeEffects) {
            int amplifier = getAmplifier(effect);
            entity.addPotionEffect(new PotionEffect(effect, Integer.MAX_VALUE, amplifier, false, false));
        }
    }
    
    private int getAmplifier(PotionEffectType effect) {
        if (effect == PotionEffectType.SPEED || effect == PotionEffectType.JUMP) {
            return 1;
        }
        if (effect == PotionEffectType.INCREASE_DAMAGE || effect == PotionEffectType.DAMAGE_RESISTANCE) {
            return 0;
        }
        if (effect == PotionEffectType.ABSORPTION) {
            return 3;
        }
        return 0;
    }
    
    public void toggleEffect(PotionEffectType effect) {
        if (activeEffects.contains(effect)) {
            activeEffects.remove(effect);
        } else {
            activeEffects.add(effect);
        }
    }
    
    public boolean hasEffect(PotionEffectType effect) {
        return activeEffects.contains(effect);
    }
    
    public Set<PotionEffectType> getActiveEffects() {
        return new HashSet<>(activeEffects);
    }
}
