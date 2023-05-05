package me.chimkenu.dialogue;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class DialogueNodeBranch extends DialogueNode {
    public DialogueNodeBranch(TextComponent text, int ticksPerChar, int ticksBeforeNext) {
        super(text, ticksPerChar, ticksBeforeNext);
    }

    @Override
    public List<BukkitTask> play(Player player, int delay, JavaPlugin plugin) {
        player.sendMessage(text);
        return new ArrayList<>();
    }
}
