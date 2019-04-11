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