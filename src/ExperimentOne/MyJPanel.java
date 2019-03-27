package ExperimentOne;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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



    public void bg(Graphics g) throws Exception {


        List<String> strings = new ArrayList<>();
        strings.add("我不是胖嘟嘟，只是气很足。");
        strings.add("我长成这样是为了让人看起来更想拥抱。");
        strings.add("如果你不开心，那你就欺负我好了，因为我喜欢你。");
        strings.add("你是我的病人，你的健康是我唯一的关注");
        strings.add("你嫌我胖，可这不是也蛮可爱的嘛。不信，你来戳戳咯");

        Color color_Bord = Color.GRAY;
        int r_hread = 30;



        int loc_x = 300;
        int loc_y = 200;

        BufferedImage logo = ImageIO.read(new File("logo.png"));

        g.drawImage(logo,0,0,null);

        g.setColor(Color.WHITE);
        GraphicsUtil.drawCircleByBresenham(loc_x,loc_y+r_hread*3+40,r_hread*3,g);

        //画头
        g.setColor(Color.WHITE);
        g.fillArc(loc_x-50,loc_y,100,60,0,360);
        g.setColor(Color.DARK_GRAY);
        GraphicsUtil.drawCircleByBresenham(loc_x-20,loc_y+30,10,g);
        GraphicsUtil.drawCircleByBresenham(loc_x+20,loc_y+30,10,g);
        GraphicsUtil.drawLineByBresenham(loc_x-10,loc_y+28,loc_x+10,loc_y+28,g);
        GraphicsUtil.drawLineByBresenham(loc_x-10,loc_y+32,loc_x+10,loc_y+32,g);
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

        GraphicsUtil.fillArcBySeedFour(280,229,Color.BLACK,Color.DARK_GRAY,g,frame);
        GraphicsUtil.fillArcBySeedFour(324,229,Color.BLACK,Color.DARK_GRAY,g,frame);
        GraphicsUtil.fillArcBySeedFour(304,229,Color.BLACK,Color.DARK_GRAY,g,frame);

        g.setColor(Color.WHITE);
        g.fillArc(loc_x-40,loc_y+170,40,100,0,360);
        g.fillArc(loc_x+10,loc_y+170,40,100,0,360);

        GraphicsUtil.fillArcBySeedFour(324,418,Color.WHITE,Color.GRAY,g,frame);
        GraphicsUtil.fillArcBySeedFour(263,435,Color.WHITE,Color.GRAY,g,frame);

        g.setColor(Color.RED);
        GraphicsUtil.drawCircleByBresenham(340,290,9,g);
        GraphicsUtil.drawCircleByBresenham(340,290,10,g);
        GraphicsUtil.drawLineByBresenham(340-9,290+3,340-3,290+3,g);
        GraphicsUtil.drawLineByBresenham(340-3,290+3,340-3,290-3,g);
        GraphicsUtil.drawLineByBresenham(340-3,290-3,340+3,290-3,g);
        GraphicsUtil.drawLineByBresenham(340+3,290+3,340+3,290-3,g);
        GraphicsUtil.drawLineByBresenham(340+3,290+3,340+9,290+3,g);



        g.setColor(Color.BLACK);
        GraphicsUtil.drawLineByBresenham(389,360,431,160,g);

        g.setColor(Color.GRAY);
        GraphicsUtil.drawCircleByBresenham(431,161,40,g);
        GraphicsUtil.drawCircleByBresenham(397,111,20,g);
        GraphicsUtil.drawCircleByBresenham(476,123,20,g);

        GraphicsUtil.fillArcBySeedFour(421,161,Color.YELLOW,Color.GRAY,g,frame);
        GraphicsUtil.fillArcBySeedFour(394,111,Color.YELLOW,Color.GRAY,g,frame);
        GraphicsUtil.fillArcBySeedFour(466,123,Color.YELLOW,Color.GRAY,g,frame);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                g.setColor(new Color(195,2,28));
                g.fillRect(500,100,600,120);

                int i = 0;
                for (; ; ) {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("楷体",1,20));
                    g.drawString(strings.get(i++%strings.size()),500,200);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    g.setColor(new Color(195,2,28));
                    g.fillRect(500,100,600,120);
                }
            }
        });

        rain(g);
    }

    public void rain(Graphics g) {

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            drawRain(random.nextInt(1000),random.nextInt(550),g);
        }

    }

    public void drawRain(int x, int y,Graphics g){

        g.setColor(Color.WHITE);
        GraphicsUtil.drawLineByBresenham(x,y,x-2,y+4,g);
    }
}
