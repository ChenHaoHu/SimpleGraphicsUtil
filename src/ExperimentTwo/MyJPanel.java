package ExperimentTwo;


import GraphicsUtil.GraphicsUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther: 简单DI年华
 * @Date: 19-4-10 14:24
 * @Description:
 *
 */
public class MyJPanel extends JPanel {

    //最外层组件 即 JFrame
    JFrame frame = null;

    public MyJPanel(JFrame frame) {
        super();
        this.frame = frame;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

    public void doMyPaint(int[][] points,int step,int n,Graphics g,int power) {

        GraphicsUtil.drawPoints(points,g,power, n,Color.red);
        for (int i = 0; i < n-3; i++) {
            GraphicsUtil.drawB_Spline3(points,i,step,g);
        }

    }


}
