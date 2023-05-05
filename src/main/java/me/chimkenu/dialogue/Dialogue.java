package me.chimkenu.dialogue;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Iterator;

public final class Dialogue extends JavaPlugin {
    public HashMap<Player, Scene> playersInDialogue;

    @Override
    public void onEnable() {
        playersInDialogue = new HashMap<>();
        getCommand("dialogue").setExecutor(new DialogueCommand(this));
        getServer().getPluginManager().registerEvents(new DialogueListener(this), this);
    }

    public void removePlayer(Player player, String reason) {
        Scene scene = playersInDialogue.remove(player);
        if (scene == null) {
            return;
        }
        scene.cancel(player, reason);
    }
}
