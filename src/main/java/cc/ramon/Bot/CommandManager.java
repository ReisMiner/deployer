package cc.ramon.Bot;

import cc.ramon.Bot.Commands.ExtendedCommand;
import cc.ramon.Bot.Commands.TestEmbed;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public CommandManager() {
        commands = new ArrayList<>();

        commands.add(new TestEmbed());
    }

    private final List<ExtendedCommand> commands;

    public void executeCommand(SlashCommandInteractionEvent event) {
        event.deferReply();
        String name = event.getName();
        for (ExtendedCommand command : commands) {
            if (command.getName().equals(name)) {
                command.run(event);
                break;
            }
        }
    }

    public void updateCommands(JDA jda, Long guildId) {
        jda.getGuildById(guildId).updateCommands().addCommands(commands).queue();

    }

    public void updateCommands(JDA jda) {
        jda.updateCommands().addCommands(commands).queue();
    }
}
