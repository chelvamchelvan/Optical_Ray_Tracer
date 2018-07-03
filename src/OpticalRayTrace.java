import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class OpticalRayTrace {

    public static void main(String args[]){

        convexLens convex = new convexLens();
        concaveLens concave = new concaveLens();

        JFrame f = new JFrame();
        f.setSize(1500,1000);
        f.setTitle("Convex lens expirement");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);

        JTabbedPane jtp = new JTabbedPane();
        jtp.setBackground(new Color(255,255,255));
        f.add(jtp);

        jtp.add("convex",convex);
        jtp.add("concave",concave);
        f.setVisible(true);


    }
}
