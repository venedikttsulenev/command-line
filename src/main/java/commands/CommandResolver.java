package commands;

import java.util.HashMap;

public class CommandResolver {

    private static final HashMap<String, Command> commandMapping = new HashMap<String, Command>(){{
        put("help", HelpCommand.getInstance());
        /* TODO: "cd", "ls", ... */
    }};

    public static Command getCommandForString(String name) {
        /* for given string return corresponding CommandSubclass.getInstance() */
        return commandMapping.get(name);
    }
}
