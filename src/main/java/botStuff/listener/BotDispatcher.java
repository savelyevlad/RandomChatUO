package botStuff.listener;

import botStuff.Commands;
import botStuff.MessageCreator;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BotDispatcher extends ListenerAdapter {

    @Getter
    private HashMap<User, User> connectedUsers = new HashMap<>();
    private User unconnectedUser = null;

    @Override
    public void onMessageReceived(@Nonnull final MessageReceivedEvent event) {

        if(!event.getAuthor().isBot()) {

            System.out.println(event.getAuthor() + ": " + event.getMessage().getContentDisplay());

            if(!checkForCommand(event.getMessage(), event)) {
                if(connectedUsers.containsKey(event.getAuthor())) {
                    connectedUsers.get(event.getAuthor()).openPrivateChannel().flatMap(channel ->
                            channel.sendMessage(new MessageCreator().create(event.getMessage()))).queue();
                }
                else {
                    event.getChannel().sendMessage("You're not in a dialog").queue();
                }
            }
        }
    }

    private boolean checkForCommand(Message message, MessageReceivedEvent event) {
        if(message.getContentDisplay().isEmpty()) {
            return false;
        }
        if(Commands.getNewDialogList().contains(message.getContentDisplay())) {
            newDialog(event);
            return true;
        }
        if(Commands.getStopDialogList().contains(message.getContentDisplay())) {
            stopDialog(event);
            return true;
        }
        return false;
    }

    private void stopDialog(MessageReceivedEvent event) {
        if(!connectedUsers.containsKey(event.getAuthor())) {
            event.getChannel().sendMessage("You're not in a dialog").queue();
        }
        else {
            event.getChannel().sendMessage("disconnected").queue();
            connectedUsers.get(event.getAuthor()).openPrivateChannel().flatMap(channel ->
                    channel.sendMessage("disconnected")).queue();
            connectedUsers.remove(connectedUsers.get(event.getAuthor()));
            connectedUsers.remove(event.getAuthor());
        }
    }

    private void newDialog(MessageReceivedEvent event) {
        if(unconnectedUser == null) {
            unconnectedUser = event.getAuthor();
        }
        else {
            connectedUsers.put(event.getAuthor(), unconnectedUser);
            connectedUsers.put(unconnectedUser, event.getAuthor());
            unconnectedUser = null;
            event.getChannel().sendMessage("connected").queue();
            connectedUsers.get(event.getAuthor()).openPrivateChannel().flatMap(channel ->
                    channel.sendMessage("connected")).queue();
        }
    }
}