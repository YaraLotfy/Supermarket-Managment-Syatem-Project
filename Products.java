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
import javax.swing.JComboBox;
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
 * @author Sama
 */
public class Products extends JFrame implements ActionListener{
     
    private JLabel ID;
    private JLabel Category;
    private JLabel Quantity; 
    private JLabel Name;
    private JLabel Price;
    private JTextField IDT;
    private JTextField QuantityT;
    private JTextField NameT;
    private JTextField PriceT;
    private JComboBox CategoryB;
    private JButton Add;
    private JButton Delete;
    private JButton Clear;
    private JFrame frame;
    private JLabel p;
    private JButton back;
    private DefaultTableModel model;
    private String data[][];
    JTable jt;
    JPanel panel;
    JScrollPane sp;
    String column[]={"ID","Name","Quantity","Price","Category"};
    String cmap[];
    int size;
    HashMap<String, String> map=new HashMap<String, String>();
    HashMap<String, String> mapc=new HashMap<String, String>();
    public Products(){
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(615,700);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setLayout(null);
        p = new JLabel("PRODUCTS");
        p.setBounds(250, 30, 100, 30);
        panel.add(p);
        ID = new JLabel("ID");
        ID.setBounds(50, 130, 100, 30);
        panel.add(ID);
        IDT = new JTextField ();
        IDT.setBounds(110, 130, 150, 30);
        panel.add(IDT);
        Quantity = new JLabel("Quantity");
        Quantity.setBounds(330, 130, 100, 30);
        panel.add(Quantity);
        QuantityT = new JTextField();
        QuantityT.setBounds(390, 130, 150, 30);
        panel.add(QuantityT);
        Name = new JLabel("Name");
        Name.setBounds(50, 180, 100, 30);
        panel.add(Name);
        NameT = new JTextField();
        NameT.setBounds(110, 180, 150, 30);
        panel.add(NameT);
        Price = new JLabel("Price");
        Price.setBounds(330, 180, 100, 30);
        panel.add(Price);
        PriceT = new JTextField();
        PriceT.setBounds(390, 180, 150, 30);
        panel.add(PriceT);
        Category = new JLabel("Category");
        Category.setBounds(50, 230, 100, 30);
        panel.add(Category);
        ObjectInputStream read = null;

            try {
                read = new ObjectInputStream(new FileInputStream(new File("categories.bin")));
                mapc = (HashMap<String, String>) read.readObject();
                read.close();

            } catch (EOFException eof) {
                System.out.println("end of file");
            } catch (IOException ex) {
                System.out.println("input out exception"+ex);
            }  catch (Exception exc) {
                System.out.println(exc);
            }
        cmap =new String[100];
        Set entries = mapc.entrySet();
        Iterator entriesIterator = entries.iterator();
        int m=0;
        while (entriesIterator.hasNext()) {
            
            Map.Entry mapping = (Map.Entry) entriesIterator.next();
            String idk = (String) mapping.getKey();
            String n = (String) mapping.getValue();
            cmap[m]=n;
            m++;
        }
        CategoryB = new JComboBox(cmap);
        CategoryB.setBounds(110, 230, 150, 30);
        panel.add(CategoryB);
        Add = new JButton("Add");
        Add.setBounds(120, 310, 100, 30);
        Add.addActionListener(this);
        panel.add(Add);
        Clear = new JButton("Clear");
        Clear.setBounds(250, 310, 100, 30);
        Clear.addActionListener(this);
        panel.add(Clear);
        Delete = new JButton("Delete");
        Delete.setBounds(380, 310, 100, 30);
        Delete.addActionListener(this);
        panel.add(Delete);
        back = new JButton("<- Back to main page");
        back.setBounds(215, 370, 160, 30);
        back.addActionListener(this);
        panel.add(back);
        
        model = new DefaultTableModel(100, column.length);
        model.setColumnIdentifiers(column);
        data= new String[100][column.length];
        
        jt = new JTable(model);
        sp = new JScrollPane(jt);
        sp.setBounds(0,450,600,300);
        panel.add(sp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    int i=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Add) {
            panel.remove(sp);
            String id = IDT.getText();
            String name = NameT.getText();
            String price = PriceT.getText();
            String quantity = QuantityT.getText();
            String categ=(String) CategoryB.getSelectedItem();
            map.put(name,price);
           ObjectOutputStream write;
            try {
                write = new ObjectOutputStream(new FileOutputStream("products.bin"));
                write.writeObject(map);
                write.close();
            } catch (FileNotFoundException ex) {
                System.out.println("error");
            } catch (IOException ex) {
                System.out.println(ex);
            }
             data[i][0]=id;
             data[i][1]=name;
             data[i][2]=quantity;
             data[i][3]=price;
             data[i][4]=categ;
             
            model.setDataVector(data, column); 
            jt = new JTable(model);
            sp = new JScrollPane(jt);
            sp.setBounds(0,450,600,300);
            panel.add(sp);
            i++;
            JOptionPane.showMessageDialog(null, "Category Added Successfully");
        }
        if (e.getSource() == Clear) {
            IDT.setText("");
            NameT.setText("");
            PriceT.setText("");
            QuantityT.setText("");
        }
        if (e.getSource() == Delete) {
            model.removeRow(jt.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Product Removed Successfully");
        }
        if (e.getSource() == back) {
            MainPage m = new MainPage();
            frame.dispose();
        }
    }
}
