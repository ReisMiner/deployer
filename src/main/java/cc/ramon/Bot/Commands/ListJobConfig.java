package cc.ramon.Bot.Commands;

import cc.ramon.FileIO.StructureManager;
import cc.ramon.Model.JobConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.awt.*;

public class ListJobConfig extends ExtendedCommand {
    public ListJobConfig() {
        super("list-jobs", "list job configs");
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Jobs");
        embedBuilder.setColor(new Color(0, 152, 152));
        StructureManager structureManager = StructureManager.getInstance();

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        StringBuilder s3 = new StringBuilder();
        for (JobConfig jobConfig : structureManager.fileData.getJobConfigs()) {
            s1.append(jobConfig.getRepoName()).append("\n");
            s2.append(jobConfig.getJobName()).append("\n");
            s3.append(jobConfig.getRunCommand()).append("\n");
        }

        embedBuilder.addField("Repository",s1.toString(),true);
        embedBuilder.addField("Job",s2.toString(),true);
        embedBuilder.addField("Command",s3.toString(),true);

        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
