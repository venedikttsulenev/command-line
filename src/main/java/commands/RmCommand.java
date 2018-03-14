package commands;

import utils.Environment;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RmCommand extends Command {

    private static final RmCommand instance = new RmCommand();

    public static RmCommand getInstance() {
        return instance;
    }

    private RmCommand() {
        super("rm", "rm <file1> <file2> ...", "remove files");
    }

    private static void removeFiles(File[] files, Environment env) {
        for (File file : files) {
            if (!file.exists())
                env.getConsole().printf("Error: File '%s' does not exist", file.getName());
            else {
                File[] innerFiles;
                if (file.isDirectory() /* Non-empty directory should be emptied before deleting */
                        && (innerFiles = file.listFiles()) != null
                        && innerFiles.length != 0) {
                    removeFiles(innerFiles, env);
                }
                if (!file.delete())
                    env.getConsole().printf("Error: Could not delete '%s'", file.getName());
            }
        }
    }

    @Override
    public void execute(String[] args, Environment env) {
        Path currentDir = env.getCurrentDirectory();
        File[] argFiles = new File[args.length];
        for (int i = 0; i < args.length; ++i) {
            Path argPath = Paths.get(args[i]).normalize();
            Path filePath = Paths.get(currentDir.toString(), argPath.toString());
            File file = filePath.toFile();
            if (!file.exists()) /* If no 'currentDir/arg' file exists */
                file = argPath.toFile(); /* assume there is an 'arg' file */
            argFiles[i] = file;
        }
        removeFiles(argFiles, env);
    }
}
