package cc.ramon.Bot.Commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;

public class SetupWebhook extends ExtendedCommand {
    public SetupWebhook() {
        super(Command.Type.USER, "set as embed source");
        this.setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_WEBHOOKS));
    }

    @Override
    public void run(UserContextInteractionEvent event) {

    }
}
