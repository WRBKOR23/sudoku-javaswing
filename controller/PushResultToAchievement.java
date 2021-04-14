//package Controller;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class PushResultToAchievement extends JFrame
//{
//
//    private JTextField nicknameFields;
//    private String nickname;
//    private JButton confirmBt;
//
//    private boolean flag = false;
//
//    public PushResultToAchievement(String title)
//    {
//        super(title);
//        nickname = "a";
//        initGUI();
//        addEvent();
//    }
//
//    public boolean isFlag()
//    {
//        return flag;
//    }
//
//    public String getNickname()
//    {
//        return nickname;
//    }
//
//    public void initGUI()
//    {
//        Container container = getContentPane();
//        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
//
//        JLabel userNameLabel = new JLabel("Nickname: ");
//        userNameLabel.setFont(new Font("arial", Font.BOLD, 15));
//
//        nicknameFields = new JTextField();
//        nicknameFields.setPreferredSize(new Dimension(200, 30));
//        nicknameFields.setFont(new Font("arial", Font.BOLD, 15));
//
//        JPanel userNamePanel = new JPanel();
//        userNamePanel.add(userNameLabel);
//        userNamePanel.add(nicknameFields);
//
//        confirmBt = new JButton("Confirm");
//        confirmBt.setPreferredSize(new Dimension(100, 40));
//
//        JPanel confirmPanel = new JPanel();
//        confirmPanel.setMaximumSize(new Dimension( 350, 70));
//        confirmPanel.add(confirmBt);
//
//        container.add(userNamePanel);
//        container.add(confirmPanel);
//    }
//
//    private void addEvent()
//    {
//        confirmBt.addActionListener(new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                confirm();
//                if (flag)
//                {
//                    dispose();
//                    notice();
//                }
//            }
//        });
//    }
//
//    private void confirm()
//    {
//        if (nicknameFields.getText().equals(""))
//        {
//            return;
//        }
//        nickname = nicknameFields.getText();
//
//        flag = true;
//    }
//
//    private void notice()
//    {
//        JOptionPane.showMessageDialog(null,
//                "Bạn đã hoàn thành phần chơi!\n" +
//                        "Hãy chọn game mới để tiếp tục chơi",
//                "",
//                JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    public void showGUI()
//    {
//        setSize( 350, 150);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setVisible(true);
//    }
//
//    public static void main(String[] args)
//    {
//        PushResultToAchievement l = new PushResultToAchievement("Thử nghiệm");
//        l.showGUI();
//
//    }
//}
