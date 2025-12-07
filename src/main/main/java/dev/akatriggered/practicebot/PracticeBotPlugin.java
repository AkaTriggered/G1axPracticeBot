package dev.akatriggered.practicebot;

import dev.akatriggered.practicebot.bot.BotManager;
import dev.akatriggered.practicebot.commands.BotMenuCommand;
import dev.akatriggered.practicebot.commands.RemoveAllBotsCommand;
import dev.akatriggered.practicebot.commands.RemoveBotCommand;
import dev.akatriggered.practicebot.commands.SpawnBotCommand;
import dev.akatriggered.practicebot.gui.ArmorGUI;
import dev.akatriggered.practicebot.gui.BehaviourGUI;
import dev.akatriggered.practicebot.gui.BotMenu;
import dev.akatriggered.practicebot.gui.EffectGUI;
import dev.akatriggered.practicebot.gui.EnchantGUI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class PracticeBotPlugin extends JavaPlugin {
    
    private BotManager botManager;
    
    @Override
    public void onEnable() {
        try {
            this.botManager = new BotManager(this);
            
            getCommand("spawnbot").setExecutor(new SpawnBotCommand(botManager));
            getCommand("botmenu").setExecutor(new BotMenuCommand(botManager));
            getCommand("removebot").setExecutor(new RemoveBotCommand(botManager));
            getCommand("removeallbots").setExecutor(new RemoveAllBotsCommand(botManager));
            
            getServer().getPluginManager().registerEvents(new BotMenu(botManager), this);
            getServer().getPluginManager().registerEvents(new ArmorGUI(botManager), this);
            getServer().getPluginManager().registerEvents(new EnchantGUI(botManager), this);
            getServer().getPluginManager().registerEvents(new EffectGUI(botManager), this);
            getServer().getPluginManager().registerEvents(new BehaviourGUI(botManager), this);
            
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Failed to enable PracticeBot plugin", e);
            getServer().getPluginManager().disablePlugin(this);
        }
    }
    
    @Override
    public void onDisable() {
        try {
            if (botManager != null) {
                botManager.removeAllBots();
            }
        } catch (Exception e) {
            getLogger().log(Level.WARNING, "Error during plugin disable", e);
        }
    }
    
    public BotManager getBotManager() {
        return botManager;
    }
}
