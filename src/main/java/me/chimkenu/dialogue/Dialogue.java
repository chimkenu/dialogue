package me.chimkenu.dialogue;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Dialogue extends JavaPlugin {
    public HashMap<Player, Location> playersInDialogue;

    @Override
    public void onEnable() {
        playersInDialogue = new HashMap<>();
        getCommand("dialogue").setExecutor(new DialogueCommand(playersInDialogue));
        getServer().getPluginManager().registerEvents(new DialogueListener(playersInDialogue), this);
    }
}
