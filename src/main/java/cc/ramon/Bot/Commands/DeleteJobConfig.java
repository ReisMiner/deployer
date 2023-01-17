package cc.ramon.Bot.Commands;

import cc.ramon.FileIO.StructureManager;
import cc.ramon.Model.JobConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.awt.*;

public class DeleteJobConfig extends ExtendedCommand {
    public DeleteJobConfig() {
        super("delete-job", "delete a job config by job name and optionally repo name");
        this.addOption(OptionType.STRING, "job", "job name of config that has to be deleted", true, true);
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {
        StructureManager structureManager = StructureManager.getInstance();

        for (int i = 0; i < structureManager.fileData.getJobConfigs().size(); i++) {
            JobConfig job = structureManager.fileData.getJobConfigs().get(i);
            if (!job.getJobName().equals(event.getOption("job").getAsString()))
                continue;

            structureManager.fileData.getJobConfigs().remove(i);
            structureManager.update();
            event.reply("Removed job!").queue();
            return;
        }

        event.reply("Job was not removed!").queue();

    }
}
