package botStuff;

import botStuff.listener.BotDispatcher;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class BotInitializer {

    @Getter
    private static BotDispatcher botDispatcher;

    public static void init() throws LoginException {

        JDA api = JDABuilder.createDefault("NzE0MTE5ODg5MjExODgzNjAx.XsweOw.y1pkSuWi6bH4EK1Gy3lb42sKTK0").build();
        botDispatcher = new BotDispatcher();
        api.addEventListener(botDispatcher);
    }
}
