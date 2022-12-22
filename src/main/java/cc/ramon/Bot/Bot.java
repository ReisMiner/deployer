package cc.ramon.Bot;

import cc.ramon.FileIO.Parser;
import cc.ramon.FileIO.StructureManager;
import cc.ramon.Main;
import cc.ramon.Model.Webhook;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class Bot extends ListenerAdapter {

    private final CommandManager commandManager = new CommandManager();
    private final Dotenv dotenv;

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
        if (!msg.getAuthor().isBot())
            return;

        Webhook webhook = Parser.ParseWebhook(msg.getEmbeds().get(0));

        System.out.println(webhook);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        commandManager.executeCommand(event);
    }
}
