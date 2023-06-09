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
    public final TextComponent text;
    public final int ticksPerChar;
    public final int ticksBeforeNext;
    public DialogueNode next;

    public DialogueNode(TextComponent text, int ticksPerChar, int ticksBeforeNext) {
        this.text = text;
        this.ticksPerChar = ticksPerChar;
        this.ticksBeforeNext = ticksBeforeNext;
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
                    for (int j = 0; j < 16; j++) {
                        player.sendMessage(Component.text());
                    }
                    player.sendMessage(Component.text("====================================================="));
                    for (int j = 0; j < 4; j++) {
                        player.sendMessage(Component.text());
                    }
                    player.sendMessage(Component.text(text.content().substring(0, temp + 1)));
                    for (int j = 0; j < 3; j++) {
                        player.sendMessage(Component.text());
                    }
                    player.sendMessage(Component.text("====================================================="));
                }
            }.runTaskLater(plugin, delay + totalTime));
            totalTime += ticksPerChar;
        }
        return tasks;
    }

    public int getTotalTime() {
        return 1 + (text.content().length() * ticksPerChar) + ticksBeforeNext; // adds a 1 tick delay to avoid text collisions
    }
}
