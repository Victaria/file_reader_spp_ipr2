import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReaderThreads extends Thread {
    private static String line = "";
    private File currFile;

    ReaderThreads(String name){
        super.setName(name);
    }

    public void run() {
       // System.out.println("started " + Thread.currentThread().getName());
        String data;
        if (currFile.canRead())
        try {
            BufferedReader in = new BufferedReader( new InputStreamReader(new FileInputStream(currFile), "Cp1251"));
            data = in.readLine();
            String str = "";
                while (data != null) {
                    str = str.concat(data);
                    data = in.readLine();
                }
            //System.out.println(str + "  " + str.indexOf(line) + "  " + str.contains(line));
            if (str.contains(line)) {
                System.out.println(currFile);
            }
            in.close();
        }catch (FileNotFoundException e) {
            System.out.println("Can't find file: " + e);
        } catch (IOException e) {
            System.out.println("file reading problem: " + e);
        }
       // System.out.println("finished " + Thread.currentThread().getName());
    }

    public static void setLine(String line) {
        ReaderThreads.line = line;
    }

    public void setFile(File file){
        currFile = file;
    }

}
