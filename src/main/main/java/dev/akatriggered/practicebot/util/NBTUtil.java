package dev.akatriggered.practicebot.util;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class NBTUtil {
    
    public static void setString(Entity entity, Plugin plugin, String key, String value) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        entity.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
    }
    
    public static String getString(Entity entity, Plugin plugin, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        return entity.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }
    
    public static boolean hasKey(Entity entity, Plugin plugin, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        return entity.getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING);
    }
}
