package dev.akatriggered.practicebot.commands;

import dev.akatriggered.practicebot.bot.BotManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnBotCommand implements CommandExecutor {
    
    private final BotManager botManager;
    
    public SpawnBotCommand(BotManager botManager) {
        this.botManager = botManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }
        
        if (botManager.hasBot(player)) {
            player.sendMessage("§cYou already have a bot spawned! Use /removebot first.");
            return true;
        }
        
        if (botManager.spawnBot(player)) {
            player.sendMessage("§aPractice bot spawned! Use /botmenu to configure it.");
        } else {
            player.sendMessage("§cFailed to spawn bot.");
        }
        
        return true;
    }
}
