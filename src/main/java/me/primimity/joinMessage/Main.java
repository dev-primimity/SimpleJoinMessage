package me.primimity.joinMessage;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this,this);

        System.out.println("\n" +
                getDescription().getName() + " v" + getDescription().getVersion() + "\n" +
                "Created by " + getDescription().getAuthors() + "\n" +
                "Need a custom plugin? Discord me @primimity\n");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        FileConfiguration cfg = getConfig();
        String msg = cfg.getString("messages.join");

        msg = msg.replace("{player}", p.getName())
                .replace("{name}", p.getDisplayName());

        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        FileConfiguration cfg = getConfig();
        String msg = cfg.getString("messages.quit");

        msg = msg.replace("{player}", p.getName())
                .replace("{name}", p.getDisplayName());

        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}