package botStuff;

import botStuff.listener.BotDispatcher;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BotInitializer {

    @Getter
    private static BotDispatcher botDispatcher;

    public static void init() throws LoginException {

        String s = null;
        try {
            s = new List[]{Files.readAllLines(Paths.get("token.txt").toAbsolutePath())}[0].toString();
            s = s.substring(1, s.length() - 1);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JDA api = JDABuilder.createDefault(s).build();
        botDispatcher = new BotDispatcher();
        api.addEventListener(botDispatcher);
    }
}
