package cc.ramon.Bot.Commands;

import cc.ramon.FileIO.StructureManager;
import cc.ramon.Model.FileData;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PrintFileData extends ExtendedCommand {

    public PrintFileData() {
        super("print-filedata", "Returns the Data stored in the file and stored in the variable for fast access.");
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {
        StructureManager sm = StructureManager.getInstance();
        FileData data = sm.checkStructure();
        event.reply("Data from File: ```\n" + data
                + "```\n" +
                "Data from Variable: ```\n" + sm.fileData + "```").queue();
    }
}
