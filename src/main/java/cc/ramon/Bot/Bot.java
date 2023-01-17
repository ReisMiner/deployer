package cc.ramon.Bot;

import cc.ramon.FileIO.CommandRunner;
import cc.ramon.FileIO.Parser;
import cc.ramon.FileIO.StructureManager;
import cc.ramon.Main;
import cc.ramon.Model.JobConfig;
import cc.ramon.Model.Webhook;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bot extends ListenerAdapter {

    private final CommandManager commandManager = new CommandManager();
    private final Dotenv dotenv;
    private final StructureManager structureManager = StructureManager.getInstance();

    public Bot(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().getPresence().setPresence(Activity.watching("GitHub"), true);

        commandManager.updateCommands(event.getJDA(), Long.valueOf(dotenv.get("GUILD")));
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();

        if (msg.getEmbeds().size() == 0) {
            return;
        }
        Webhook webhook = Parser.ParseWebhook(msg.getEmbeds().get(0));

        if (webhook.isSuccess()) {
            for (JobConfig jobConfig : structureManager.fileData.getJobConfigs()) {
                if (jobConfig.getJobName().equals(webhook.jobName()) && jobConfig.getRepoName().equals(webhook.repoName())) {
                    CommandRunner.runCommand(jobConfig.getRunCommand());
                    event.getMessage().reply("Running Command: " + jobConfig.getRunCommand()).queue();
                    break;
                }
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        commandManager.executeCommand(event);
    }

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        commandManager.executeCommand(event);
    }

    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        commandManager.executeCommand(event);
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals("newJobConfig")) {
            String repo = event.getValue("repo").getAsString();
            String job = event.getValue("job").getAsString();
            String command = event.getValue("command").getAsString();

            if (structureManager.fileData.getJobConfigs().size() > 0 && structureManager.fileData.getJobConfigs().get(0).getRepoName() == null)
                structureManager.fileData.getJobConfigs().set(0, new JobConfig(repo, job, command));
            else
                structureManager.fileData.getJobConfigs().add(new JobConfig(repo, job, command));

            if (structureManager.update())
                event.reply("Added new Job Config").queue();
            else
                event.reply("Error while adding new Job Config").queue();
        }
    }

    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        if (event.getName().equals("delete-job") && event.getFocusedOption().getName().equals("job")) {
            List<Command.Choice> options = structureManager.fileData.getJobConfigs().stream()
                    .filter(job -> job.getJobName().startsWith(event.getFocusedOption().getValue()))
                    .map(job -> new Command.Choice(job.getJobName(), job.getJobName()))
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
    }
}
