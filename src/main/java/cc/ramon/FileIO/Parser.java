package cc.ramon.FileIO;

import cc.ramon.Model.Webhook;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class Parser {
    public static Webhook ParseWebhook(MessageEmbed embed) {
        String[] split = embed.getTitle().split(" ");

        String repoName = split[0].substring(1, split[0].length() - 1);

        StringBuilder jobNameBuilder = new StringBuilder();
        for (int i = 1; i < split.length - 3; i++) {
            jobNameBuilder.append(split[i]);
        }

        boolean isSuccess = split[split.length - 3].equals("success");

        String jobUrl = embed.getUrl();

        return new Webhook(repoName, jobNameBuilder.toString(), jobUrl, isSuccess);
    }
}
