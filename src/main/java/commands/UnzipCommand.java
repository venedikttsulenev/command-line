package commands;

import utils.Environment;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipCommand extends Command {

    private static final UnzipCommand instance = new UnzipCommand();
    private static final int MEGABYTE = 1048576;
    private static final int BUFFER_SIZE = MEGABYTE;

    public static UnzipCommand getInstance() {
        return instance;
    }

    private UnzipCommand() {
        super("unzip", "unzip <zip-file> <target folder>", "decompress zip-archive");
    }

    private void unzipFile(File file, File outputDir, PrintWriter userOutput) throws IOException {
        ZipInputStream zIn = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry;
        while (null != (entry = zIn.getNextEntry())) {
            userOutput.format("Extracting '%s'%n", entry.getName());
            File outputFile = new File(outputDir.toString() + File.separator + entry.getName());
            if (entry.isDirectory())
                outputFile.mkdir();
            else {
                new File(outputFile.getParent()).mkdirs();
                FileOutputStream fOut = new FileOutputStream(outputFile);
                byte buffer[] = new byte[BUFFER_SIZE];
                int bytesRead;
                while (-1 != (bytesRead = zIn.read(buffer)))
                    fOut.write(buffer, 0, bytesRead);
                fOut.close();
            }
        }
        zIn.close();
        userOutput.println("Done.");
    }

    @Override
    public void execute(String[] args, Environment env) {
        Path dir = env.getCurrentDirectory();
        Console console = env.getConsole();
        if (args.length != 2) {
            console.printf("Error: 2 arguments expected%n");
            console.printf("Usage: %s%n", getUsage());
            return;
        }
        File outputFolder = Paths.get(dir.toString(), args[1]).normalize().toFile();
        if (!outputFolder.exists()) {
            console.printf("Error: '%s' folder does not exist%n", args[1]);
            return;
        }
        if (!outputFolder.isDirectory()) {
            console.printf("Error: '%s' is not a directory%n", args[1]);
            return;
        }
        File inputFile = Paths.get(dir.toString(), args[0]).toFile();
        if (!inputFile.exists()) {
            console.printf("Error: '%s' file does not exist%n", args[0]);
            return;
        }
        try {
            unzipFile(inputFile, outputFolder, console.writer());
        } catch (IOException e) {
            console.printf("Error: %s%n", e.getMessage());
        }
    }
}
