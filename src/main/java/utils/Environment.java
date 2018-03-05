package utils;

import java.io.Console;
import java.nio.file.Path;

public class Environment {
    private final Console console;
    private Path currentDirectory;
    public Environment(Console console, Path directory) {
        this.console = console;
        this.currentDirectory = directory;
    }

    public Console getConsole() {
        return console;
    }

    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(Path directory) {
        this.currentDirectory = directory;
    }
}
