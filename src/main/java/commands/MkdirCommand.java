package commands;

import utils.Environment;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MkdirCommand extends Command {

    private static final MkdirCommand instance = new MkdirCommand();

    public static MkdirCommand getInstance() {
        return instance;
    }

    private MkdirCommand() {
        super("mkdir", "mkdir <dir>", "create new directory");
    }

    @Override
    public void execute(String[] args, Environment env) {
        String currentDir = env.getCurrentDirectory().toString();
        Path newDir = Paths.get(currentDir, args[0]).normalize();
        newDir.toFile().mkdirs();
    }
}
