/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket.management.system.project;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author reema
 */
public class UpdateAdmin extends JFrame implements ActionListener, Serializable {

    private static JLabel UserName;
    private static JTextField UserText;
    private static JLabel Password;
    private static JTextField PasswordText;
    private static JButton Update;
    private static JButton Clear;
    private final ImageIcon admin;
    private final JLabel ad;
    private JButton back;
    HashMap<String, String> Admin = new HashMap<String, String>();
    JFrame frame;
    
    public UpdateAdmin() {
        JPanel panel = new JPanel();
        frame = new JFrame();
        frame.setSize(800, 650);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setLayout(null);
        UserName = new JLabel("UserName");
        UserName.setBounds(250, 220, 100, 30);
        UserName.setFont(new Font("Cambria", Font.BOLD, 15));
        panel.add(UserName);
        UserText = new JTextField();
        UserText.setBounds(350, 230, 150, 30);
        panel.add(UserText);
        Password = new JLabel("Password");
        Password.setBounds(250, 280, 150, 30);
        Password.setFont(new Font("Cambria", Font.BOLD, 15));
        panel.add(Password);
        PasswordText = new JTextField();
        PasswordText.setBounds(350, 280, 150, 30);
        panel.add(PasswordText);
        Update = new JButton("Update");
        Update.setBounds(270, 370, 100, 30);
        Update.setFont(new Font("Cambria", Font.BOLD, 15));
        panel.add(Update);
        Update.addActionListener(this);
        Clear = new JButton("Clear");
        Clear.setBounds(400, 370, 100, 30);
        Clear.setFont(new Font("Cambria", Font.BOLD, 15));
        panel.add(Clear);
        Clear.addActionListener(this);
        back = new JButton("<- Back to main page");
        back.setBounds(300, 450, 160, 30);
        back.addActionListener(this);
        panel.add(back);
        admin = new ImageIcon(getClass().getResource("AdminImage.jpg"));
        ad = new JLabel(admin);
        ad.setBounds(0, 0, 800, 650);
        panel.add(ad);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void UpdateAdmin(String Password, String UserName) {
        Admin.put(Password, UserName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Update) {
            ObjectOutputStream writefile;
            String UserName = UserText.getText();
            String Password = PasswordText.getText();
            try {
                writefile = new ObjectOutputStream(new FileOutputStream("binary.bin"));
                Admin.put(UserName, Password);
                System.out.println(Admin);
                writefile.writeObject(Admin);
                writefile.close();
                JOptionPane.showMessageDialog(this, "Updated Successfully");
            } catch (FileNotFoundException ex) {
                System.out.println("error");
            }catch (IOException ex) {
                System.out.println("");
            }
        }
        
        if (e.getSource() == Clear) {
            UserText.setText("");
            PasswordText.setText("");
        }
        if (e.getSource() == back) {
            MainPage m = new MainPage();
            frame.dispose();
        }
    }
}
