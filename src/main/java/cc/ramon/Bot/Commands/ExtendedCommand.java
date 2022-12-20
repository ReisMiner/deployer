package cc.ramon.Bot.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public abstract class ExtendedCommand extends CommandDataImpl {

    public ExtendedCommand(String name, String description) {
        super(name, description);
    }
    public ExtendedCommand(Command.Type type, String description) {
        super(type, description);
    }

    public void run(SlashCommandInteractionEvent event){};
    public void run(UserContextInteractionEvent event){};
}
