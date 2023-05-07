package me.chimkenu.dialogue;

import me.chimkenu.dialogue.dialogues.TestDialogue;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DialogueCommand implements CommandExecutor {
    private final Dialogue plugin;

    public DialogueCommand(Dialogue plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        if (plugin.playersInDialogue.containsKey(player)) {
            return true;
        }

        Scene scene = TestDialogue.SCENE_1.getScene(player, plugin);
        plugin.playersInDialogue.put(player, scene);
        scene.play(player, plugin);

        return true;
    }
}
