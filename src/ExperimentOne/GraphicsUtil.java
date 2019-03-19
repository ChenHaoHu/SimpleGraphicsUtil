package ExperimentOne;


import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * @Auther: 简单DI年华
 * @Date: 19-3-17 17:13
 * @Description:
 *
 *
 * todo: 画圆时，采用移动坐标原点的办法失败，猜想是画图采用多线程
 *
 *
 *
 */
public class GraphicsUtil {


    public static void fillArcBySeedFour(int x, int y, Color newColor, Graphics g, JComponent component) throws AWTException {


        //Todo: 需要解耦
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x0 = (int)(toolkit.getScreenSize().getWidth()-400)/2+9;
        int y0 = (int)(toolkit.getScreenSize().getHeight()-300)/2+31;

        Robot robot = new Robot();
        x = x + x0 ;
        y = y + y0 ;
        g.setColor(newColor);
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(x,y));
        drawPoint(x-x0,y-y0,g);

        boolean flag = true;

        while (!stack.empty() || flag == true){

            if (stack.empty() && flag == true){
                x0--;
                flag = !flag;
                stack.push(new Point(x-1,y));
            }



            Point pop = stack.pop();
            x = (int) pop.getX();
            y = (int) pop.getY();



            if (!robot.getPixelColor(x,y+1).equals(newColor) && !robot.getPixelColor(x,y+1).equals(Color.BLACK)){
                stack.push(new Point(x,y+1));
                drawPoint(x-x0,y-y0+1,g);
                System.out.println(1);
            }


            if (!robot.getPixelColor(x+1,y).equals(newColor) && !robot.getPixelColor(x+1,y).equals(Color.BLACK)){
                stack.push(new Point(x+1,y));
                drawPoint(x-x0+1,y-y0,g);
                System.out.println(2);
            }


            if (!robot.getPixelColor(x,y-1).equals(newColor) && !robot.getPixelColor(x,y-1).equals(Color.BLACK)){
                stack.push(new Point(x,y-1));
                drawPoint(x-x0,y-y0-1,g);
                System.out.println(3);
            }

            if (!robot.getPixelColor(x-1,y).equals(newColor) && !robot.getPixelColor(x-1,y).equals(Color.BLACK)){
                stack.push(new Point(x-1,y));
                drawPoint(x-x0-1,y-y0,g);
                System.out.println(4);
            }



        }

        System.out.println("out");
    }

    /**
     * 使用中点画圆法
     * https://blog.csdn.net/zl908760230/article/details/53954746
     * @param x
     * @param y
     * @param r
     * @param g
     */
    public static void drawCircleByMidPoint(int x,int y,int r,Graphics g){
        int x0 = x;
        int y0 = y;
        x = 0;
        y = r;
        int d = 1 - r;
        drawEightPoint(x,y,x0,y0,g);
        while (x <= y) {
            if (d < 0) {
                d += 2 * x + 3;
                x++;
            }
            else {
                d += 2 * (x - y) + 5;
                x++;
                y--;
            }
            drawEightPoint(x,y,x0,y0,g);
        }

    }

    /**
     * https://blog.csdn.net/sinat_41104353/article/details/82961824
     * Bresenham 算法画圆
     * @param x
     * @param y
     * @param R
     * @param g
     */
    public static void drawCircleByBresenham(int x,int y,int R,Graphics g){

        int p;
        p=3-2*R;
        int x0 = x;
        int y0 = y;
        x = 0;
        y = R;
        for(;x<=y;x++){
            drawEightPoint(x,y,x0,y0,g);
            if(p>=0){
                p+=4*(x-y)+10;
                y--;
            }
            else {
                p+=4*x+6;
            }
        }
    }

    /**
     * 画八个对称的点
     * https://img-blog.csdnimg.cn/20190107225414814.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NpbmF0XzQxMTA0MzUz,size_16,color_FFFFFF,t_70
     * @param x
     * @param y
     * @param g
     */
    public static void drawEightPoint(int x,int y,int x0, int y0,Graphics g){

        drawPoint(x0 + x, y0 + y, g);
        drawPoint(x0 - x, y0 + y, g);
        drawPoint(x0 + x, y0 - y, g);
        drawPoint(x0 - x, y0 - y, g);

        drawPoint(x0 + y, y0 + x, g);
        drawPoint(x0 - y, y0 + x, g);
        drawPoint(x0 + y, y0 - x, g);
        drawPoint(x0 - y, y0 - x, g);

    }

    /**
     * 画点
     * @param x
     * @param y
     * @param g
     */
    public static void drawPoint(int x, int y, Graphics g) {
        g.drawLine(x, y, x + 1, y);
    }


    /**
     * DDA算法画线
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param g
     */
    public static  void drawLineByDDA(int x1, int y1, int x2, int y2, Graphics g) {

        int length = 0;
        double dy = 0;
        double dx = 0;
        double x = x1;
        double y = y1;
        int k = 0;
        length = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        dx = (double) (x2 - x1) / length;
        dy = (double) (y2 - y1) / length;
        while (k < length) {
            drawPoint((int) x, (int) y, g);
            x = x + dx;
            y = y + dy;
            k++;
        }

    }


    /**
     * 中点算法画线
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param g
     */
    public static void drawLineByMidPoint(int x1, int y1, int x2, int y2, Graphics g) {
        int x, y, a, b, d, d1, d2;
        a = y1 - y2;
        b = x2 - x1;
        x = x1;
        y = y1;
        d = 2 * a + b;
        d1 = 2 * a;
        d2 = 2 * (a + b);

        drawPoint(x, y, g);

        for (x = x1; x <= x2; x++) {

            if (d < 0) {
                y++;
                d += d2;
            } else {
                d += d1;
            }

            drawPoint(x, y, g);
        }
    }

    /**
     * 参考 https://blog.csdn.net/cjw_soledad/article/details/78886117
     *Bresenham算法
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param g
     */
    public static void drawLineByBresenham(int x1, int y1, int x2, int y2, Graphics g) {

        //得出差值
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        //判断大小
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;


        int err = (dx > dy ? dx : -dy) / 2;
        int e2 = 0;

        for (; ; ) {
            drawPoint(x1, y1, g);
            if (x1 == x2 && y1 == y2) break;
            e2 = err;
            if (e2 > -dx) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dy) {
                err += dx;
                y1 += sy;
            }
        }

    }


}
