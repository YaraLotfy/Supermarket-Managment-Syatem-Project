/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket.management.system.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author dell
 */
public class Categories extends JFrame implements ActionListener
{
    HashMap<String, String> map=new HashMap<String, String>();

    private String CatID;
    private String CategoryName;
    private JButton RemoveButton;
    private JButton AddButton;
    private JButton Clear;
    private JLabel lblID;
    private JTextField jtfID;
    private JLabel lblName;
    private JTextField jtfName;
    private JLabel ListHeader;
    private JLabel FirstRowList;
    private JLabel SecondRowList;
    private JLabel clear;
    private JTable CategoryList;
    private JButton ShowTable;
    private String data[][];
    private JButton back;
    JTable jt;
    JPanel panel;
    JScrollPane sp;
    String column[]={"CategoryID","CategoryName"};
    JFrame frame;
    DefaultTableModel model;
    int nrow;
    int x ,r,c;
    
     public Categories  ()
    {
        
        CatID = " ";
        CategoryName = " ";
        
        frame = new JFrame();
        panel = new JPanel();
        
        
        frame.setSize(615,700);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setLayout(null);

        AddButton = new JButton("Add");
        AddButton.setBounds(70, 235, 100, 30);
        panel.add(AddButton);
        
        
        Clear = new JButton("Clear");
        Clear.setBounds(330, 235, 100, 30);
        panel.add(Clear);
         
        
        RemoveButton = new JButton("Remove");
        RemoveButton.setBounds(200, 235, 100, 30);
        panel.add(RemoveButton);
        
        lblID = new JLabel("CategoryID:");
        lblID.setBounds(50, 130, 100, 30);
        panel.add(lblID);
        
        jtfID = new JTextField();
        jtfID.setBounds(150, 130, 150, 30);
        panel.add(jtfID);
        
        lblName = new JLabel("CategoryName:");
        lblName.setBounds(50, 180, 100, 30);
        panel.add(lblName);
        
        jtfName = new JTextField();
        jtfName.setBounds(150, 180, 150, 30);
        panel.add(jtfName);
        
        back = new JButton("<- Back to main page");
        back.setBounds(200, 280, 160, 30);
        back.addActionListener(this);
        panel.add(back);
        
        CategoryList = new JTable();
        
        model = new DefaultTableModel(100, column.length);
        model.setColumnIdentifiers(column);
        data= new String[100][2];
        jt = new JTable(model);
        sp = new JScrollPane(jt);
        sp.setBounds(0,450,600,300);
        panel.add(sp);
       
        
        AddButton.addActionListener(this);
        RemoveButton.addActionListener(this);
        
        Clear.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
     int i=0;
     @Override
    public void actionPerformed(ActionEvent e)
    {
       if (e.getSource()== AddButton)
        {
            panel.remove(sp);
            String id = jtfID.getText();
            String name = jtfName.getText();
            map.put(id,name);
            System.out.println(map);
            ObjectOutputStream write;
            try {
                write = new ObjectOutputStream(new FileOutputStream("categories.bin"));
                write.writeObject(map);
                write.close();
                JOptionPane.showMessageDialog(this, "Updated Successfully");
            } catch (FileNotFoundException ex) {
                System.out.println("error");
            } catch (IOException ex) {
                System.out.println(ex);
            }
            
            data[i][0]=id;
            data[i][1]=name;
            model.setDataVector(data, column); 
            jt = new JTable(model);
            jt.remove(0);
            sp = new JScrollPane(jt);
            sp.setBounds(0,450,600,300);
            panel.add(sp);
            i++;
            JOptionPane.showMessageDialog(null, "Category Added Successfully");
        }
       
       if (e.getSource()== RemoveButton)
        {    
            model.removeRow(jt.getSelectedRow());
            System.out.println(jt.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Category Removed Successfully");
        }
       
       if (e.getSource()== Clear)
        {
            jtfID.setText("");
            jtfName.setText("");
        }
       if (e.getSource() == back) {
            MainPage m = new MainPage();
            frame.dispose();
        }
    }
    
      public Categories(String CatID, String CategoryName)
    {
        this.CatID = CatID;
        this.CategoryName = CategoryName;
    }

    public String getCatID() 
    {
        return CatID;
    }

    public String getCategoryName() 
    {
        return CategoryName;
    }  
    public void Display()
    {
        System.out.print(map);
    }
    public String[][] getdata(){
        return data;
    }
}

