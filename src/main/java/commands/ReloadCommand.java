package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.axo.effect.effectautomessage.EffectAutoMessage;


public
class ReloadCommand implements CommandExecutor {
    private final EffectAutoMessage plugin;
    public ReloadCommand(EffectAutoMessage plugin) {
        this.plugin = plugin;
    }
    @Override
    public
    boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("effectam.reload")) {
            try {
                final long millisActualTime = System.currentTimeMillis();
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload-message")));
                final long executionTime = System.currentTimeMillis() - millisActualTime;
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reloaded-in")+ executionTime + " &ams"));
            } catch (final Exception exception) {
                String error = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("error-config"));
                sender.sendMessage(error);
                plugin.getLogger().warning(error);
                System.out.println(exception + "");
            }
        }
        return true;
    }
}