package adminStuff;

import botStuff.BotInitializer;
import botStuff.MessageCreator;
import net.dv8tion.jda.api.entities.User;

import java.util.Scanner;

public class AdminDispatcher implements Runnable {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

        while (true) {

            String command = scanner.nextLine();

            switch (command) {
                case "/send":
                    sendCommand();
                    break;
                default:
                    System.out.println("Command does not exist");
                    break;
            }
        }
    }

    private void sendCommand() {

        String toSend = scanner.nextLine();

        for (User user : BotInitializer.getBotDispatcher().getConnectedUsers().keySet()) {
            user.openPrivateChannel().flatMap(channel ->
                    channel.sendMessage(toSend)).queue();
        }
    }
}
