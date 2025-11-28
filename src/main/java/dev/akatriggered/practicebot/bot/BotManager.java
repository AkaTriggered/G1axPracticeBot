package dev.akatriggered.practicebot.bot;

import dev.akatriggered.practicebot.PracticeBotPlugin;
import dev.akatriggered.practicebot.util.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BotManager {
    
    private final PracticeBotPlugin plugin;
    private final Map<UUID, PracticeBot> playerBots;
    private final Map<UUID, PlayerData> playerData;
    
    public BotManager(PracticeBotPlugin plugin) {
        this.plugin = plugin;
        this.playerBots = new HashMap<>();
        this.playerData = new HashMap<>();
    }
    
    public boolean hasBot(Player player) {
        return playerBots.containsKey(player.getUniqueId());
    }
    
    public PracticeBot getBot(Player player) {
        return playerBots.get(player.getUniqueId());
    }
    
    public PlayerData getPlayerData(Player player) {
        return playerData.computeIfAbsent(player.getUniqueId(), k -> new PlayerData());
    }
    
    public boolean spawnBot(Player player) {
        if (hasBot(player)) {
            return false;
        }
        
        PracticeBot bot = new PracticeBot(plugin, player);
        playerBots.put(player.getUniqueId(), bot);
        bot.spawn();
        return true;
    }
    
    public boolean removeBot(Player player) {
        PracticeBot bot = playerBots.remove(player.getUniqueId());
        if (bot != null) {
            bot.remove();
            return true;
        }
        return false;
    }
    
    public void removeAllBots() {
        playerBots.values().forEach(PracticeBot::remove);
        playerBots.clear();
    }
}
