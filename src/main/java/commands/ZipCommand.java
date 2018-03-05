package commands;

import utils.Environment;

import java.nio.file.Path;

public class ZipCommand implements Command {

    private static final ZipCommand instance = new ZipCommand();

    public static ZipCommand getInstance() {
        return instance;
    }

    private ZipCommand() {}

    @Override
    public void execute(String[] args, Environment env) {
        Path dir = env.getCurrentDirectory();
        /* TODO */
    }
}
