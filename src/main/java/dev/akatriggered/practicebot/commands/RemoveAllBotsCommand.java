package dev.akatriggered.practicebot.commands;

import dev.akatriggered.practicebot.bot.BotManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveAllBotsCommand implements CommandExecutor {
    
    private final BotManager botManager;
    
    public RemoveAllBotsCommand(BotManager botManager) {
        this.botManager = botManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }
        
        if (!sender.hasPermission("practicebot.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }
        
        botManager.removeAllBots();
        sender.sendMessage(ChatColor.GREEN + "All practice bots have been removed!");
        return true;
    }
}
