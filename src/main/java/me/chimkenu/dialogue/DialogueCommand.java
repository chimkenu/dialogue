package me.chimkenu.dialogue;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DialogueCommand implements CommandExecutor {
    public static final HashMap<UUID, List<Scene>> validDialogues = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        if (args.length != 2) {
            return true;
        }

        UUID uuid;
        try {
            uuid = UUID.fromString(args[0]);
        } catch (IllegalArgumentException ignored) {
            return true;
        }

        List<Scene> scenes = validDialogues.get(uuid);
        if (scenes == null) {
            return true;
        }

        int index;
        try {
            index = Integer.parseInt(args[1]);
        } catch (NumberFormatException ignored) {
            return true;
        }

        if (index < 0 || index >= scenes.size()) {
            return true;
        }

        scenes.get(index).play(player);
        validDialogues.remove(uuid);

        return true;
    }
}
