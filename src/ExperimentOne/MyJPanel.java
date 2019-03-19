package ExperimentOne;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 简单DI年华
 * @Date: 19-3-17 14:41
 * @Description:
 *
 */
public class MyJPanel extends JPanel {


    //最外层组件 即 JFrame
    JFrame frame = null;

    public MyJPanel(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }



    public void up(Graphics g) throws Exception {



    }

}
