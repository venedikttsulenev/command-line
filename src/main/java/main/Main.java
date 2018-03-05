package main;

import commands.Command;
import commands.CommandResolver;
import utils.Environment;

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
            String s = console.readLine();
            String commandName = s;
            String [] arguments = null;
            int spaceIndex = s.indexOf(' ');
            if (spaceIndex != -1) {
                commandName = s.substring(0, spaceIndex);
                while (s.charAt(spaceIndex) == ' ')
                    ++spaceIndex;
                arguments = s.substring(spaceIndex).split(" +");
            }
            Command command = CommandResolver.getCommandForString(commandName);
            if (command != null)
                command.execute(arguments, environment);
            else
                console.printf("Unknown command '%s'%n", commandName);
        }
    }
}
