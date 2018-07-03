import java.lang.Math;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

class convexLens extends JPanel implements MouseMotionListener{

    static int pos=500;
    public convexLens(){
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

        object = new Line2D.Double(pos,height/2-obj_height,pos,height/2);
        g2d.draw(object);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(new Color(41,128,185));
        g2d.fillOval(width/2-7,height/2-obj_height,20,2*obj_height);


        //f,2f making
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        g2d.drawLine(width/2-f,height/2-5,width/2-f,height/2+5);
        g2d.drawLine(width/2-(2*f),height/2-5,width/2-(2*f),height/2+5);
        g2d.drawLine(width/2+f,height/2-5,width/2+f,height/2+5);
        g2d.drawLine(width/2+(2*f),height/2-5,width/2+(2*f),height/2+5);

        if(pos<width/2){
            //Ray making
            g2d.setColor(new Color(33,47,61));
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(pos,height/2-obj_height,width/2,height/2-obj_height);
            int v1 = height/2+(obj_height * (width/2-f))/f;
            g2d.drawLine(width/2,height/2-obj_height,width,v1);
            int v2 = height/2+((obj_height * (width/2))/(width/2-pos));
            g2d.drawLine(pos,height/2-obj_height,width,v2);

            //imaginge making
            double v3 = (1.0/(1.0/f-1.0/(width/2-pos)));
            double v4 = (obj_height*v3)/(width/2-pos);
            g2d.draw(new Line2D.Double(width/2+v3,height/2,width/2+v3,height/2+v4));

            //virtuval ray
            float[] dashingPattern1 = {4f, 4f};
            Stroke stroke1 = new BasicStroke(2f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
            g2d.setStroke(stroke1);
            g2d.draw(new Line2D.Double(pos,height/2-obj_height,width/2+v3,height/2+v4));
            g2d.draw(new Line2D.Double(width/2,height/2-obj_height,width/2+v3,height/2+v4));
            g.setFont(new Font("TimesRoman", Font.PLAIN,30));

            g2d.setColor(new Color(203,67,53));
            g2d.drawString("F: ",80,height/2+250);
            g2d.drawString(" "+f, 100,height/2+250);

            g2d.drawString("object height: ",200,height/2+250);
            g2d.drawString(" "+obj_height, 380,height/2+250);

            g2d.drawString("Imagine distance : ",80,height/2+300);
            g2d.drawString(" "+ v3, 300,height/2+300);

            g2d.drawString("Imagine height : ",80,height/2+350);
            g2d.drawString(" "+v4, 300,height/2+350);
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
