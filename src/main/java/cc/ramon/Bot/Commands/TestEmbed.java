package cc.ramon.Bot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.awt.*;

public class TestEmbed extends ExtendedCommand {

    public TestEmbed() {
        super("test-embed", "makes a test embed so GH Actions dont have to run");
        this.addOption(OptionType.BOOLEAN, "success", "if embed is green or not");
        this.addOption(OptionType.STRING, "repo", "repository name", false);
        this.addOption(OptionType.STRING, "job", "job name", false);
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        String repo = "deployer";
        String job = "build";
        if (event.getOption("repo") != null)
            repo = event.getOption("repo").getAsString();
        if (event.getOption("job") != null)
            job = event.getOption("job").getAsString();


        embedBuilder.setTitle("[" + repo + "] " + job + " success on master", "https://github.com/ReisMiner/deployer/actions/runs/3738591536/jobs/6344829009");

        OptionMapping successOption = event.getOption("success");
        if (successOption != null && successOption.getAsBoolean())
            embedBuilder.setColor(new Color(0, 152, 0));
        else
            embedBuilder.setColor(new Color(252, 43, 43));

        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
