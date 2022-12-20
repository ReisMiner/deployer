package cc.ramon.Bot.Commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public abstract class ExtendedCommand extends CommandDataImpl {

    public ExtendedCommand(String name, String description) {
        super(name, description);
    }

    public abstract void run(SlashCommandInteractionEvent event);
}
