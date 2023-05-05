package me.chimkenu.dialogue;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final DialogueNode head;
    public final Location origin;
    public final double radius;
    public final List<BukkitTask> tasks;

    public Scene(List<DialogueNode> dialogueList, Location origin, double radius) {
        head = dialogueList.get(0);
        this.origin = origin;
        this.radius = radius;
        this.tasks = new ArrayList<>();

        if (dialogueList.size() < 2) {
            return;
        }

        DialogueNode current = head;
        for (int i = 1; i < dialogueList.size(); i++) {
            current.next = dialogueList.get(i);
            current = current.next;
        }
    }

    public void play(Player player, JavaPlugin plugin) {
        DialogueNode current = head;
        int delay = 0;
        while (current != null) {
            current.play(player, delay, plugin);
            delay += current.getTotalTime();
            current = current.next;
        }
    }

    public void cancel() {

    }
}
