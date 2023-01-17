package cc.ramon.Bot.Commands;

import cc.ramon.FileIO.Parser;
import cc.ramon.FileIO.StructureManager;
import cc.ramon.Model.Webhook;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class AddJobConfig extends ExtendedCommand {
    public AddJobConfig() {
        super(Command.Type.MESSAGE, "add repo and job to config");
        this.setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_WEBHOOKS));
    }

    @Override
    public void run(MessageContextInteractionEvent event) {
        User webhook = event.getInteraction().getTarget().getAuthor();
        StructureManager sm = StructureManager.getInstance();
        if (sm.fileData.getWebhookId() != webhook.getIdLong()) {
            event.reply("embed author is not set as webhook source.").setEphemeral(true).queue();
            return;
        }

        if (event.getTarget().getEmbeds().size() == 0) {
            event.reply("message does not contain embed").setEphemeral(true).queue();
            return;
        }

        Webhook parsedWebhook = Parser.ParseWebhook(event.getTarget().getEmbeds().get(0));

        TextInput repoInput = TextInput.create("repo", "Repository Name", TextInputStyle.SHORT)
                .setPlaceholder("Public Repository Name")
                .setValue(parsedWebhook.repoName())
                .setMinLength(3)
                .setMaxLength(100)
                .build();

        TextInput jobInput = TextInput.create("job", "Job Name", TextInputStyle.SHORT)
                .setPlaceholder("Name of the CI Job")
                .setValue(parsedWebhook.jobName())
                .setMinLength(3)
                .setMaxLength(100)
                .build();

        TextInput commandInput = TextInput.create("command", "Command to be run", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Command that's run locally on successful CI run")
                .setMinLength(3)
                .setMaxLength(1000)
                .build();

        Modal modal = Modal.create("newJobConfig", "Add new Job Config")
                .addActionRows(ActionRow.of(repoInput), ActionRow.of(jobInput), ActionRow.of(commandInput)).build();

        event.replyModal(modal).queue();
    }
}
