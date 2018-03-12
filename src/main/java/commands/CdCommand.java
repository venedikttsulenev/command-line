package commands;

import utils.Environment;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommand extends Command {

    private final static CdCommand instance = new CdCommand();

    public static CdCommand getInstance() {
        return instance;
    }

    private CdCommand() {
        super("cd", "cd <dir>", "change working directory");
    }

    @Override
    public void execute(String[] args, Environment env) {
        Path currentDir = env.getCurrentDirectory();
        Path newDir = Paths.get(currentDir.toString(), args[0]).normalize(); /* Check 'currentDir/args[0]' directory */
        if (newDir.toFile().exists())
            env.setCurrentDirectory(newDir);
        else {
            newDir = Paths.get(args[0]).normalize(); /* Check 'args[0]' directory */
            if (newDir.toFile().exists())
                env.setCurrentDirectory(newDir);
            else
                env.getConsole().printf("Error: directory '%s' does not exist%n", args[0]);
        }
    }
}
