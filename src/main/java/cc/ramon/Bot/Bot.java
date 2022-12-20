package cc.ramon.Bot;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Bot extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().getPresence().setPresence(Activity.watching("Github"), true);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();

        if (msg.getEmbeds().size() == 0) {
            return;
        }
        if (!msg.getAuthor().isBot())
            return;

    }
}
