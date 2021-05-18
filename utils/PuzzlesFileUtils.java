package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PuzzlesFileUtils
{
    /*
     * Correct the file name
     */
    public static String correctFileName(String mode)
    {
        String fileName = mode + "_mode.txt";

        return fileName;
    }

    /*
     * Load game from file
     */
    public static ArrayList<String> readPuzzles(String mode)
    {
        String            fileName = correctFileName(mode);
        ArrayList<String> list     = new ArrayList<>();

        try
        {
            File    file = new File(Constants.PUZZLE_FILE_LOCATION + fileName);
            Scanner sc   = new Scanner(file);

            while (sc.hasNextLine())
            {
                list.add(sc.nextLine());
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
        finally
        {
            return list;
        }
    }
}
