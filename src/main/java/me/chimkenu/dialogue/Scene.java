package me.chimkenu.dialogue;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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

    public Scene(DialogueNode[] dialogueList, Location origin, double radius) {
        head = dialogueList[0];
        this.origin = origin;
        this.radius = radius;
        this.tasks = new ArrayList<>();

        if (dialogueList.length < 2) {
            return;
        }

        DialogueNode current = head;
        for (int i = 1; i < dialogueList.length; i++) {
            current.next = dialogueList[i];
            current = current.next;
        }
    }

    public void play(Player player, JavaPlugin plugin) {
        DialogueNode current = head;
        int delay = 0;
        while (current != null) {
            tasks.addAll(current.play(player, delay, plugin));
            delay += current.getTotalTime();
            current = current.next;
        }
    }

    public void cancel(Player player, String reason) {
        tasks.forEach(BukkitTask::cancel);
        player.sendMessage(Component.text(reason, NamedTextColor.RED));
    }
}
