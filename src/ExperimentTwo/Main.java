package ExperimentTwo;

import GraphicsUtil.GraphicsUtil;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @Auther: 简单DI年华
 * @Date: 19-4-10 14:24
 * @Description:
 *
 */
public class Main {


    static int n = 0;

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("画图板");
        frame.setBackground(Color.WHITE);
        frame.setSize(1000,550);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocation(x, y);
        MyJPanel jPanel = new MyJPanel(frame);
        jPanel.setBackground(new Color(249, 231, 255, 0));
        frame.add(jPanel);
        jPanel.setLayout(null);
        JButton but = new JButton("删除点");
        but.setSize(100,40);
        but.setLocation(20,20);

        jPanel.add(but);

        int[][]  points = new int[50][2];
        //预备的点   可以修改
        int power = 10;  //标记的粗度
        int step = 50;   //步长

        but.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
              if (but.getText().equals("删除点")){
                  but.setText("显示点");
                  GraphicsUtil.drawPoints(points,jPanel.getGraphics(),power,n,Color.WHITE);
              }else{
                  but.setText("删除点");
                  GraphicsUtil.drawPoints(points,jPanel.getGraphics(),power,n,Color.RED);

              }

            }
        });


        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX());
                System.out.println(e.getY());
                points[n][0] = e.getX();
                points[n++][1] = e.getY();
                jPanel.doMyPaint(points,step,n,jPanel.getGraphics(),power);
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

        jPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getX();
                int y = e.getY();

                for (int i = 0; i < points.length; i++) {
                    if (x > points[i][0]-power && x < points[i][0]+power && y < points[i][1]+power && y > points[i][1]-power){
                        points[i][0] = e.getX();
                        points[i][1] = e.getY();
                        jPanel.getGraphics().clearRect(60,60,jPanel.getWidth(),jPanel.getHeight());
                        jPanel.doMyPaint(points,step,n,jPanel.getGraphics(),power);
                    }
                }
            }


        });

    }



}
