package ExperimentOne;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @Auther: 简单DI年华
 * @Date: 19-3-17 14:41
 * @Description:
 *
 * frame:400,300
 *
 * 1．巩固图形学中关于绘制直线和圆的算法；
 * 2．任选一种画直线（DDA法,中点画线法和Bresenham画线法）画线
 * 3. 任选一种画圆弧方法（中点画圆法和Bresenham画圆法）画圆绘制圆；
 * 4. 要求设计一个具有实际意义的图形，使用直线和圆的生成算法；
 * 注意：本次实验要求不能直接使用系统提供的画线和画圆函数，可以调用其他的画图函数。
 */
public class MyJPanel extends JPanel {

    int x0 = 0;
    int y0 = 0;
    int x1 = 0;
    int y1 = 0;

    boolean flag = false;

    public MyJPanel() {

        MyJPanel a = this;
        this.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
               if (flag == false){
                   x0 = (int)point.getX();
                   y0 =  (int)point.getY();
               }else{
                   x1 = (int)point.getX();
                   y1 =  (int)point.getY();
               }

               flag = !flag;

                a.repaint();
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

       if (flag == false){
           g.setColor(Color.RED);
           g.drawLine( x1,y1,x0,y0);
       }else{
           g.setColor(Color.RED);
           g.drawLine( x0,y0,x1,y1);
       }
        try {
            test1(g);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test1(Graphics g) throws InterruptedException {
        g.setColor(Color.BLACK);

        GraphicsUtil.drawCircleByMidPoint(150,230,30,g);
        GraphicsUtil.drawCircleByMidPoint(250,230,30,g);

        GraphicsUtil.drawLineByBresenham(170,210,170,80,g);
        GraphicsUtil.drawLineByBresenham(230,210,230,80,g);

        GraphicsUtil.drawLineByBresenham(170,80,200,40,g);
        GraphicsUtil.drawLineByBresenham(230,80,200,40,g);

        GraphicsUtil.drawLineByBresenham(120,220,100,200,g);
        GraphicsUtil.drawLineByBresenham(280,220,300,200,g);

        GraphicsUtil.drawLineByBresenham(120,210,90,150,g);
        GraphicsUtil.drawLineByBresenham(280,210,310,150,g);

        g.drawString("大",195,100);
        g.drawString("宝",195,130);
        g.drawString("剑",195,160);

    }


}
