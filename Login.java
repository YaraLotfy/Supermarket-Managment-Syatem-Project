/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket.management.system.project;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Sama
 */
public class Login extends JFrame implements ActionListener {

    private static JLabel login;
    private static JLabel user;
    private static JLabel userlabel;
    private static JTextField usertext;
    private static JLabel passwordlabel;
    private static JPasswordField passwordtext;
    private static JLabel success;
    private static JButton button;
    private static String users = "ali";
    private static String passwords = "1234";
    private static JButton resetButton;
    private static JCheckBox showPassword;
    private static JComboBox User;
    int test=0;
    JFrame frame;
    HashMap<String, String> Admin = new HashMap<String, String>();
    HashMap<String, String> seller = new HashMap<String, String>();
    HashMap<String, String> map = new HashMap<String, String>();
    public Login() {
        try {
            JPanel panel = new JPanel();
            frame = new JFrame();
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);
            frame.add(panel);
            panel.setLayout(null);
            login = new JLabel("Login");
            login.setBounds(150, 30, 200, 30);
            panel.add(login);
            user = new JLabel("User");
            user.setBounds(50, 90, 100, 30);
            panel.add(user);
            User = new JComboBox(new String[]{"admin", "seller"});
            User.setBounds(150, 90, 200, 30);
            User.addActionListener(this);
            panel.add(User);
            userlabel = new JLabel("User Name");
            userlabel.setBounds(50, 130, 100, 30);
            panel.add(userlabel);
            usertext = new JTextField(20);
            usertext.setBounds(150, 130, 150, 30);
            panel.add(usertext);
            passwordlabel = new JLabel("Password");
            passwordlabel.setBounds(50, 180, 100, 30);
            panel.add(passwordlabel);
            passwordtext = new JPasswordField(20);
            passwordtext.setBounds(150, 180, 150, 30);
            panel.add(passwordtext);
            button = new JButton("Login");
            button.setBounds(50, 270, 100, 30);
            button.addActionListener(this);
            panel.add(button);
            resetButton = new JButton("RESET");
            resetButton.setBounds(200, 270, 100, 30);
            resetButton.addActionListener(this);
            panel.add(resetButton);
            showPassword = new JCheckBox("Show Password");
            showPassword.setBounds(150, 230, 150, 30);
            showPassword.addActionListener(this);
            panel.add(showPassword);
            success = new JLabel("");
            success.setBounds(10, 110, 300, 25);
            panel.add(success);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (StackOverflowError e) {
            System.out.println("error");
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == User) {
            if (User.getSelectedItem() == "admin") {
                ObjectInputStream readfile = null;
                
                try {
                    readfile = new ObjectInputStream(new FileInputStream(new File("binary.bin")));
                    Admin = (HashMap<String, String>) readfile.readObject();
                    System.out.println(Admin);
                    readfile.close();
                    JOptionPane.showMessageDialog(this, "Successful");
                    //map.putAll(Admin);
                    map=Admin;
                     //System.out.println(map);
                } catch (EOFException eof) {
                    System.out.println("end of file");
                } catch (IOException ex) {
                    System.out.println("input out exception");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch(Exception exc){
                    System.out.println(exc);
                }
                
            } else if (User.getSelectedItem() == "seller") {
                 ObjectInputStream readfile =null;
                
                try {
                    readfile = new ObjectInputStream(new FileInputStream(new File("seller.bin")));
                    seller = (HashMap<String, String>) readfile.readObject();
                    System.out.println(seller);
                    readfile.close();
                    JOptionPane.showMessageDialog(this, "Successful");
                    //map.putAll(seller);
                    map=seller;
                     System.out.println(map);
                } catch (EOFException eof) {
                    System.out.println("end of file");
                } catch (IOException ex) {
                    System.out.println("input out exception");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch(Exception exc){
                    System.out.println(exc);
                }
            }
        }
        if (e.getSource() == button) {
            String user = usertext.getText();
            String password = passwordtext.getText();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry me2 = (Map.Entry) iterator.next();
                System.out.println("Key: " + me2.getKey() + " & Value: " + me2.getValue());
                String users = (String) me2.getKey();
                String passwords = (String) me2.getValue();
                if (user.equals(users) && password.equals(passwords)) {
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    MainPage p = new MainPage();
                    test=1;
                    frame.dispose();
                } 
            }
            if(test!=1){
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }
        if (e.getSource() == resetButton) {
            usertext.setText("");
            passwordtext.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordtext.setEchoChar((char) 0);
            }
        }
    }
}
