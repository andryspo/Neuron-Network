package sample.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtil implements OutputUtill {

    private File file;

    public FileUtil(String fileName) throws IOException {
        file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        file.getParentFile().mkdirs();
        file.delete();
        file.createNewFile();
    }

    public void append(String s) throws IOException {
        try(FileWriter writer = new FileWriter(file, true)) {
            writer.append(s);
        }
    }
}
