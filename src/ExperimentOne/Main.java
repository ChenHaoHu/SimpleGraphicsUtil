package ExperimentOne;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @Auther: 简单DI年华
 * @Date: 19-3-17 14:24
 * @Description:
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("画图板");
        frame.setBackground(Color.WHITE);
        frame.setSize(1000,600);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyJPanel jPanel = new MyJPanel(frame);
        jPanel.setBackground(Color.YELLOW);
        frame.add(jPanel);
        frame.setLocation(x, y);
        frame.setVisible(true);
        Thread.sleep(1000);
        jPanel.up(jPanel.getGraphics());

        jPanel.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX());
                System.out.println(e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }



}
