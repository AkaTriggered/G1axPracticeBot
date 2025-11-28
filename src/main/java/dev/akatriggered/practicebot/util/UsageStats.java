package dev.akatriggered.practicebot.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class UsageStats {
    private final Plugin plugin;
    private final String serverUUID;
    
    public UsageStats(Plugin plugin) {
        this.plugin = plugin;
        this.serverUUID = getOrCreateServerUUID();
    }
    
    private String getOrCreateServerUUID() {
        File uuidFile = new File(plugin.getDataFolder(), "server-uuid.txt");
        if (uuidFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(uuidFile))) {
                return reader.readLine();
            } catch (IOException e) {
                plugin.getLogger().warning("Failed to read server UUID: " + e.getMessage());
            }
        }
        
        String uuid = UUID.randomUUID().toString();
        plugin.getDataFolder().mkdirs();
        try (PrintWriter writer = new PrintWriter(new FileWriter(uuidFile))) {
            writer.println(uuid);
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save server UUID: " + e.getMessage());
        }
        return uuid;
    }
    
    public void sendUsageData() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL("https://api.example.com/usage"); // Replace with your endpoint
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                
                String data = String.format("{\"plugin\":\"%s\",\"version\":\"%s\",\"server\":\"%s\",\"players\":%d}", 
                    plugin.getName(), plugin.getDescription().getVersion(), serverUUID, Bukkit.getOnlinePlayers().size());
                
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(data.getBytes());
                }
                
                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    plugin.getLogger().info("Usage data sent successfully");
                }
            } catch (Exception e) {
                // Silently fail - don't spam console
            }
        });
    }
}
