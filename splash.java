package supermarket.management.system.project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sama
 */

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.*;

public class splash extends JFrame {

    private final ImageIcon image1;
    private final JLabel lm;
    JPanel panel =new JPanel();
    static JProgressBar b; 
    
    public splash() throws InterruptedException {
        JFrame f = new JFrame("Splash");
        f.setSize(325, 350);
        f.setLocationRelativeTo(null);
        f.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
         b = new JProgressBar(); 
        b.setBounds(70, 250,160,30);
        b.setValue(0); 
        b.setStringPainted(true); 
        panel.add(b); 
        image1 = new ImageIcon(getClass().getResource("image2.jpg"));
        lm = new JLabel(image1);
        lm.setBounds(0,0, 300,300);
        panel.add(lm);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JLabel count = new JLabel();
        count.setBounds(130, 90, 300, 200);
        panel.add(count);
        for (int i = 0; i <= 100; i++) {
            b.setValue(i + 10);
            Thread.sleep(1000);
            i += 10;
        }
        Login lo=new Login();
        f.dispose();
    }
}