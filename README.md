# SimpleGraphicsUtil
`SimpleGraphicsUtil is a tool to solveteachers'  inspect`

> 计算机图形学作业


##### 更新日志


* 提交三种常见画直线代码
* 提交两种常见画圆代码
* 提交四连通种子填充代码
* 优化填充算法，替换robot `getPixelColor`方法耗时
* 完成图像制作 -- 可爱的大白



> 可爱的大白



![](https://github.com/ChenHaoHu/SimpleGraphicsUtil/blob/master/show.gif)




> 画直线

1. DDA直线生成算法

```java
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
```

2. 中点画线法

```java
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
```

3. Bresenham直线生成法

```java
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
```





> 画圆

1. 中点画圆法

```java
public static synchronized void drawCircleByMidPoint(int x,int y,int r,Graphics g){
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
```

2. Bresenham生成算法

```java
public static  void drawCircleByBresenham(int x,int y,int R,Graphics g){

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
```



> 对应的`drawEightPoint`方法的实现

`就是实现八个对称点，所以我们只需要画一部分圆弧即可`

```java
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
```



> 填充算法  

1. 四连通种子填充

```java
public static void fillArcBySeedFour(int x, int y, Color newColor, Color bordColor, Graphics g, JFrame frame) throws AWTException {

    // 9 和 31 是边框引起的
    int x0 = frame.getX()+9;
    int y0 = frame.getY()+31;

    Robot robot = new Robot();
    x = x + x0 ;
    y = y + y0 ;
    g.setColor(newColor);
    Stack<Point> stack = new Stack<>();
    stack.push(new Point(x,y));

    drawPoint(x-x0,y-y0,g);

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    BufferedImage screenCapture = null;




    boolean flag = true;

    screenCapture  = robot.createScreenCapture(new Rectangle(0, 0, toolkit.getScreenSize().width, toolkit.getScreenSize().height));


    while (!stack.empty() || flag == true){

        //为了让他画两边，如果没有只会画一边，可能原因是因为我画点是用了 【 g.drawLine(x, y, x + 1, y);】存在进一的问题
        if (stack.empty() && flag == true){
            x0--;
            flag = !flag;
            stack.push(new Point(x-1,y));
        }


        Point pop = stack.pop();
        x = (int) pop.getX();
        y = (int) pop.getY();

        screenCapture.getRGB(x, y);

        if (screenCapture.getRGB(x,y+1) != newColor.getRGB() && screenCapture.getRGB(x,y+1) != bordColor.getRGB()){
            stack.push(new Point(x,y+1));
            drawPoint(x-x0,y-y0+1,g);
            screenCapture.setRGB(x,y+1,newColor.getRGB());
        }


        if (screenCapture.getRGB(x+1,y) != newColor.getRGB() && screenCapture.getRGB(x+1,y) != bordColor.getRGB()){
            stack.push(new Point(x+1,y));
            drawPoint(x-x0+1,y-y0,g);
            screenCapture.setRGB(x+1,y,newColor.getRGB());
        }


        if (screenCapture.getRGB(x,y-1) != newColor.getRGB() && screenCapture.getRGB(x,y-1) != bordColor.getRGB()){
            stack.push(new Point(x,y-1));
            drawPoint(x-x0,y-y0-1,g);
            screenCapture.setRGB(x,y-1,newColor.getRGB());
        }

        if (screenCapture.getRGB(x-1,y) != newColor.getRGB() && screenCapture.getRGB(x-1,y) != bordColor.getRGB()){
            stack.push(new Point(x-1,y));
            drawPoint(x-x0-1,y-y0,g);
            screenCapture.setRGB(x-1,y,newColor.getRGB());
        }



    }

}
```

2. 扫描线种子填充算法

>对于区域内的每一像素段，只保留其最右
>（或左）端的像素作为种子像素。适用于边界
>定义的区域。区域可以是凸的，也可以是凹
>的，还可以包含一个或多个孔。

##### 算法由四步实现

- [x] 初始化一个空的栈用于存放种子点，将种子点(x, y)入栈；
- [x] 判断栈是否为空，如果栈为空则结束算法，否则取出栈顶元素作为当前扫描线的种子点(x, y)，y是当前的扫描线；
- [x] 从种子点(x, y)出发，沿当前扫描线向左、右两个方向填充，直到边界。分别标记区段的左、右端点坐标为xLeft和xRight；
- [x] `分别检查与当前扫描线相邻的y - 1和y + 1两条扫描线在区间[xLeft, xRight]中的像素，从xLeft开始向xRight方向搜索，若存在非边界且未填充的像素点，则找出这些相邻的像素点中最右边的一个，并将其作为种子点压入栈中，然后返回第（2）步；`

