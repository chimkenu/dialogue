package me.chimkenu.dialogue;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DialogueListener implements Listener {
    private final Dialogue plugin;

    public DialogueListener(Dialogue plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        plugin.removePlayer(e.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Scene scene = plugin.playersInDialogue.get(player);
        if (scene == null) {
            return;
        }

        if (scene.origin.distanceSquared(player.getLocation()) > Math.pow(scene.radius, 2)) {
            plugin.removePlayer(e.getPlayer());
        }
    }
}
