package ExperimentOne;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther: 简单DI年华
 * @Date: 19-3-17 14:24
 * @Description:
 *
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("画图板");
        frame.setSize(400,300);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new MyJPanel();
        jPanel.setBackground(Color.WHITE);
        frame.add(jPanel);
        frame.setLocation(x, y);
        frame.setVisible(true);
    }



}
