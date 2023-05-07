package me.chimkenu.dialogue.dialogues;

import me.chimkenu.dialogue.Dialogue;
import me.chimkenu.dialogue.DialogueNode;
import me.chimkenu.dialogue.DialogueNodeBranch;
import me.chimkenu.dialogue.Scene;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;

public enum TestDialogue {
    SCENE_1 {
        @Override
        public Scene getScene(Player player, Dialogue plugin) {
            return new Scene(new DialogueNode[]{
                    new DialogueNode(Component.text("* FUCK YEAH BABY I MADE IT!!!"), 2, 20 * 3),
                    new DialogueNode(Component.text("* ..."), 2, 20 * 2),
                    new DialogueNode(Component.text("* What to do now?"), 2, 20 * 2),
                    new DialogueNodeBranch(
                        new TextComponent[]{
                                Component.text("* booty"),
                                Component.text("* homosexuals.")
                        },
                        new Scene[]{
                                SCENE_2A.getScene(player, plugin),
                                SCENE_2B.getScene(player, plugin)
                        }, player.getLocation(), 7, plugin)
            }, player.getLocation(), 7);
        }
    },
    SCENE_2A {
        @Override
        public Scene getScene(Player player, Dialogue plugin) {
            return new Scene(new DialogueNode[]{
                    new DialogueNode(Component.text("* i put the new forgis on the G"), 2, 20 * 3),
                    new DialogueNode(Component.text("* i trap until the bloody bottoms is underneath"), 2, 20 * 2),
                    new DialogueNodeBranch(
                            new TextComponent[]{
                                    Component.text("* cuz all my niggas got it out the streets"),
                                    Component.text("* woah man i aint like that")
                            },
                            new Scene[]{
                                    SCENE_2A_1.getScene(player, plugin),
                                    SCENE_2B.getScene(player, plugin)
                            }, player.getLocation(), 7, plugin)
            }, player.getLocation(), 7);
        }
    },
    SCENE_2B {
        @Override
        public Scene getScene(Player player, Dialogue plugin) {
            return new Scene(new DialogueNode[]{
                    new DialogueNode(Component.text("* ew"), 2, 20 * 3),
                    new DialogueNode(Component.text("* fucken weirdo"), 2, 20 * 2),
            }, player.getLocation(), 7);
        }
    },
    SCENE_2A_1 {
        @Override
        public Scene getScene(Player player, Dialogue plugin) {
            return new Scene(new DialogueNode[]{
                    new DialogueNode(Component.text("* i keep a hunnid racks, inside my jeans"), 2, 20 * 3),
                    new DialogueNode(Component.text("* i remember hittin the mall with the whole team"), 2, 20 * 2),
                    new DialogueNode(Component.text("* now a nigga can't answer calls 'cause I'm ballin'"), 2, 20 * 2),
                    new DialogueNode(Component.text("* ok im done"), 2, 20 * 2)
            }, player.getLocation(), 7);
        }
    };

    public abstract Scene getScene(Player player, Dialogue plugin);
}
