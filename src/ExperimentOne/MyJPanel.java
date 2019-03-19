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
 * frame:400,300
 *
 * 1．巩固图形学中关于绘制直线和圆的算法；
 * 2．任选一种画直线（DDA法,中点画线法和Bresenham画线法）画线
 * 3. 任选一种画圆弧方法（中点画圆法和Bresenham画圆法）画圆绘制圆；
 * 4. 要求设计一个具有实际意义的图形，使用直线和圆的生成算法；
 * 注意：本次实验要求不能直接使用系统提供的画线和画圆函数，可以调用其他的画图函数。
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

        Color color1 = Color.lightGray;
        Color color_Bord = Color.GRAY;
        int r_hread = 30;



        int loc_x = 300;
        int loc_y = 200;


        g.setColor(Color.WHITE);
        GraphicsUtil.drawCircleByBresenham(loc_x,loc_y+r_hread*3+40,r_hread*3,g);

        //画头
        g.setColor(Color.WHITE);
        g.fillArc(loc_x-50,loc_y,100,60,0,360);
        g.setColor(Color.DARK_GRAY);
        GraphicsUtil.drawCircleByBresenham(loc_x-20,loc_y+30,10,g);
        GraphicsUtil.drawCircleByBresenham(loc_x+20,loc_y+30,10,g);
        GraphicsUtil.drawLineByBresenham(loc_x-10,loc_y+30,loc_x+10,loc_y+30,g);
        g.setColor(Color.WHITE);
        GraphicsUtil.drawCircleByBresenham(loc_x+60,loc_y+r_hread*3+40,r_hread*2,g);
        GraphicsUtil.drawCircleByBresenham(loc_x-60,loc_y+r_hread*3+40,r_hread*2,g);


        ExecutorService executorService = Executors.newFixedThreadPool(6);
        CountDownLatch countDownLatch = new CountDownLatch(6);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    GraphicsUtil.fillArcBySeedFour(loc_x,loc_y+r_hread*3,Color.WHITE,color_Bord,g,frame);
                    countDownLatch.countDown();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    GraphicsUtil.fillArcBySeedFour(loc_x+60,loc_y+r_hread*3+40,Color.WHITE,color_Bord,g,frame);
                    countDownLatch.countDown();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    GraphicsUtil.fillArcBySeedFour(loc_x-60,loc_y+r_hread*3+40,Color.WHITE,color_Bord,g,frame);
                    countDownLatch.countDown();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    GraphicsUtil.fillArcBySeedFour(loc_x-100,loc_y+r_hread*3+40,Color.WHITE,color_Bord,g,frame);
                    countDownLatch.countDown();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    GraphicsUtil.fillArcBySeedFour(loc_x+100,loc_y+r_hread*3+40,Color.WHITE,color_Bord,g,frame);
                    countDownLatch.countDown();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    GraphicsUtil.fillArcBySeedFour(loc_x,loc_y+r_hread*3+90,Color.WHITE,color_Bord,g,frame);
                    countDownLatch.countDown();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
        countDownLatch.await();
        g.setColor(Color.GRAY);


//        GraphicsUtil.drawLineByBresenham(loc_x-r_hread/3,loc_y+r_hread,loc_x-r_hread,loc_y+r_hread*2,g);
//        GraphicsUtil.drawLineByBresenham(loc_x+r_hread/3,loc_y+r_hread,loc_x+r_hread,loc_y+r_hread*2,g);


    }

}
