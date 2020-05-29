package botStuff;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;

public class MessageCreator {

    private MessageBuilder builder = new MessageBuilder();
    private EmbedBuilder embedBuilder = new EmbedBuilder();

    public Message create(String text) {
        builder.append(text);
        return builder.build();
    }

    public Message create(Message message) {

        checkForText(message);
        checkForImage(message); // works for only one image
        // TODO: checkForImages(message);

        try {
            builder.setEmbed(embedBuilder.build());
        } catch (IllegalStateException ignored) { }


        return builder.build();
    }

    private void checkForText(Message message) {
        builder.append(message.getContentDisplay() == null ? "" : message.getContentDisplay()); // adds text i suppose
    }

    private void checkForImage(Message message) { // works for only one image
        try {
            embedBuilder.setImage(message.getAttachments().get(0).getUrl());
        } catch (IndexOutOfBoundsException ignored) { }
    }
}
