package xyz.axo.effect.effectautomessage;

import commands.ReloadCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public final class EffectAutoMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("automessage").setExecutor(new ReloadCommand(this));
        saveDefaultConfig();
        getLogger().info("Loading...");
        List<String> messages = this.getConfig().getStringList("messages");
        Random random = new Random();
        this.getServer().getScheduler().runTaskTimer(this, () -> {
            messages.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
            int index = random.nextInt(this.getConfig().getList("messages").size());
            Bukkit.broadcastMessage((String) this.getConfig().getList("messages").get(index));
        }, 0L, 20L * getConfig().getInt("time"));
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
