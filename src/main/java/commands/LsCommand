package commands;

import utils.Environment;

import java.io.Console;
import java.io.File;

public class LsCommand implements Command {
    private static final LsCommand instance = new LsCommand();
    private static final String[] supportedCommands = new String[]{"ls"};
    private static final String[] descriptions = new String[]{"display ls"};

    public static LsCommand getInstance() {
        return instance;
    }

    private LsCommand() {
    }

    public void execute(String[] args, Environment env) {
        Console console = env.getConsole();
        File dir = new File(System.getProperty("user.dir"));
        for (File f : dir.listFiles()) {
            System.out.println(f);
        }
    }
}
