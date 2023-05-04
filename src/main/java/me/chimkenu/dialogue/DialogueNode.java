package me.chimkenu.dialogue;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;

public class DialogueNode {
    private final TextComponent text;
    public DialogueNode next;

    public DialogueNode(TextComponent text, DialogueNode next) {
        this.text = text;
        this.next = next;
    }

    /**
     * @param player The player that will see the dialogue
     * @param delay  Time (in ticks) before the text plays
     * @return       The number of ticks the text plays for
     */
    public int play(Player player, int delay) {
        // TODO: ADD FUNCTIONALITY
        return 0;
    }
}
