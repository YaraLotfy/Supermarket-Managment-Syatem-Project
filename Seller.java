/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket.management.system.project;


/**
 *
 * @author DELL
 */
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Seller extends JFrame implements ActionListener {

   
    private String Name;
    private String Id;
    private String Password;
    private String Gender;
    private String data[][];
    private JLabel LabelName, LabelID, LabelPassword, LabelGender;
    private JTextField t1, t2, t3;
    private JButton Add, Delete, Clear;
    private JComboBox ComboBoxGender;
    DefaultTableModel model;
    JTable JT;
    JPanel panel;
    JScrollPane sp;
    String column[]={"ID","Name","Password","Gender"};
    HashMap<String, String> map=new HashMap<String, String>();
    JFrame f;
    private JButton back;
    
    public Seller() {
        
        f = new JFrame("SELLER");
        panel = new JPanel();

        f.setSize(815,800);
        f.setLocationRelativeTo(null);
        f.add(panel);
        panel.setLayout(null);
        
        LabelName = new JLabel(" Seller Name: ");
        LabelName.setBounds(50, 100, 200, 30);
        
        LabelID = new JLabel(" Seller ID: ");
        LabelID.setBounds(400, 100, 100, 30);
        
        LabelPassword = new JLabel(" Seller Password: ");
        LabelPassword.setBounds(50, 220, 200, 30);
        
        LabelGender = new JLabel(" Gender ");
        LabelGender.setBounds(400, 220, 250, 30);
        
        panel.add(LabelGender);
        panel.add(LabelName);
        panel.add(LabelID);
        panel.add(LabelPassword);
        
        t1 = new JTextField(" ");
        t1.setBounds(140, 100, 150, 30);
        panel.add(t1);
        
        t2 = new JTextField(" ");
        t2.setBounds(480, 100, 150, 30);
        panel.add(t2);
        
        t3 = new JTextField(" ");
        t3.setBounds(170, 220, 150, 30);
        panel.add(t3);
        
        ComboBoxGender = new JComboBox(new String[]{"Male", "Female"});
        ComboBoxGender.setBounds(490, 225, 140, 20);
        panel.add(ComboBoxGender);

        Add = new JButton(" ADD ");
        Add.setToolTipText("ADD data in JTable");
        Add.setBounds(200, 300, 100, 30);
        panel.add(Add);
        
        Delete = new JButton(" DELETE ");
        Delete.setBounds(340, 300, 100, 30);
        panel.add(Delete);
        
        Clear = new JButton(" CLEAR ");
        Clear.setBounds(480, 300, 100, 30);
        panel.add(Clear);
        
        back = new JButton("<- Back to main page");
        back.setBounds(305, 370, 160, 30);
        back.addActionListener(this);
        panel.add(back);
        
        model = new DefaultTableModel(100, column.length);
        model.setColumnIdentifiers(column);
        data= new String[100][column.length];

        
        JT = new JTable(model);
        sp = new JScrollPane(JT);
        sp.setBounds(0,450,800,300);
        panel.add(sp);
       
       
        
        Add.addActionListener(this);
        Delete.addActionListener(this);
        Clear.addActionListener(this);
        
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
     int i=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== Add)
        {
            panel.remove(sp);
            String SellerNames= t1.getText();
            String SellerID = t2.getText();
            String SellerPassword = t3.getText();
            String SellerGender = ComboBoxGender.getSelectedItem().toString();
            
            map.put(SellerNames,SellerPassword);
           ObjectOutputStream write;
            try {
                write = new ObjectOutputStream(new FileOutputStream("seller.bin"));
                write.writeObject(map);
                write.close();
            } catch (FileNotFoundException ex) {
                System.out.println("error");
            } catch (IOException ex) {
                System.out.println(ex);
            }
            
            data[i][0]=SellerID;
            data[i][1]=SellerNames;
            data[i][2]=SellerPassword;
            data[i][3]=SellerGender;
            model.setDataVector(data, column); 
            JT = new JTable(model);
            sp = new JScrollPane(JT);
            sp.setBounds(0,450,800,300);
            panel.add(sp);
            i++;
            JOptionPane.showMessageDialog(null, " ADDED Successfully");
        }
        if (e.getSource() == Delete) {
            model.removeRow(JT.getSelectedRow());
            JOptionPane.showMessageDialog(this, " DELETE successfully");
        }
        
        if (e.getSource() == Clear)
        {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            ComboBoxGender.setSelectedItem("Male");
        }
        if (e.getSource() == back) {
            MainPage m = new MainPage();
            f.dispose();
        }
    }
}
