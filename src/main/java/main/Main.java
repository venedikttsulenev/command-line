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
            String s;
            do {
                s = console.readLine().trim();
            }
            while (s.length() == 0);
            String commandName = s;
            String[] arguments = null;
            int spaceIndex = 0;
            while (spaceIndex < s.length() && !Character.isWhitespace(s.charAt(spaceIndex)))
                ++spaceIndex;
            if (spaceIndex != s.length()) { /* There are some arguments */
                commandName = s.substring(0, spaceIndex);
                while (Character.isWhitespace(s.charAt(spaceIndex)))
                    ++spaceIndex;
                arguments = s.substring(spaceIndex).split("\\s+");
            }
            Command command = CommandResolver.getCommandForString(commandName);
            if (command != null)
                command.execute(arguments, environment);
            else
                console.printf("Unknown command '%s'%n", commandName);
        }
    }
}
