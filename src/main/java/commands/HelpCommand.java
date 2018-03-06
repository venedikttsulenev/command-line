package commands;

import utils.Environment;

import java.io.Console;

public class HelpCommand extends Command {

    private static final HelpCommand instance = new HelpCommand();
    private static final Command[] supportedCommands = CommandResolver.getSupportedCommands();

    public static HelpCommand getInstance() {
        return instance;
    }

    private HelpCommand() {
        super("help", "help", "display help");
    }

    public void execute(String [] args, Environment env) {
        Console console = env.getConsole();
        for (Command cmd : supportedCommands)
            console.printf("%-5s -- %s%n    usage: '%s'%n", cmd.getName(), cmd.getDescription(), cmd.getUsage());
    }
}
