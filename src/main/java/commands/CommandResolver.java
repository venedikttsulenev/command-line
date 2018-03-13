package commands;

import java.util.HashMap;

public class CommandResolver {

    private static final Command[] supportedCommands = new Command[] {
            HelpCommand.getInstance(),
            QuitCommand.getInstance(),
            LsCommand.getInstance(),
            CdCommand.getInstance(),
            PwdCommand.getInstance(),
            MkdirCommand.getInstance(),
            ZipCommand.getInstance(),
            UnzipCommand.getInstance(),
            ExtractCommand.getInstance()
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
