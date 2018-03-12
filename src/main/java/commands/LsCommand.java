package commands;

import utils.Environment;

import java.io.Console;
import java.io.File;

public class LsCommand extends Command {
    private static final LsCommand instance = new LsCommand();

    public static LsCommand getInstance() {
        return instance;
    }

    private LsCommand() {
        super("ls", "ls", "list files");
    }

    public void execute(String[] args, Environment env) {
        Console console = env.getConsole();
        File dir = env.getCurrentDirectory().toFile();
        for (File f : dir.listFiles()) {
            console.printf("%s%n", f.getName());
        }
    }
}
