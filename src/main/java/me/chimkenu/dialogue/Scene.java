package me.chimkenu.dialogue;

import org.bukkit.entity.Player;

import java.util.List;

public class Scene {
    private final DialogueNode head;

    public Scene(List<DialogueNode> dialogueList) {
        head = dialogueList.get(0);

        if (dialogueList.size() < 2) {
            return;
        }

        DialogueNode current = head;
        for (int i = 1; i < dialogueList.size(); i++) {
            current.next = dialogueList.get(i);
            current = current.next;
        }
    }

    public void play(Player player) {
        DialogueNode current = head;
        int delay = 0;
        while (current != null) {
            delay += current.play(player, delay);
            current = current.next;
        }
    }
}
