package mvc.utils;

import java.awt.*;

public interface Constants
{
    /*
     * Configuration variable.
     */
    public static final String INIT_FILE = "src/config.ini";
    public static final String PUZZLE_FILE_LOCATION = "src/resources/puzzles/";
    public static final String MUSIC_FILE_LOCATION = "src/resources/music/";
    public static final String ICON_IMAGE_FILE_LOCATION = "src/resources/icon_image/";
    public static final String DB_DRIVER = "DB_DRIVER";
    public static final String DB_TIME_ZONE = "DB_TIME_ZONE";
    public static final String DB_URL = "DB_URL";
    public static final String DB_PORT = "DB_PORT";
    public static final String DB_NAME = "DB_NAME";
    public static final String DB_USER = "DB_USER";
    public static final String DB_PASS = "DB_PASS";

    /*
     * Default value in game
     */
    public class InitConfiguration
    {

        public static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        public static String DB_URL = "jdbc:mysql://localhost:";
        public static String DB_PORT = "3306";
        public static String DB_TIME_ZONE = "serverTimezone=UTC";
        public static String DB_NAME = "temp";
        public static String DB_USER = "WRBKOR23";
        public static String DB_PASS = "hai210501";
    }

    public class UIConfiguration
    {
        private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        public static int MENU_SCREEN_WIDTH = 310;
        public static int MENU_SCREEN_HEIGHT = 646;
        public static int PUZZLE_SCREEN_WIDTH = 646;
        public static int PUZZLE_SCREEN_HEIGHT = 646;
        public static int SCREEN_POSITION_X = (gd.getDisplayMode().getWidth() - (MENU_SCREEN_WIDTH + PUZZLE_SCREEN_WIDTH)) / 2;
        public static int SCREEN_POSITION_Y = (gd.getDisplayMode().getHeight() - MENU_SCREEN_HEIGHT) / 2;
        public static Color BG_COLOR_1 = Color.decode("#fd5f92");
        public static Color BG_COLOR_2 = Color.decode("#0f81d8");
        public static Color BG_COLOR_3 = Color.white;
        public static Color FG_GROUND_1 = Color.decode("#0f81d8");
        public static Color FG_GROUND_2 = Color.decode("#2c2c62");
        public static Color FG_GROUND_3 = Color.white;
        public static Color EASY_BUTTON_BG = Color.decode("#1cde12");
        public static Color NORMAL_BUTTON_BG = Color.decode("#c312de");
        public static Color HARD_BUTTON_BG = Color.decode("#ff9317");
        public static Color CHALLENGE_BUTTON_BG = Color.decode("#ff1717");
        public static Color LINE_BORDER_1 = Color.decode("#2197f1");
        public static Color LINE_BORDER_2 = Color.white;
        public static Color LINE_BORDER_3 = Color.decode("#ff33cc");
        public static String FONT_1 = "arial";

    }


}
