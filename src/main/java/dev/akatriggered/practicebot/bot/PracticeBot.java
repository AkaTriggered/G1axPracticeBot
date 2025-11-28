package dev.akatriggered.practicebot.bot;

import dev.akatriggered.practicebot.PracticeBotPlugin;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class PracticeBot {
    
    private final PracticeBotPlugin plugin;
    private final Player owner;
    private Zombie entity;
    private BotEquipment equipment;
    private BotEffects effects;
    private BotBehaviour behaviour;
    private BotAI ai;
    
    public PracticeBot(PracticeBotPlugin plugin, Player owner) {
        this.plugin = plugin;
        this.owner = owner;
        this.equipment = new BotEquipment();
        this.effects = new BotEffects();
        this.behaviour = new BotBehaviour();
        this.ai = new BotAI(this);
    }
    
    public void spawn() {
        Location loc = owner.getLocation().add(2, 0, 0);
        entity = (Zombie) owner.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        
        entity.setCustomName(owner.getName() + "'s Bot");
        entity.setCustomNameVisible(false);
        entity.setRemoveWhenFarAway(false);
        entity.setPersistent(true);
        entity.setCanPickupItems(false);
        entity.setBaby(false);
        entity.setAI(true);
        
        equipment.apply(entity);
        effects.apply(entity);
        ai.start();
    }
    
    public void remove() {
        if (entity != null && !entity.isDead()) {
            entity.remove();
        }
        if (ai != null) {
            ai.stop();
        }
    }
    
    public void updateEquipment() {
        if (entity != null && !entity.isDead()) {
            equipment.apply(entity);
        }
    }
    
    public void updateEffects() {
        if (entity != null && !entity.isDead()) {
            effects.apply(entity);
        }
    }
    
    public void updateBehaviour() {
        if (ai != null) {
            ai.updateBehaviour(behaviour);
        }
    }
    
    public Player getOwner() {
        return owner;
    }
    
    public Zombie getEntity() {
        return entity;
    }
    
    public BotEquipment getEquipment() {
        return equipment;
    }
    
    public BotEffects getEffects() {
        return effects;
    }
    
    public BotBehaviour getBehaviour() {
        return behaviour;
    }
}
