package main;

import commands.Command;
import commands.CommandResolver;
import utils.Environment;
import utils.ParseUtils;

import java.io.Console;
import java.nio.file.Paths;

public class Main {
    private static final Console console = System.console();
    private static final Environment environment = new Environment(
            console,
            Paths.get(System.getProperty("user.dir"))
    );
    private static void prompt() {
        console.printf("> ");
    }
    public static void main(String[] args) {
        while (!environment.isExit()) {
            String line;
            do {
                prompt();
                line = console.readLine().trim();
            }
            while (line.length() == 0); /* Ignore lines that do not contain non-whitespace characters */

            ParseUtils.ParseResult parseResult = ParseUtils.parseCommand(line);
            String commandName = parseResult.getCommandName();
            String[] arguments = parseResult.getArgs();

            Command command = CommandResolver.getCommandForString(commandName);
            if (command != null)
                command.execute(arguments, environment);
            else
                console.printf("Unknown command '%s'%n", commandName);
        }
    }
}
