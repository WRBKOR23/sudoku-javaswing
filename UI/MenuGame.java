package UI;

import Model.SudokuModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class MenuGame extends JPanel
{
    private static final Validate validate = new Validate();

    private final DisplayPuzzle display;
    private final SudokuModel sudokuModel;
    private final JTextField[][] textFields;
    private boolean flagStart = false;

    public MenuGame(DisplayPuzzle display, SudokuModel sudokuModel, JTextField[][] textFields)
    {
        this.display = display;
        this.sudokuModel = sudokuModel;
        this.textFields = textFields;

        initGUI();
    }

    private void initGUI()
    {
        removeAll();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createFeatureArea());

        add(timeControl());

        add(createControlArea());

        add(createModeArea());

        setOpaque(true);
        setBackground(Color.red);

        revalidate();
        repaint();
    }

    private JPanel createFeatureArea()
    {
        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        featurePanel.add(createFeatureButton("hint"));
        featurePanel.add(createFeatureButton("check"));
        featurePanel.add(createFeatureButton("music"));
        featurePanel.add(createFeatureButton("help"));

        return featurePanel;
    }

    private JButton createFeatureButton(String nameButton)
    {
        Image img = getIconButton(nameButton, 40);

        JButton button = new JButton(new ImageIcon(img));
        button.setPreferredSize(new Dimension(40, 40));
        button.setActionCommand(nameButton);
        button.addActionListener(new GameController());

        return button;
    }

    private JPanel timeControl()
    {
        JPanel timeArea = new JPanel();
        timeArea.setPreferredSize(new Dimension(300, 150));

        JLabel timeLabel = new JLabel("TIME");
        timeLabel.setPreferredSize(new Dimension(300, 70));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setFont(timeLabel.getFont().deriveFont(20.0f));

        JLabel clockLabel = new JLabel("05:00");
        clockLabel.setPreferredSize(new Dimension(120, 50));
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setFont(clockLabel.getFont().deriveFont(40.0f));

        timeArea.add(timeLabel);
        timeArea.add(clockLabel);

        timeArea.setOpaque(true);
        timeArea.setBackground(Color.blue);

        return timeArea;
    }

    private JPanel createModeArea()
    {
        JPanel modeArea = new JPanel();
        modeArea.setLayout(new BoxLayout(modeArea, BoxLayout.Y_AXIS));

        modeArea.add(createModeButton("Challenge"));
        modeArea.add(createModeButton("Hard"));
        modeArea.add(createModeButton("Normal"));
        modeArea.add(createModeButton("Easy"));

        modeArea.setPreferredSize(new Dimension(300, 250));

        return modeArea;
    }

    private JPanel createModeButton(String mode)
    {
        JPanel modePanel = new JPanel();

        JButton button = new JButton(mode);
        button.setPreferredSize(new Dimension(130, 50));
        button.setFont(button.getFont().deriveFont(18.0f));

        button.setActionCommand(mode.toLowerCase(Locale.ROOT));
        button.addActionListener(new GameController());

        modePanel.add(button);

        modePanel.setOpaque(true);
        modePanel.setBackground(Color.green);

        return modePanel;
    }

    private JPanel createControlArea()
    {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        controlPanel.add(createControlButton("play"));
        controlPanel.add(createControlButton("pause"));
        controlPanel.add(createControlButton("stop"));

        return controlPanel;
    }

    private Image getIconButton(String nameButton, int size)
    {
        String directory = "src/Icon/" + nameButton + "_button.png";

        Image img = null;

        try
        {
            img = ImageIO.read(new File(directory));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        assert img != null;

        img = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);

        return img;
    }

    private JButton createControlButton(String nameButton)
    {
        Image img = getIconButton(nameButton, 60);

        JButton button = new JButton(new ImageIcon(img));
        button.setPreferredSize(new Dimension(50, 50));
        button.setActionCommand(nameButton);
        button.addActionListener(new GameController());

        return button;
    }

    class GameController implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand())
            {
                case "back":

                    break;

                case "easy", "normal", "hard", "challenge":
                    createNewGame(e.getActionCommand());

                    flagStart = true;

                    break;

                case "play":
                    for (int i = 0; i < 9; i++)
                    {
                        for (int j = 0; j < 9; j++)
                        {
                            textFields[i][j].setEnabled(true);
                        }
                    }

//                    for (int j = 0; j < 9; j++)
//                    {
//                        for (int k = 0; k < 9; k++)
//                        {
//                            System.out.print(sudokuModel.getPzArr()[j][k]);
//                        }
//                    }
//                    System.out.println();
//                    display.initGUI();
                    break;

                case "pause":
//                    for (int i = 0; i < 9; i++)
//                    {
//                        for (int j = 0; j < 9; j++)
//                        {
//                            textFields[i][j].setEnabled(false);
//                        }
//                    }
//                    display.setupWaitScreen();
                    break;

                case "hint", "check", "music", "intro":
                    feature(e.getActionCommand());
                    break;

            }
        }

        private void createNewGame(String mode)
        {
            int choice = JOptionPane.showOptionDialog(null,
                    "Bạn có muốn chơi mới không?",
                    "",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, null, null);

            if (choice == JOptionPane.YES_OPTION)
            {
                if (!sudokuModel.changePzArr(mode))
                {
                    JOptionPane.showMessageDialog(null,
                            "Bạn đã chơi hết các câu đố ở chế độ này\n" +
                                    "Vui lòng chọn chế độ khác để tiếp tục chơi!",
                            "",
                            JOptionPane.INFORMATION_MESSAGE);

                    return;
                }

                validate.setPuzzle(sudokuModel.getPzArr());

                display.initGUI();
            }
        }

        private void feature(String actionCommand)
        {
            switch (actionCommand)
            {
                case "hint":
                    if (!flagStart)
                    {
                        return;
                    }

                    char val = validate.hint(display.getCurr());
                    display.suggestion(String.valueOf(val));
                    break;

                case "check":
                    for (int i = 0; i < 9; i++)
                    {
                        for (int j = 0; j < 9; j++)
                        {
                            String tempVal = display.getTextFields()[i][j].getText();
                            String tempRes = String.valueOf(validate.getPuzzle()[i][j]);

                            if (!tempVal.equals(tempRes))
                            {
                                display.getTextFields()[i][j].setForeground(Color.red);
                            }
                        }
                    }
                    break;
            }
        }
    }
}
