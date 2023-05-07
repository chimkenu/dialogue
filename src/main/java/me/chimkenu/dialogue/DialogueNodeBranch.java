package me.chimkenu.dialogue;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class DialogueNodeBranch extends DialogueNode {
    public static HashSet<UUID> validUUIDs = new HashSet<>();

    public DialogueNodeBranch(TextComponent[] texts, Scene[] choices, Location origin, double radius, Dialogue plugin) {
        super(collate(texts, choices, origin, radius, plugin), 0, 0);
    }

    @Override
    public List<BukkitTask> play(Player player, int delay, JavaPlugin plugin) {
        return new ArrayList<>(List.of(new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage(text);
            }
        }.runTaskLater(plugin, 1 + delay)));
    }

    private static TextComponent collate(TextComponent[] texts, Scene[] choices, Location origin, double radius, Dialogue plugin) {
        if (texts.length != choices.length) {
            throw new InputMismatchException();
        }

        TextComponent collated = Component.text("");
        UUID uuid = UUID.randomUUID();
        validUUIDs.add(uuid);

        for (int i = 0; i < texts.length; i++) {
            final int I = i;
            texts[i] = texts[i].clickEvent(ClickEvent.callback(audience -> {
                if (!(audience instanceof Player player)) {
                    return;
                }

                if (!validUUIDs.contains(uuid)) {
                    return;
                }

                validUUIDs.remove(uuid);
                if (!plugin.playersInDialogue.containsKey(player)) {
                    return;
                }

                if (origin.distanceSquared(player.getLocation()) > Math.pow(radius, 2)) {
                    return;
                }

                choices[I].play(player, plugin);
            }));
            collated = collated.append(texts[i]);
        }
        return collated;
    }
}
