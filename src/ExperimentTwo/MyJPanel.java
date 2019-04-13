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

    public void doMyPaint(int[][] points,int step,int n,Graphics g,int power,int type) {

        GraphicsUtil.drawPoints(points,g,power, n,Color.red);
        if (type == 1){
            GraphicsUtil.drawBezier(points,n,step,g);
        }else{
            GraphicsUtil.drawB_Spline(points,n,step,g);
        }

    }


}
