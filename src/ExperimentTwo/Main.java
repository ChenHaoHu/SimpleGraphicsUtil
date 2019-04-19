package ExperimentTwo;

import GraphicsUtil.GraphicsUtil;
import jdk.nashorn.internal.scripts.JO;
import jdk.nashorn.internal.scripts.JS;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Properties;

/**
 * @Auther: 简单DI年华
 * @Date: 19-4-10 14:24
 * @Description:
 *
 */
public class Main {

    static JFrame frame = null;
    static MyJPanel jPanel = null;
    static int n = 0;
    static int[][]  points = new int[300][2];
    //预备的点   可以修改
    static int power = 10;  //标记的粗度
    static int step = 50;   //步长
    static int type = 2;
    static String openFilePath = null;


    public static void main(String[] args)  {

        frame = new JFrame("画图板");
        frame.setBackground(Color.WHITE);
        frame.setSize(1200,550);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
        //添加了监听事件
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocation(x, y);
        jPanel = new MyJPanel(frame);
        jPanel.setBackground(new Color(249, 231, 255, 0));
        frame.add(jPanel);
        jPanel.setLayout(null);

        JMenuBar bar = new JMenuBar();
        JMenu jMenu1 = new JMenu("File");
        JMenuItem jMenu1_item1 = new JMenuItem("Import");
        JMenuItem jMenu1_item2 = new JMenuItem("Export");
        JMenuItem jMenu1_item3 = new JMenuItem("Save");

        jMenu1.add(jMenu1_item1);
        jMenu1.add(jMenu1_item2);
        jMenu1.add(jMenu1_item3);
        JMenu jMenu2 = new JMenu("Point");
        JMenuItem jMenu2_item1 = new JMenuItem("Delete");
        JMenuItem jMenu2_item2 = new JMenuItem("Show");
        jMenu2.add(jMenu2_item1);
        jMenu2.add(jMenu2_item2);
        JMenu jMenu3 = new JMenu("Repaint");
        JMenuItem jMenu3_item1 = new JMenuItem("Go");
        JMenuItem jMenu3_item2 = new JMenuItem("Kong");
        jMenu3.add(jMenu3_item1);
        jMenu3.add(jMenu3_item2);
        JMenu jMenu4 = new JMenu("Algorithm");
        JMenuItem jMenu4_item1 = new JMenuItem("Bezier");
        JMenuItem jMenu4_item2 = new JMenuItem("B_Spline");
        jMenu4.add(jMenu4_item1);
        jMenu4.add(jMenu4_item2);
        JMenu jMenu5 = new JMenu("Steps");

        for (int i = 0; i < 1000; i = i + 100) {
            jMenu5.add(new JMenuItem((i)+""));
        }


        bar.add(jMenu1);
        bar.add(jMenu2);
        bar.add(jMenu3);
        bar.add(jMenu4);
        bar.add(jMenu5);


        frame.setJMenuBar(bar);


        setMenuListen(jMenu1,jMenu2,jMenu3,jMenu4,jMenu5);

        frame.setVisible(true);


        File file = new File("Point.obj");

        readObjFile(file);


        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("x == "+  e.getX() + "  y == "+e.getY());

                points[n][0] = e.getX();
                points[n++][1] = e.getY();
                jPanel.doMyPaint(points,step,n,jPanel.getGraphics(),power,type,true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {


            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        jPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getX();
                int y = e.getY();
                for (int i = 0; i < points.length; i++) {
                    if (x > points[i][0]-power && x < points[i][0]+power && y < points[i][1]+power && y > points[i][1]-power){
                        points[i][0] = e.getX();
                        points[i][1] = e.getY();
                        jPanel.getGraphics().clearRect(0,0,jPanel.getWidth(),jPanel.getHeight());
                        jPanel.doMyPaint(points,step,n,jPanel.getGraphics(),power,type,true);
                    }
                }

            }


        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                int a = JOptionPane.showConfirmDialog(jPanel, "是否保存？");

                if (a == 0){
                    JFileChooser jFileChooser = new JFileChooser();


                    jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                    int i = jFileChooser.showOpenDialog(jPanel);

                    if (i == JFileChooser.APPROVE_OPTION){

                        String path = jFileChooser.getSelectedFile().getAbsolutePath();

                        String fileName = JOptionPane.showInputDialog("input file name:");
                        if (fileName != null){
                            saveObjFile(path+File.separator+fileName+".obj");
                            JOptionPane.showMessageDialog(null,"export ok");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }else{
                            JOptionPane.showMessageDialog(null,"export fail");
                        }

                    }
                }else if(a == 2){
                    System.out.println(a);
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }else{
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }


            }
        });


    }

    private static void saveObjFile(String objFileName) {

        File file = new File(objFileName);

        if (file.exists()){
            file.delete();
        }

        ObjectOutputStream outputStream = null;
        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            points[points.length-1][1] =n;
            outputStream.writeObject(points);

        }catch (Exception e1){
            System.out.println("序列化失败");
            System.out.println(e1.fillInStackTrace());
        }finally {
            try {
                outputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void readObjFile(File file) {

        ObjectInputStream inputStream = null;

        if (file.exists()){
            try{
                inputStream = new ObjectInputStream(new FileInputStream(file));
                Object o = inputStream.readObject();
                points = (int[][]) o;
                n = points[points.length-1][1];
                if (n>points.length){
                    n = 0;
                }

                System.out.println(n);

                jPanel.getGraphics().clearRect(0,0,jPanel.getWidth(),jPanel.getHeight());
                jPanel.doMyPaint(points,step,n,jPanel.getGraphics(),power,type,true);

                openFilePath = file.getAbsolutePath();

                frame.setTitle(file.getName());


            }catch (Exception e){
                System.out.println(e.fillInStackTrace());
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            System.out.println("没有序列化");
        }



    }

    private static void setMenuListen(JMenu jMenu1, JMenu jMenu2, JMenu jMenu3, JMenu jMenu4, JMenu jMenu5) {


        //delete point
        jMenu2.getItem(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel.getGraphics().clearRect(0,0,jPanel.getWidth(),jPanel.getHeight());
                jPanel.doMyPaint(points,step,n,jPanel.getGraphics(),power,type,false);
            }
        });
        //show point
        jMenu2.getItem(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel.getGraphics().clearRect(0,0,jPanel.getWidth(),jPanel.getHeight());
                jPanel.doMyPaint(points,step,n,jPanel.getGraphics(),power,type,true);
            }
        });
        //save
        jMenu1.getItem(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveObjFile(openFilePath);
                JOptionPane.showMessageDialog(jPanel,"save ok");
            }
        });
        //repaint
        jMenu3.getItem(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n=0;
                jPanel.getGraphics().clearRect(0,0,jPanel.getWidth(),jPanel.getHeight());
            }
        });

        //import
        jMenu1.getItem(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jFileChooser = new JFileChooser();

                jFileChooser.setFileFilter(new FileNameExtensionFilter("obj & OBJ","obj","OBJ"));

                int i = jFileChooser.showOpenDialog(jPanel);
                if (i == JFileChooser.APPROVE_OPTION){
                    String path = jFileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println(path);
                    readObjFile(new File(path));
                }
            }
        });

        //export
        jMenu1.getItem(1).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {



                JFileChooser jFileChooser = new JFileChooser();


                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int i = jFileChooser.showOpenDialog(jPanel);

                if (i == JFileChooser.APPROVE_OPTION){

                    String path = jFileChooser.getSelectedFile().getAbsolutePath();

                    String fileName = JOptionPane.showInputDialog("input file name:");
                    if (fileName != null){
                        saveObjFile(path+File.separator+fileName+".obj");
                        JOptionPane.showMessageDialog(null,"export ok");
                    }

                }
            }
        });



        //Bezier
        jMenu4.getItem(0).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                type = 1;
                jPanel.getGraphics().clearRect(0,0,jPanel.getWidth(),jPanel.getHeight());
                jPanel.doMyPaint(points,step,n,jPanel.getGraphics(),power,type,true);

            }
        });

        //B_Spline
        jMenu4.getItem(1).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                type = 0;
                jPanel.getGraphics().clearRect(0,0,jPanel.getWidth(),jPanel.getHeight());
                jPanel.doMyPaint(points,step,n,jPanel.getGraphics(),power,type,true);

            }
        });


        //steps
        for (int i = 0; i < 10; i++) {
            jMenu5.getItem(i).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    JMenuItem source = (JMenuItem) e.getSource();
                    String text = source.getText();

                   try {
                        step = Integer.valueOf(text);
                   }catch (Exception e1){
                       System.out.println(text+ " to int fail");
                   }


                    jPanel.getGraphics().clearRect(0, 0, jPanel.getWidth(), jPanel.getHeight());
                    jPanel.doMyPaint(points, step, n, jPanel.getGraphics(), power, type,true);

                }
            });
        }

    }


}
