package ExperimentOne;

import javax.swing.*;
import java.awt.*;

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
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        drawLineByDDA(10,20,320,230,g);
        g.drawLine(10,20,320,230);
        drawLineByMidPoint(10,20,320,230,g);
    }

    void drawPoint(int x,int y,Graphics g){
        g.drawLine(x,y,x+1,y);
    }

    void drawLineByDDA(int x1,int y1,int x2,int y2,Graphics g){

        int length = 0;
        double dy = 0;
        double dx = 0;
        double x = x1;
        double y = y1;
        int k = 0;
        length = Math.max(Math.abs(x2-x1),Math.abs(y2-y1));
        dx = (double)(x2-x1)/length;
        dy = (double) (y2-y1)/length;
        System.out.println(dx+"  "+dy);
        while (k < length){
            drawPoint((int)x,(int)y,g);
            x = x + dx ;
            y = y + dy;
            k++;
            System.out.println(x+"===="+y);
        }

    }

    void drawLineByMidPoint(int x1,int y1,int x2,int y2,Graphics g){
        int x,y,a,b,d,d1,d2;
        a = y1-y2;
        b = x2-x1;
        x = x1;
        y = y1;
        d = 2*a+b;
        d1 = 2*a;
        d2 = 2*(a+b);

        drawPoint(x,y,g);

        for(x=x1;x<=x2;x++){

            if(d<0){
                y++;
                d+=d2;
            }else{
                d+=d1;
            }

            drawPoint(x,y,g);
        }
    }

    void drawLineByBresenham(int x1,int y1,int x2,int y2,Graphics g){

    }


}
