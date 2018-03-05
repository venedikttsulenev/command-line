package commands;

import utils.Environment;

import java.io.Console;

public class HelpCommand implements Command {

    private static final HelpCommand instance = new HelpCommand();
    private static final String [] supportedCommands = new String [] {"help"};
    private static final String [] descriptions = new String[] {"display help"};

    public static HelpCommand getInstance() {
        return instance;
    }

    private HelpCommand() {}

    public void execute(String [] args, Environment env) {
        Console console = env.getConsole();
        for (int i = 0; i < supportedCommands.length; ++i)
            console.printf("%s -- %s%n", supportedCommands[i], descriptions[i]);
    }
}
