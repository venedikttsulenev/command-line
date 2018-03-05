package commands;

import utils.Environment;

import java.io.Console;

public class HelpCommand implements Command {

    private static final HelpCommand instance = new HelpCommand();
    private static final String[][] supportedCommands = new String[][] {
            {"help",    "display help"},
            {"ls",      "list files"},
            {"quit",    "quit"},
            {"zip",     "create zip archive"}
    };
    private final static int NAME = 0;
    private final static int DESCRIPTION = 1;

    public static HelpCommand getInstance() {
        return instance;
    }

    private HelpCommand() {}

    public void execute(String [] args, Environment env) {
        Console console = env.getConsole();
        for (String [] cmd : supportedCommands)
            console.printf("%-5s -- %s%n", cmd[NAME], cmd[DESCRIPTION]);
    }
}
