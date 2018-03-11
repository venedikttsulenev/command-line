package commands;

import utils.Environment;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCommand extends Command {

    private static final ZipCommand instance = new ZipCommand();
    private static final int MEGABYTE = 1048576;
    private static final int BUFFER_SIZE = MEGABYTE;

    public static ZipCommand getInstance() {
        return instance;
    }

    private ZipCommand() {
        super("zip", "zip <file1> <file2> ... <output file>", "create zip archive");
    }

    private void zipFile(String root, File file, ZipOutputStream zipOut) throws IOException {
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            if (list == null)
                return; /* This is not good */
            String newRoot = root + file.getName() + File.separator;
            if (list.length != 0) { /* If folder is not empty */
                for (File f : list)
                    zipFile(newRoot, f, zipOut);
            }
            else { /* Include empty folders */
                zipOut.putNextEntry(new ZipEntry(root + file.getName() + File.separator));
                zipOut.closeEntry();
            }
        } else {
            FileInputStream fIn = new FileInputStream(file);
            ZipEntry entry = new ZipEntry(root + file.getName());
            zipOut.putNextEntry(entry);
            int bytesRead;
            byte[] buffer = new byte[BUFFER_SIZE];
            while (-1 != (bytesRead = fIn.read(buffer, 0, BUFFER_SIZE)))
                zipOut.write(buffer, 0, bytesRead);
            fIn.close();
            zipOut.closeEntry();
        }
    }

    @Override
    public void execute(String[] args, Environment env) {
        Path dir = env.getCurrentDirectory();
        Console console = env.getConsole();
        if (args.length < 2) {
            console.printf("Error: Too few arguments: at least 2 expected%n");
            console.printf("Usage: %s%n", getUsage());
        }
        else {
            File outputFile = Paths.get(dir.toString(), args[args.length - 1]).toFile();
            try {
                outputFile.createNewFile();
                ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(outputFile));
                for (int i = 0; i < args.length - 1; ++i) {
                    File file = Paths.get(dir.toString(), args[i]).toFile();
                    if (!file.exists())
                        console.printf("Error: file '%s' does not exist%n", args[i]);
                    else
                        try {
                            zipFile("", file, zipOut);
                        } catch (IOException e) {
                            console.printf("Error: '%s'%n", e.getMessage());
                        }
                }
                zipOut.close();
            } catch (IOException e) {
                console.printf("Error: could create file '%s': %s%n", outputFile.getName(), e.getMessage());
            }
        }
    }
}
