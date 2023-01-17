package cc.ramon.Bot;

import cc.ramon.Bot.Commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public CommandManager() {
        commands = new ArrayList<>();

        commands.add(new TestEmbed());
        commands.add(new SetupWebhook());
        commands.add(new PrintFileData());
        commands.add(new AddJobConfig());
        commands.add(new ListJobConfig());
    }

    private final List<ExtendedCommand> commands;

    public void executeCommand(GenericCommandInteractionEvent event) {
        String name = event.getName();
        for (ExtendedCommand command : commands) {
            if (command.getName().equals(name)) {
                if (event instanceof SlashCommandInteractionEvent) {
                    SlashCommandInteractionEvent e = (SlashCommandInteractionEvent) event;
                    e.deferReply();
                    command.run(e);
                } else if (event instanceof MessageContextInteractionEvent) {
                    MessageContextInteractionEvent e = (MessageContextInteractionEvent) event;
                    e.deferReply();
                    command.run(e);
                } else if (event instanceof UserContextInteractionEvent) {
                    UserContextInteractionEvent e = (UserContextInteractionEvent) event;
                    e.deferReply();
                    command.run(e);
                }
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
