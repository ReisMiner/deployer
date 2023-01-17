package cc.ramon.Bot.Commands;

import cc.ramon.FileIO.StructureManager;
import cc.ramon.Model.FileData;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;

public class SetupWebhook extends ExtendedCommand {
    public SetupWebhook() {
        super(Command.Type.MESSAGE, "set as embed source");
        this.setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_WEBHOOKS));
    }

    @Override
    public void run(MessageContextInteractionEvent event) {
        User webhook = event.getInteraction().getTarget().getAuthor();
        StructureManager sm = StructureManager.getInstance();
        FileData data = sm.checkStructure();
        if (data == null) {
            if (!sm.makeStructure()) {
                event.reply("**ERROR** Could not create file structure!").queue();
                return;
            }
            data = sm.checkStructure();
            if (data == null) {
                event.reply("**ERROR** File is corrupt!").queue();
                return;
            }
        }

        data.setWebhookId(webhook.getIdLong());
        if (sm.update(data))
            event.reply("Set Webhook Source to " + webhook.getName() + "[" + webhook.getIdLong() + "]").queue();
        else
            event.reply("Error while setting webhook source").queue();

    }
}
