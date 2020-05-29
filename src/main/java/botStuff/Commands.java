package botStuff;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public final class Commands {

    private Commands () {}

    @Getter
    private static List<String> newDialogList = Arrays.asList("!new", "!n", "/n", "/new");
    @Getter
    private static List<String> stopDialogList = Arrays.asList("!stop", "!s", "/s", "/stop");

    @Getter
    private List<List<String>> all = Arrays.asList(newDialogList, stopDialogList);
}
