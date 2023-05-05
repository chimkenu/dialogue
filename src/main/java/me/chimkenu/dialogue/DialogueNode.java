package me.chimkenu.dialogue;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class DialogueNode {
    private final TextComponent text;
    public final int ticksPerChar;
    public DialogueNode next;

    public DialogueNode(TextComponent text, int ticksPerChar, DialogueNode next) {
        this.text = text;
        this.ticksPerChar = ticksPerChar;
        this.next = next;
    }

    public DialogueNode(TextComponent text, int ticksPerChar) {
        this.text = text;
        this.ticksPerChar = ticksPerChar;
        this.next = null;
    }

    /**
     * @param player        the player that will see the dialogue
     * @param delay         time (in ticks) before the text plays
     * @param plugin        the reference to the plugin scheduling task
     * @return              the list of scheduled BukkitTasks
     */
    public List<BukkitTask> play(Player player, int delay, JavaPlugin plugin) {
        List<BukkitTask> tasks = new ArrayList<>();
        int totalTime = 1; // adds a 1 tick delay to avoid text collisions

        for (int i = 0; i < text.content().length(); i++) {
            int temp = i;
            tasks.add(new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage(Component.text(text.content().substring(0, temp)));
                }
            }.runTaskLater(plugin, delay + totalTime));
            totalTime += ticksPerChar;
        }
        return tasks;
    }

    public int getTotalTime() {
        return 1 + (text.content().length() * ticksPerChar); // adds a 1 tick delay to avoid text collisions
    }
}
