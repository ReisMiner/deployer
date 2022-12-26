package cc.ramon;

import cc.ramon.Bot.Bot;
import cc.ramon.FileIO.StructureManager;
import cc.ramon.Model.FileData;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class Main {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        StructureManager structureManager = StructureManager.getInstance();
        structureManager.setup();

        JDA jda = JDABuilder.create(dotenv.get("BOT_TOKEN"), GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new Bot(dotenv))
                .build();
        jda.setAutoReconnect(true);
    }
}
