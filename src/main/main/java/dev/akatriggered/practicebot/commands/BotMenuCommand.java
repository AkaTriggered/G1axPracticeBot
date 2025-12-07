package dev.akatriggered.practicebot.commands;

import dev.akatriggered.practicebot.bot.BotManager;
import dev.akatriggered.practicebot.gui.BotMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BotMenuCommand implements CommandExecutor {
    
    private final BotManager botManager;
    
    public BotMenuCommand(BotManager botManager) {
        this.botManager = botManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }
        
        if (!botManager.hasBot(player)) {
            player.sendMessage("§cYou don't have a bot! Use /spawnbot first.");
            return true;
        }
        
        new BotMenu(botManager).openMenu(player);
        return true;
    }
}
