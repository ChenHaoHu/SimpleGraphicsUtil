# SimpleGraphicsUtil
SimpleGraphicsUtil is a tool for teachers'  inspect

> 计算机图形学作业


##### 更新日志


* 提交三种常见画直线代码
* 提交两种常见画圆代码



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



### 对应的`drawEightPoint`方法的实现

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