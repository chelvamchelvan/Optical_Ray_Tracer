import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.lang.Math;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

class concaveLens extends JPanel implements MouseMotionListener{
    static int w;
    static int pos=500;
    public concaveLens(){
        addMouseMotionListener(this);
        setBackground(Color.white);
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        int height=1000,width=1500;
        int obj_height=200,f=150;
        Line2D.Double object;

        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0,height/2,width,height/2);
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4));


        BufferedImage img = null;
        try {
            File file = new File("../Optical_Ray_Tracer/images/concave.JPG");
            img = ImageIO.read(file);
            w = img.getWidth();
        } catch (IOException e) {
            System.out.println("error");
        };

        g2d.drawImage(img,width/2-(w/2),height/2-obj_height-20,null);
        g2d.drawLine(width/2,height/2-5,width/2,height/2+5);

        object = new Line2D.Double(pos,height/2-obj_height,pos,height/2);
        g2d.draw(object);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(new Color(41,128,185));

        //f,2f making
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        g2d.drawLine(width/2-f,height/2-5,width/2-f,height/2+5);
        g2d.drawLine(width/2-(2*f),height/2-5,width/2-(2*f),height/2+5);
        g2d.drawLine(width/2+f,height/2-5,width/2+f,height/2+5);
        g2d.drawLine(width/2+(2*f),height/2-5,width/2+(2*f),height/2+5);

        if(pos<width/2){
            g2d.setColor(new Color(33,47,61));
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(pos,height/2-obj_height,width/2,height/2-obj_height);
            int v1 = (obj_height *(width/2-f))/f;
            g2d.drawLine(width/2,height/2-obj_height,0,v1+height/2);
            int v2 = height/2+((obj_height * (width/2))/(width/2-pos));
            g2d.drawLine(pos,height/2-obj_height,width,v2);

            double v3 = 1.0/((1.0/f)+(1.0/(width/2-pos)));
            double v4 = (v3 *  obj_height)/(width/2-pos);
            g2d.draw(new Line2D.Double(width/2-v3,height/2,width/2-v3,height/2-v4));

            g.setFont(new Font("TimesRoman", Font.PLAIN,30));

            g2d.setColor(new Color(203,67,53));
            g2d.drawString("F: ",width/2+200,height/2-450);
            g2d.drawString(" "+f, width/2+220,height/2-450);

            g2d.drawString("object height: ",width/2+300,height/2-450);
            g2d.drawString(" "+obj_height, width/2+500,height/2-450);

            g2d.drawString("Imagine distance : ",width/2+200,height/2-400);
            g2d.drawString(" "+ Math.round(v3), width/2+450,height/2-400);

            g2d.drawString("Imagine height : ",width/2+200,height/2-350);
            g2d.drawString(" "+Math.round(v4), width/2+450,height/2-350);


        }

        else{
            g.setFont(new Font("TimesRoman", Font.PLAIN,25));
            g2d.drawString("move only infront the lens", width/2+200,50);

        }
    }



    public void mouseMoved(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent a) {
        pos = a.getX();
        repaint();
    }
}
