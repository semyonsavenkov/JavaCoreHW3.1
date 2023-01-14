import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        //creating a StringBuilder for logging
        StringBuilder logSB = new StringBuilder();

        //creating folders
        createFolder("Games", logSB);
        createFolder("Games/src", logSB);
        createFolder("Games/savegames", logSB);
        createFolder("Games/res", logSB);
        createFolder("Games/temp", logSB);

        createFolder("Games/src/test", logSB);
        createFolder("Games/src/main", logSB);

        createFolder("Games/res/drawables", logSB);
        createFolder("Games/res/vectors", logSB);
        createFolder("Games/res/icons", logSB);

        //creating files
        createFile("Games/temp/temp.txt", logSB);
        createFile("Games/src/main/Main.java", logSB);
        createFile("Games/src/main/Utils.java", logSB);

        //recording whole log
        byte[] bufferedBytes = logSB.toString().getBytes();
        try (FileOutputStream myOut = new FileOutputStream("Games/temp/temp.txt", false);
             BufferedOutputStream myBOS = new BufferedOutputStream(myOut)) {
            myBOS.write(bufferedBytes, 0, bufferedBytes.length);
        } catch (IOException myEx) {
            System.out.println(myEx.getMessage());
        }
    }

    private static void createFile(String fileName, StringBuilder logSB) {
        File myFile = new File(fileName);
        String logText = "";
        try {
            if (myFile.createNewFile())
                logText = getStringDate() + " создан файл " + fileName + "\n";
            System.out.println(logText);
        } catch (IOException ex) {
            logText = getStringDate() + " не удалось создать файл " + fileName + "\n" + ex.getMessage() + "\n";
            System.out.println(logText);
        }

        logSB.append(logText);

    }

    private static void createFolder(String folderName, StringBuilder logSB) {
        File myDir = new File(folderName);
        String logText = "";
        if (myDir.mkdir()) {
            logText = getStringDate() + " создан каталог " + myDir.getAbsolutePath() + "\n";
            System.out.println(logText);
        } else {
            logText = getStringDate() + " не удалось создать каталог " + folderName + "\n";
            System.out.println(logText);
        }
        logSB.append(logText);
    }

    public static String getStringDate () {
        SimpleDateFormat myDF = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        return myDF.format(new Date());
    }

}
