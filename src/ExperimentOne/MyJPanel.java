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
        GraphicsUtil.drawLineByDDA(10, 20, 320, 230, g);
        g.drawLine(10,20,320,230);
        GraphicsUtil.drawLineByMidPoint(10, 20, 320, 230, g);
        GraphicsUtil.drawLineByBresenham(10, 20, 320, 230, g);
    }
}
