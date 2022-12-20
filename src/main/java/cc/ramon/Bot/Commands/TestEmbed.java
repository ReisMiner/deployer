package cc.ramon.Bot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.awt.*;

public class TestEmbed extends ExtendedCommand {

    public TestEmbed() {
        super("test-embed", "makes a test embed so GH Actions dont have to run");
        this.addOption(OptionType.BOOLEAN, "success", "if embed is green or not");
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("[deployer] build success on master", "https://github.com/ReisMiner/deployer/actions/runs/3738591536/jobs/6344829009");

        OptionMapping successOption = event.getOption("success");
        if (successOption != null && successOption.getAsBoolean())
            embedBuilder.setColor(new Color(0, 152, 0));
        else
            embedBuilder.setColor(new Color(252, 43, 43));

        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
