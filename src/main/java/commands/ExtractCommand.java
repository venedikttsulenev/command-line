package commands;

import utils.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;

public class ExtractCommand extends Command {

    private static final ExtractCommand instance = new ExtractCommand();

    public static ExtractCommand getInstance() {
        return instance;
    }

    private ExtractCommand() {
        super("ex", "ex <dirA> <dirB>", "extract mp3 files for dir A to B");
    }

    private static final int MEGABYTE = 1048576;

    static int count = 0;

    private static void concatFiles(File[] inputFiles, File outputFile) throws IOException {
        outputFile.createNewFile();
        FileOutputStream fOut = new FileOutputStream(outputFile);
        byte buffer[] = new byte[10 * MEGABYTE];
        for (File f : inputFiles) {
            if (f == null) {
                break;
            }
            FileInputStream fIn = new FileInputStream(f);
            int bytesRead;
            while (-1 != (bytesRead = fIn.read(buffer))) {
                fOut.write(buffer, 0, bytesRead);
            }
        }
    }

    public void execute(String[] args, Environment env) {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext pcontext = new ParseContext();
        File in = new File(args[0]);
        File out = new File(args[1]);
        File[] input = new File[1];
        th:
        for (File f : in.listFiles()) {
            try {
                FileInputStream inputstream = new FileInputStream(f);
                Mp3Parser mp3Parser = new Mp3Parser();
                mp3Parser.parse(inputstream, handler, metadata, pcontext);
                String[] metadataNames = metadata.names();
                if (metadataNames.length < 3) {
                    continue th;
                }
                input[0] = f;
                File output = new File(out, "\\" + count + ".mp3");
                count++;
                concatFiles(input, output);

            } catch (TikaException e) {
                System.out.println("Document " + f + " unparcable");
                continue;
            } catch (IOException e) {
                System.out.println("Stop");
                e.printStackTrace();
                return;

            } catch (SAXException e) {
                System.out.println("Stop");
                e.printStackTrace();
                return;
            }
        }
    }
}
