package mvc.utils;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PuzzlesFileUtils
{
    public static String correctFileName(String mode)
    {
        String fileName = mode + "_modee.txt";

        return fileName;
    }

    public static ArrayList<String> readPuzzles(String mode)
    {
        String fileName = correctFileName(mode);
        ArrayList<String> list = new ArrayList<>();

        try
        {
            File file = new File(Constants.PUZZLE_FILE_LOCATION + fileName);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
            {
                list.add(sc.nextLine());
            }

        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null,
                                          "Can not read puzzle files!\n" +
                                          "Game stopped without user's permission!!!!",
                                          "",
                                          JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        return list;
    }
}
