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