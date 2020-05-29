import adminStuff.AdminDispatcher;
import botStuff.BotInitializer;

public class Dispatcher {

    public static void main(String[] args) throws Exception {

        BotInitializer.init();

        new Thread(new AdminDispatcher()).start();
    }
}
