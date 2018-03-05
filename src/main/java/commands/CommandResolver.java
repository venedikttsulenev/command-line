package commands;

import java.util.HashMap;

public class CommandResolver {

    private static final HashMap<String, Command> commandMapping = new HashMap<String, Command>(){{
        put("help", HelpCommand.getInstance());
        put("quit", QuitCommand.getInstance());
        put("ls", LsCommand.getInstance());
        put("zip", ZipCommand.getInstance());
    }};

    public static Command getCommandForString(String name) {
        /* for given string return corresponding Command instance */
        return commandMapping.get(name);
    }
}
