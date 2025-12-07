package dev.akatriggered.practicebot.commands;

import dev.akatriggered.practicebot.bot.BotManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveBotCommand implements CommandExecutor {
    
    private final BotManager botManager;
    
    public RemoveBotCommand(BotManager botManager) {
        this.botManager = botManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }
        
        if (botManager.removeBot(player)) {
            player.sendMessage("§aPractice bot removed.");
        } else {
            player.sendMessage("§cYou don't have a bot to remove.");
        }
        
        return true;
    }
}
