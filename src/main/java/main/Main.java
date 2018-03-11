package main;

import commands.Command;
import commands.CommandResolver;
import utils.Environment;
import utils.ParseUtils;

import java.io.Console;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Console console = System.console();
        Environment environment = new Environment(
                console,
                Paths.get(System.getProperty("user.dir"))
        );
        while (!environment.isExit()) {
            String line;
            do
                line = console.readLine().trim();
            while (line.length() == 0);

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
