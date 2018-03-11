package commands;

import java.util.HashMap;

public class CommandResolver {

    private static final Command[] supportedCommands = new Command[] {
            HelpCommand.getInstance(),
            LsCommand.getInstance(),
            QuitCommand.getInstance(),
            ZipCommand.getInstance(),
            UnzipCommand.getInstance()
    };

    private static final HashMap<String, Command> commandMapping = new HashMap<String, Command>(){{
        for (Command command : supportedCommands)
            put(command.getName(), command);
    }};

    public static Command[] getSupportedCommands() {
        return supportedCommands;
    }

    public static Command getCommandForString(String name) {
        /* Get Command instance by command name */
        return commandMapping.get(name);
    }
}
