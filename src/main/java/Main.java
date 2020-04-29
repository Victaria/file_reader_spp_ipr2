import javafx.scene.shape.Path;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    String pattern = "D:\\Disk_D\\bsuir\\testDir";
    static ArrayList<File> filesList = new ArrayList<File>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter directory path:");
        File dir = new File(in.next());
        if (dir.isDirectory()) {
            System.out.println("How many files do you want to open simultaneously?");
            if (in.hasNextInt()) {
                int count = in.nextInt();
                if (count > 0) {
                    System.out.println("Enter String, which you want to find.");
                    String line = in.next();

                    filesInDir(dir);

                    readFiles(count, line);
                } else System.out.println("Incorrect input. Number must be > 0.");
            } else System.out.println("Incorrect input.");

        } else System.out.println("it's not a directory.");
    }

    public static void filesInDir(File dir){
        for (File file : dir.listFiles())
        {
            if (file.isDirectory()) filesInDir(file);
            else {
                filesList.add(file);
               // System.out.println(file.getName());
            }
        }
    }

    public static void readFiles(int count, String line){
        System.out.println("Those files contains String '" + line + "':");
        ReaderThreads.setLine(line);
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(count);

        for (int i = 0; i < filesList.size(); i++){
            ReaderThreads rf = new ReaderThreads("thread" + i);
            rf.setFile(filesList.get(i));
            pool.execute(rf);
        }
        pool.shutdown();
    }
}
