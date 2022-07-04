/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket.management.system.project;

/**
 *
 * @author Sama
 */
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.Math.abs;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Selling extends JFrame implements ActionListener {

    HashMap<String, String> mapc = new HashMap<String, String>();
    HashMap<String, String> maps = new HashMap<String, String>();
    HashMap<String, String> mapp = new HashMap<String, String>();

    private String Name;
    private String BillID;
    private String Category;
    private String Quantity;
    private String Pirce;
    private String Data[][];
    private JComboBox CategoriesComboBox, SellerComboBox, ProductsComboBox;
    private JLabel LabelName, LabelQuantity, LabelCatList, LabelSeller, LabelInsertedMoney;
    private JTextField textfieldPRODUCTS, textfield_Quantity, Receipt, TextInsertedMoney;
    private JButton Add, Edit, Delete, Clear, ViewCart, back, Print,Pay;
    private final ImageIcon redBag;
    private JSpinner spinner;
    private int TotalPrice;
    TextArea area;
    JTable jtable;
    JPanel panel;
    DefaultTableModel model;
    JScrollPane sp;
    String column[] = {"PRODUCTS","CATEGORY","QUANTITY","PRICE/ ITEM","TotalPrice"};
    String cmap[], smap[], pmap[];
    int size;
    int CHECK_OUT = 0;
    JFrame Frame;

    public Selling() throws IOException {
        Frame = new JFrame("SELLING");
        panel = new JPanel();

        Frame.setSize(800, 800);
        Frame.setLocationRelativeTo(null);
        Frame.add(panel);
        panel.setLayout(null);

        area=new TextArea("\t\t***BILL***");
        area.append("\nPRODUCTS\t QUANTITY \t TOTAL_PRICE "+"\n===========================================================");
        
        area.setBounds(10, 250, 335, 420);
        panel.add(area);
        
        LabelName = new JLabel(" PRODUCTS ");
        LabelName.setBounds(50, 100, 200, 30);

        LabelCatList = new JLabel(" Category List ");
        LabelCatList.setBounds(390, 130, 140, 20);
        LabelCatList.setFont(new Font("Verdana", Font.BOLD, 15));

        LabelQuantity = new JLabel(" QUANTITY ");
        LabelQuantity.setBounds(50, 150, 100, 30);
        
        LabelInsertedMoney = new JLabel(" INSERTED_MONEY: ");
        LabelInsertedMoney.setBounds(20, 685, 120, 30);
        
        LabelSeller = new JLabel(" SELLER ");
        LabelSeller.setBounds(500, 535, 140, 20);
        LabelSeller.setFont(new Font("Verdana", Font.BOLD, 15));

        panel.add(LabelName);
        panel.add(LabelCatList);
        panel.add(LabelQuantity);
        panel.add(LabelSeller);
        panel.add(LabelInsertedMoney);

        ObjectInputStream reading = null;

        try {
            reading = new ObjectInputStream(new FileInputStream(new File("products.bin")));
            mapp = (HashMap<String, String>) reading.readObject();
            reading.close();

        } catch (EOFException eof) {
            System.out.println("end of file");
        } catch (IOException ex) {
            System.out.println("input out exception" + ex);
        } catch (Exception exc) {
            System.out.println(exc);
        }

        pmap = new String[100];
        Set entry = mapp.entrySet();
        Iterator entryIterator = entry.iterator();
        int p = 0;
        while (entryIterator.hasNext()) {

            Map.Entry mapping = (Map.Entry) entryIterator.next();
            String productname = (String) mapping.getKey();
            String price = (String) mapping.getValue();
            pmap[p] = productname;
            p++;
        }

        ProductsComboBox = new JComboBox(pmap);
        ProductsComboBox.setBounds(140, 100, 150, 30);
        panel.add(ProductsComboBox);

        textfield_Quantity = new JTextField();
        textfield_Quantity.setBounds(140, 150, 150, 30);
        panel.add(textfield_Quantity);
        
        TextInsertedMoney = new JTextField();
        TextInsertedMoney.setBounds(140, 685, 150, 30);
        panel.add(TextInsertedMoney);
        

        ObjectInputStream readingCategories = null;

        try {
            readingCategories = new ObjectInputStream(new FileInputStream(new File("categories.bin")));
            mapc = (HashMap<String, String>) readingCategories.readObject();
            readingCategories.close();

        } catch (EOFException eof) {
            System.out.println("end of file");
        } catch (IOException ex) {
            System.out.println("input out exception" + ex);
        } catch (Exception exc) {
            System.out.println(exc);
        }

        cmap = new String[100];
        Set entries = mapc.entrySet();
        Iterator IteratorCategories = entries.iterator();
        int c = 0;
        while (IteratorCategories.hasNext()) {
            Map.Entry mapping = (Map.Entry) IteratorCategories.next();
            String idOFcategories = (String) mapping.getKey();
            String nameOFcategories = (String) mapping.getValue();
            cmap[c] = nameOFcategories;
            c++;
        }
        CategoriesComboBox = new JComboBox(cmap);
        CategoriesComboBox.setBounds(380, 160, 150, 30);
        panel.add(CategoriesComboBox);

        ObjectInputStream reads = null;

        try {
            reads = new ObjectInputStream(new FileInputStream(new File("seller.bin")));
            maps = (HashMap<String, String>) reads.readObject();
            reads.close();

        } catch (EOFException eof) {
            System.out.println("end of file");
        } catch (IOException ex) {
            System.out.println("input out exception" + ex);
        } catch (Exception exc) {
            System.out.println(exc);
        }
        smap = new String[100];
        Set entrie = maps.entrySet();
        Iterator entrieIterator = entrie.iterator();
        int o = 0;
        while (entrieIterator.hasNext()) {

            Map.Entry mapping = (Map.Entry) entrieIterator.next();
            String name = (String) mapping.getKey();
            String password = (String) mapping.getValue();
            smap[o] = name;
            o++;
        }

        SellerComboBox = new JComboBox(smap);
        SellerComboBox.setBounds(470, 560, 160, 30);
        panel.add(SellerComboBox);

        Add = new JButton(" ADD TO CART ");
        Add.setBounds(80, 200, 125, 30);
        panel.add(Add);
        
        Print = new JButton(" Print Bill ");
        Print.setBounds(460, 610, 180, 30);
        panel.add(Print);
        
        back = new JButton("<- Back to main page");
        back.setBounds(460, 650, 180, 30);
        back.setFont(new Font("Arial", Font.BOLD, 13));
        back.addActionListener(this);
        panel.add(back);
        
        Pay = new JButton("PAY");
        Pay.setBounds(300, 685, 60, 30);
        panel.add(Pay);
        
        Delete = new JButton(" REMOVE ITEM ");
        Delete.setBounds(560, 160, 125, 30);
        panel.add(Delete);

        redBag = new ImageIcon(getClass().getResource("cart.jpg"));
        ViewCart = new JButton(redBag);
        ViewCart.setToolTipText("Cart");
        ViewCart.setBounds(690, 30, 50, 50);
        panel.add(ViewCart);

        Clear = new JButton(" CLEAR ");
        Clear.setBounds(220, 200, 100, 30);
        panel.add(Clear);

        model = new DefaultTableModel(100, column.length);
        model.setColumnIdentifiers(column);
        Data = new String[100][column.length];

        jtable = new JTable(model);
        jtable.setRowHeight(25);
        sp = new JScrollPane(jtable);
        sp.setBounds(350, 200, 430, 300);
        panel.add(sp);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        Add.addActionListener(this);
        Delete.addActionListener(this);
        Print.addActionListener(this);
        Pay.addActionListener(this);
        
        ViewCart.addActionListener(this);
        Clear.addActionListener(this);

        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    int i = 0;
    char CounterID = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Add) {
            panel.remove(sp);
            String Quantity = textfield_Quantity.getText();
            String Products = (String) ProductsComboBox.getSelectedItem();
            String Category = (String) CategoriesComboBox.getSelectedItem();
            Set entr = mapp.entrySet();
            Iterator entrIterator = entr.iterator();
            int p = 0;
            while (entrIterator.hasNext()) 
            {
                Map.Entry mapping = (Map.Entry) entrIterator.next();
                String productname = (String) mapping.getKey();
                String price = (String) mapping.getValue();
                if (Products.equals(productname)) {
                    Data[i][3] = price;
                }
            }
            Data[i][0] = Products;
            Data[i][2] = Quantity;
            Data[i][1] = Category;
            int pr=Integer.parseInt(Data[i][3]);
            int qu=Integer.parseInt(Data[i][2]);
            TotalPrice = (pr* qu);
            String tp=String.valueOf(TotalPrice);
            Data[i][4]=tp;
            model.setDataVector(Data, column);
            jtable = new JTable(model);
            sp = new JScrollPane(jtable);
            sp.setBounds(350, 200, 430, 300);
            panel.add(sp);
            i++;
            JOptionPane.showMessageDialog(null, "Category Added Successfully");
        }

        if (e.getSource() == Clear) {
            textfield_Quantity.setText("");
        }
        
        if (e.getSource() == Delete) {
            model.removeRow(jtable.getSelectedRow());
            System.out.println(jtable.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Category Removed Successfully");
        }
        
        if (e.getSource() == Print) {
        String Products = (String) ProductsComboBox.getSelectedItem();
        int TOTAL_PRICE = 0;
        for(int tprice =0; tprice<i; tprice++){
            TOTAL_PRICE = Integer.parseInt ((String) jtable.getValueAt(tprice, 4));
        }

        
        area.append("\n"+ Products+"\t\t "
                +textfield_Quantity.getText()+ "\t\t" +TOTAL_PRICE);
       
        }
            
        if (e.getSource() == ViewCart) 
        { 
             for (int tprice = 0; tprice < i; tprice++) {
                CHECK_OUT += Integer.parseInt((String) jtable.getValueAt(tprice, 4));
            }
            area.append("\n\n\n\n\n\n\n\n\n================================================="
                    + "\nCHECK_OUT" + CHECK_OUT);
            JOptionPane.showMessageDialog(null, "Please Pay "+CHECK_OUT);
        }
        
        if (e.getSource() == Pay)
        {
            double CHANGE;
            int InsertedMoney = Integer.parseInt(TextInsertedMoney.getText());
            CHANGE = abs(CHECK_OUT - InsertedMoney);
            area.append("\nINSERTED_MONEY\t" + InsertedMoney + "\nCHANGE\t" + CHANGE + "\nSELLER : " + SellerComboBox.getSelectedItem());
        }
        if (e.getSource() == back) {
            MainPage m = new MainPage();
            Frame.dispose();
        }

    }
}