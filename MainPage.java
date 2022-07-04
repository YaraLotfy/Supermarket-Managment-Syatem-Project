/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket.management.system.project;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Sama
 */
public class MainPage extends JFrame implements ActionListener {

    private static JComboBox opitions;
    private static JButton button;
    private ImageIcon image;
    private JLabel lm;
    JFrame frame;
    
    public MainPage() {
        
        JPanel panel = new JPanel();
        frame = new JFrame();
        frame.setSize(800, 650);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setLayout(null);
        opitions = new JComboBox(new String[]{"Update Admin", "Seller", "Categories", "Selling", "Products"});
        opitions.setBounds(180, 200, 200, 30);
        opitions.addActionListener(this);
        panel.add(opitions);
        button = new JButton("Enter");
        button.setBounds(230, 250, 100, 30);
        button.addActionListener(this);
        panel.add(button);
        image=new ImageIcon(getClass().getResource("image1.jpg"));
        lm=new JLabel(image);
        lm.setBounds(0, 0, 800, 650);
        panel.add(lm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (opitions.getSelectedItem() == "Update Admin") {
                UpdateAdmin u=new UpdateAdmin();
            } else if (opitions.getSelectedItem() == "Seller") {
                Seller s=new Seller();  
            } else if (opitions.getSelectedItem() == "Categories") {
                Categories c = new Categories();
            } else if (opitions.getSelectedItem() == "Selling") {
                try {
                    Selling se=new Selling();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            } else if (opitions.getSelectedItem() == "Products") {
                Products p=new Products();
            }
            frame.dispose();
        }
    }
}