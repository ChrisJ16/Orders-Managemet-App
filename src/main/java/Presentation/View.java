package Presentation;
import BussinesLogic.ProdusBLL;
import Model.Client;
import Model.Produs;
import Extra.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.awt.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class View extends JFrame{
    private Controller controller = new Controller(this);

    private JPanel mainPanel = new JPanel();
    private JPanel panel1 = new JPanel(); //client
    private JPanel panel2 = new JPanel(); //produs
    private JPanel panel3 = new JPanel(); //comenzi
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panel6 = new JPanel();
    private JPanel panel7 = new JPanel();
    private JPanel panel8 = new JPanel();
    private JPanel panel9 = new JPanel();
    private JPanel panel10 = new JPanel(); //istoric comenzi
    private JPanel panel11 = new JPanel();

    private JPanel panelAux1 = new JPanel();
    private JPanel panelAux2 = new JPanel();
    private JPanel panelAux3 = new JPanel();
    private JPanel panelAux4 = new JPanel();
    private JPanel panelAux5 = new JPanel();

    private JButton btn1 = new JButton("Creaza Client");
    private JButton btn2 = new JButton("Update Client");
    private JButton btn3 = new JButton("Sterge Client");

    private JButton btn4 = new JButton("Creaza Produs");
    private JButton btn5 = new JButton("Update Produs");
    private JButton btn6 = new JButton("Sterge Produs");

    private JButton btn7 = new JButton("Adauga produs cos");
    private JButton btn8 = new JButton("Sterge produs cos");
    private JButton btn9 = new JButton("Finalizare comanda");

    private JButton btn10 = new JButton("Sterge comanda");
    private JButton btn11 = new JButton("Afiseaza produse");
    //private JButton btn12 = new JButton("Sterge produs");

    private JLabel labelStatus1 = new JLabel("Status: ");
    private JLabel labelStatus2 = new JLabel("Status: ");
    private JLabel labelStatus3 = new JLabel("Status: ");
    private JLabel labelStatus4 = new JLabel("Status: ");
    private JLabel labelStatusPret = new JLabel("Total: 0");

    private JLabel label1 = new JLabel("Nume ");
    private JLabel label2 = new JLabel("Prenume ");
    private JLabel label3 = new JLabel("Varsta ");
    private JLabel label4 = new JLabel("Email ");
    private JLabel label5 = new JLabel("Adresa ");
    private JLabel label6 = new JLabel("Telefon ");

    private JLabel label7 = new JLabel("Nume Produs");
    private JLabel label8 = new JLabel("Pret ");
    private JLabel label9 = new JLabel("Cantiate ");

    private JLabel label10 = new JLabel("Client ");
    private JLabel label11 = new JLabel("Adresa ");
    private JLabel label12 = new JLabel("Telefon ");
    private JLabel label13 = new JLabel("Produs ");
    private JLabel label14 = new JLabel("Cantiate Produs Stock");
    private JLabel label15 = new JLabel("Cantiate Produs Dorita");

    private JTextField text1 = new JTextField(50);
    private JTextField text2 = new JTextField(50);
    private JTextField text3 = new JTextField(50);
    private JTextField text4 = new JTextField(50);
    private JTextField text5 = new JTextField(50);
    private JTextField text6 = new JTextField(50);
    private JTextField text7 = new JTextField(50);
    private JTextField text8 = new JTextField(50);
    private JTextField text9 = new JTextField(50);
    private JTextField text10 = new JTextField(50);
    private JTextField text11 = new JTextField(50);
    private JTextField text12 = new JTextField(50);
    private JTextField text13 = new JTextField(50);

    private JTabbedPane tabbedPane = new JTabbedPane();

    private JComboBox comb1 = new JComboBox();
    private JComboBox comb2 = new JComboBox();

    //client interface
    private JTable table1;
    private JScrollPane scrol1;
    //end client interface

    //product interface
    private JTable table2;
    private JScrollPane scrol2;
    //end product interface

    //comenzi interface
    private JTable table3;
    private JScrollPane scrol3;
    //end comenzi interface

    //istoric comenzi
    private JTable table4;
    private JScrollPane scrol4;

    private JTable table5;
    private JScrollPane scrol5;
    //end istoric comenzi

    public View(String name){
        super(name);
        this.initGUI();
    }

    public void initGUI(){
        this.setSize(700, 470);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(this.mainPanel);
        this.initTables();
        this.initTable3();
        this.initComboBoxes();
        this.initComoponents();
        this.setCommands();
    }

    public void initComboBoxes(){
        comb1.removeAllItems();comb1.revalidate();comb1.repaint();
        comb2.removeAllItems();comb2.revalidate();comb2.repaint();

        List<Client> listaClienti = controller.getClientiFromDB();
        List<Produs> listaProduse = controller.getProdusFromDB();

        for(Client c : listaClienti){
            comb1.addItem(new String(c.getId() + " " + c.getNume() + " " + c.getPrenume()));
        }

        for(Produs p : listaProduse){
            comb2.addItem(new String(p.getId() + " " +p.getNumeProdus()) + " -> " + p.getPretProdus() + " lei/u");
        }
    }

    public void initTables(){
        table1 = controller.UpdateTable1();
        scrol1 = new JScrollPane(table1);
        panelAux1.removeAll();
        panelAux1.revalidate();
        panelAux1.repaint();
        panelAux1.add(scrol1);

        table2 = controller.UpdateTable2();
        scrol2 = new JScrollPane(table2);
        panelAux2.removeAll();
        panelAux2.revalidate();
        panelAux2.repaint();
        panelAux2.add(scrol2);

        table4 = controller.UpdateTable4();
        scrol4 = new JScrollPane(table4);
        panelAux4.removeAll();
        panelAux4.revalidate();
        panelAux4.repaint();
        panelAux4.add(scrol4);

        table5 = controller.UpdateTable5();
        scrol5 = new JScrollPane(table5);

        activeComponentsLastTab();
    }

    public void initTable3(){
        table3 = controller.UpdateTable3();
        scrol3 = new JScrollPane(table3);
        panelAux3.removeAll();
        panelAux3.revalidate();
        panelAux3.repaint();
        panelAux3.add(scrol3);
    }

    public void UpdateTable3(){
        table3 = controller.UpdateAfterDeleteTable3();
        scrol3 = new JScrollPane(table3);
        panelAux3.removeAll();
        panelAux3.revalidate();
        panelAux3.repaint();
        panelAux3.add(scrol3);
    }

    public void initComoponents() {
        //client
        panel1.setLayout(new GridLayout(3,0));
        panel4.setLayout(new GridLayout(0,2));
        panel5.setLayout(new FlowLayout());

        panel4.add(label1);
        panel4.add(text1);
        panel4.add(label2);
        panel4.add(text2);
        panel4.add(label3);
        panel4.add(text3);
        panel4.add(label4);
        panel4.add(text4);
        panel4.add(label5);
        panel4.add(text5);
        panel4.add(label6);
        panel4.add(text6);
        panel4.add(btn1);
        panel4.add(labelStatus1);

        panel5.add(btn2);
        panel5.add(btn3);

        table1.setFillsViewportHeight(true);
        panel1.add(panel4); //spatiu de introdus date pt creare client

        panelAux1.setLayout(new GridLayout(1,0));
        panelAux1.add(scrol1);
        panel1.add(panelAux1); //tabel pt interogare tabel

        panel1.add(panel5); //butoane pt update si stergere
        //end client

        //produs
        panel2.setLayout(new GridLayout(3,0));
        panel6.setLayout(new GridLayout(0,2));
        panel7.setLayout(new FlowLayout());

        panel6.add(label7);
        panel6.add(text7);
        panel6.add(label8);
        panel6.add(text8);
        panel6.add(label9);
        panel6.add(text9);
        panel6.add(btn4);
        panel6.add(labelStatus2);

        table2.setFillsViewportHeight(true);

        panel7.add(btn5);
        panel7.add(btn6);

        panel2.add(panel6);

        panelAux2.setLayout(new GridLayout(1,0));
        panelAux2.add(scrol2);
        panel2.add(panelAux2);

        panel2.add(panel7);
        //end produs

        //comenzi
        panel3.setLayout(new GridLayout(3,0));
        panel8.setLayout(new GridLayout(0,2));
        panel9.setLayout(new FlowLayout());

        panel8.add(label10);
        panel8.add(comb1);
        panel8.add(label11);
        panel8.add(text10);
        panel8.add(label12);
        panel8.add(text11);
        panel8.add(label13);
        panel8.add(comb2);
        panel8.add(label14);
        panel8.add(text12);
        panel8.add(label15);
        panel8.add(text13);
        panel8.add(btn7);
        panel8.add(labelStatus3);

        panel9.add(btn8);
        panel9.add(btn9);
        panel9.add(labelStatusPret);

        table3.setFillsViewportHeight(true);

        panel3.add(panel8);

        panelAux3.setLayout(new GridLayout(1,0));
        panelAux3.add(scrol3);
        panel3.add(panelAux3);

        panel3.add(panel9);
        //end comenzi

        //istoric comenzi
        panel10.setLayout(new GridLayout(3,0));
        panel11.setLayout(new FlowLayout());

        table4.setFillsViewportHeight(true);
        table5.setFillsViewportHeight(true);
        panelAux4.setLayout(new GridLayout(1,0));
        panelAux4.add(scrol4);
        panelAux5.setLayout(new GridLayout(1,0));
        panelAux5.add(scrol5);

        //panel11.add(btn11);
        panel11.add(btn10);
        //panel11.add(btn12);
        panel11.add(labelStatus4);

        panel10.add(panelAux4);
        panel10.add(panelAux5);
        panel10.add(panel11);
        //end istoric comenzi

        tabbedPane.addTab("Client", panel1);
        tabbedPane.addTab("Produs", panel2);
        tabbedPane.addTab("Comanda", panel3);
        tabbedPane.addTab("Istoric Comanda", panel10);

        mainPanel.add(tabbedPane);
    }

    public void setCommands(){
        btn1.setActionCommand("CreateClient");
        btn1.addActionListener(this.controller);
        btn2.setActionCommand("UpdateClient");
        btn2.addActionListener(this.controller);
        btn3.setActionCommand("DeleteClient");
        btn3.addActionListener(this.controller);

        btn4.setActionCommand("CreateProdus");
        btn4.addActionListener(this.controller);
        btn5.setActionCommand("UpdateProdus");
        btn5.addActionListener(this.controller);
        btn6.setActionCommand("DeleteProdus");
        btn6.addActionListener(this.controller);

        btn7.setActionCommand("AdaugaProdusCos");
        btn7.addActionListener(this.controller);
        btn8.setActionCommand("StergeProdusCos");
        btn8.addActionListener(this.controller);
        btn9.setActionCommand("FinalizareComanda");
        btn9.addActionListener(this.controller);

        btn10.setActionCommand("StergeComanda");
        btn10.addActionListener(this.controller);

        btn11.setActionCommand("AfiseazaProduse");
        btn11.addActionListener(this.controller);

        activeComponents();
    }

    public void activeComponentsLastTab(){
        table4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                table5 = controller.UpdateTable5();
                scrol5 = new JScrollPane(table5);
                panelAux5.removeAll();
                panelAux5.revalidate();
                panelAux5.repaint();
                panelAux5.add(scrol5);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private Produs selectedProduct;
    public void activeComponents(){
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("Selected pane: " + tabbedPane.getSelectedIndex());
                initComboBoxes();
            }
        });

        comb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comb2.getSelectedItem() != null) {
                    int index=-1;
                    String str = comb2.getSelectedItem().toString();
                    Pattern pattern = Pattern.compile( "\\d+");
                    Matcher matcher = pattern.matcher(str);
                    while(matcher.find()){
                        index = Integer.parseInt(matcher.group().toString());break;
                    }

                    List<Produs> listaProduse = controller.getProdusFromDB();
                    for(Produs p : listaProduse){
                        if(p.getId() == index){
                            text12.setText(String.valueOf(p.getCantitate()));
                            selectedProduct = p;
                            break;
                        }
                    }
                }
            }
        });

        comb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comb1.getSelectedItem() != null) {
                    int index=-1;
                    String str = comb1.getSelectedItem().toString();
                    Pattern pattern = Pattern.compile( "\\d+");
                    Matcher matcher = pattern.matcher(str);
                    while(matcher.find()){
                        index = Integer.parseInt(matcher.group().toString());break;
                    }

                    List<Client> listaClienti = controller.getClientiFromDB();
                    for(Client c : listaClienti){
                        if(c.getId() == index){
                            text10.setText(c.getAdresa());
                            text11.setText(c.getNrTelefon());
                            break;
                        }
                    }
                }
            }
        });
    }

    public Client getNewCLient(){
        try {
            return (new Client(-1, text1.getText(), text2.getText(), Integer.parseInt(text3.getText()), text4.getText(), text5.getText(), text6.getText()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Produs getNewProdus(){
        try {
            return (new Produs(-1, text7.getText(), Integer.parseInt(text8.getText()), Integer.parseInt(text9.getText())));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Produs getSelectedProduct(){
        return selectedProduct;
    }

    public String[][] getTable3Data(){
        String[][] listaProduse = new String[100][100];
            for (int i = 0; i < table3.getRowCount(); i++) {
                if(table3.getValueAt(i,0) != null) {
                    listaProduse[i][0] = table3.getValueAt(i, 0).toString();
                    listaProduse[i][1] = table3.getValueAt(i, 1).toString();
                    listaProduse[i][2] = table3.getValueAt(i, 2).toString();
                    listaProduse[i][3] = table3.getValueAt(i, 3).toString();
                }
            }
        return listaProduse;
    }

    public int getCantitateDorita()
    {
        return Integer.parseInt(text13.getText().toString());
    }

    public int getTable3RowCount(){
        return table3.getRowCount();
    }

    public int getTable3SelectedRow(){
        return  table3.getSelectedRow();
    }

    public void resetTable3(){
        table3 = controller.ResetTable3();
        scrol3 = new JScrollPane(table3);
        panelAux3.removeAll();
        panelAux3.revalidate();
        panelAux3.repaint();
        panelAux3.add(scrol3);
    }

    public void setLabelStatus1(String msg){
        labelStatus1.setText("Status: " + msg);
    }

    public void setLabelStatus2(String msg){
        labelStatus2.setText("Status: " + msg);
    }

    public void setLabelStatus3(String msg){
        labelStatus3.setText("Status: " + msg);
    }

    public void setLabelStatus4(String msg){
        labelStatus4.setText("Status: " + msg);
    }

    public void setLabelPret(String msg){
        labelStatusPret.setText("Total: " + msg);
    }

    public String getLabelPret(){
        return  labelStatusPret.getText();
    }

    public int getSelectedRowClientId(){
        int row = table1.getSelectedRow();
        int id = Integer.parseInt(table1.getValueAt(row,0).toString());
        return id;
    }

    public int getSelectedRowProdusId(){
        int row = table2.getSelectedRow();
        int id = Integer.parseInt(table2.getValueAt(row,0).toString());
        return id;
    }

    public int getSelectedClientId(){
        int id=-1;
        String str = comb1.getSelectedItem().toString();
        Pattern pattern = Pattern.compile( "\\d+");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            id = Integer.parseInt(matcher.group().toString());break;
        }

        return id;
    }

    public int getSelectedProductToBeDeleted(){
        return table3.getSelectedRow();
    }

    public List<Client> getUpdatedDataFromTable1(){
        int cont=0;
        Client claux;
        List<Client> clientList = controller.getClientiFromDB();
        List<Client> newClientList = new ArrayList<>();

        for(Client c: clientList){
            claux = new Client(Integer.parseInt(table1.getValueAt(cont,0).toString()), table1.getValueAt(cont,1).toString(), table1.getValueAt(cont,2).toString(),
                    Integer.parseInt(table1.getValueAt(cont,3).toString()), table1.getValueAt(cont,4).toString(), table1.getValueAt(cont,5).toString(),
                    table1.getValueAt(cont,6).toString());
            cont++;

            if(!c.equals(claux)){
                newClientList.add(claux);
            }
        }
        return newClientList;
    }

    public List<Produs> getUpdatedDataFromTable2(){
        int cont=0;
        Produs prodaux;
        List<Produs> produsList = controller.getProdusFromDB();
        List<Produs> newProdusList = new ArrayList<>();

        for(Produs prod: produsList){
            prodaux = new Produs(Integer.parseInt(table2.getValueAt(cont,0).toString()), table2.getValueAt(cont,1).toString(),
                    Integer.parseInt(table2.getValueAt(cont,2).toString()), Integer.parseInt(table2.getValueAt(cont,3).toString()));
            cont++;

            if(!prod.equals(prodaux)){
                newProdusList.add(prodaux);
            }
        }
        return newProdusList;
    }

    public int getSelectedCommandIndexTable4(){
        int row = table4.getSelectedRow();
        int id = -1;
        if(table4.getSelectedRow() != -1)
            id = Integer.parseInt(table4.getValueAt(row,0).toString());;
        return id;
    }
}
