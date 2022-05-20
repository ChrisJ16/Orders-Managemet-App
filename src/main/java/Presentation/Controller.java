package Presentation;

import BussinesLogic.*;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class Controller implements ActionListener {
    private View view;
    private Client newClient;
    private Produs newProdus;
    private List<Produs> produsList = new ArrayList<Produs>();
    private ClientBLL clientBLL = new ClientBLL();
    private ProdusBLL produsBLL = new ProdusBLL();
    private ComandaBLL comandaBLL = new ComandaBLL();
    private ProdComenziBLL prodComenziBLL = new ProdComenziBLL();

    public Controller(View v) {
        this.view = v;
    }

    public List<Client> getClientiFromDB() {
        return clientBLL.findAll();
    }

    public List<Produs> getProdusFromDB() {
        return produsBLL.findAll();
    }

    public int updatePriceTag(int price) {
        String str = view.getLabelPret();
        int strSize = str.length();
        int pret = Integer.parseInt(str.substring(7, strSize));
        pret = pret + price;
        view.setLabelPret(String.valueOf(pret));
        return pret;
    }

    private String[] sortT1(String[] columnName){
        String aux;
        aux = columnName[1];
        columnName[1] = columnName[3];
        columnName[3] = aux;
        aux = columnName[2];
        columnName[2] = columnName[4];
        columnName[4] = aux;
        aux = columnName[4];
        columnName[4] = columnName[5];
        columnName[5] = aux;
        aux = columnName[6];
        columnName[6] = columnName[5];
        columnName[5] = aux;
        return columnName;
    }

    public JTable UpdateTable1() {
        List<Client> listaClienti = clientBLL.findAll();
        String data[][] = new String[100][100];
        String columnName[] = sortT1(clientBLL.getTableHeaders());

        int cont = 0;
        for (Client c : listaClienti) {
            data[cont][0] = String.valueOf(c.getId());
            data[cont][1] = c.getNume();
            data[cont][2] = c.getPrenume();
            data[cont][3] = String.valueOf(c.getVarsta());
            data[cont][4] = c.getEmail();
            data[cont][5] = c.getAdresa();
            data[cont][6] = c.getNrTelefon();
            cont++;
        }
        JTable table = new JTable(data, columnName);
        return table;
    }

    public JTable UpdateTable2() {
        List<Produs> listaProduse = produsBLL.findAll();
        String data[][] = new String[100][100];
        String columnName[] = produsBLL.getTableHeaders();

        int cont = 0;
        for (Produs prods : listaProduse) {
            data[cont][0] = String.valueOf(prods.getId());
            data[cont][1] = prods.getNumeProdus();
            data[cont][2] = String.valueOf(prods.getPretProdus());
            data[cont][3] = String.valueOf(prods.getCantitate());

            cont++;
        }
        JTable table = new JTable(data, columnName);
        return table;
    }

    public JTable UpdateTable3() {
        String data[][] = new String[100][100];
        String columnName[] = produsBLL.getTableHeaders();
        try {
            Produs selectedProduct = view.getSelectedProduct();
            if (selectedProduct != null) {
                data = view.getTable3Data();
                int rowCount = 0;
                for (int i = 0; i < view.getTable3RowCount(); i++)
                    if (data[i][0] != null)
                        rowCount++;

                if (view.getCantitateDorita() > selectedProduct.getCantitate())
                    view.setLabelStatus3("Nu exista atatea produse in stock!");
                else {
                    data[rowCount][0] = String.valueOf(selectedProduct.getId());
                    data[rowCount][1] = String.valueOf(selectedProduct.getNumeProdus());
                    data[rowCount][2] = String.valueOf(selectedProduct.getPretProdus());
                    data[rowCount][3] = String.valueOf(view.getCantitateDorita());
                    view.setLabelStatus3("Produs introdus cu succes!");

                    updatePriceTag(view.getCantitateDorita() * selectedProduct.getPretProdus());
                }
            }
        } catch (Exception e) {
            view.setLabelStatus3("Produsul nu s-a putut introduce in cos");
        }

        JTable table = new JTable(data, columnName);
        return table;
    }

    public JTable ResetTable3(){
        String data[][] = new String[100][100];
        String columnName[] = produsBLL.getTableHeaders();

        JTable table = new JTable(data, columnName);
        return table;
    }

    public JTable UpdateAfterDeleteTable3() {
        String data[][] = new String[100][100];
        String newData[][] = new String[100][100];
        //String columnName[] = {"ID", "NUME PRODUS", "PRET", "CANTITATE"};
        String columnName[] = produsBLL.getTableHeaders();

        data = view.getTable3Data();
        int idToBeEliminated = view.getTable3SelectedRow();
        int price = 0, cont = 0;

        for (int i = 0; i < view.getTable3RowCount(); i++) {
            if (i != idToBeEliminated) {
                newData[cont][0] = data[i][0];
                newData[cont][1] = data[i][1];
                newData[cont][2] = data[i][2];
                newData[cont][3] = data[i][3];
                cont++;
            } else {
                price = (-1) * Integer.parseInt(data[i][2]) * Integer.parseInt(data[i][3]);
            }
        }
        this.updatePriceTag(price);
        JTable table = new JTable(newData, columnName);
        return table;
    }

    public JTable UpdateTable4() {
        String data[][] = new String[100][10];
        //String columnName[] = {"ID COMANDA", "ID CLIENT", "NUME", "ADRESA", "TELEFON", "TOTAL"};
        String columnName[] = comandaBLL.getTableHeaders();
        List<Comanda> listaComenzi = comandaBLL.findAll();

        int cont = 0;
        for (Comanda c : listaComenzi) {
            data[cont][0] = String.valueOf(c.getId());
            data[cont][1] = String.valueOf(c.getIdClient());
            data[cont][2] = c.getNumeClient();
            data[cont][3] = c.getAdresClient();
            data[cont][4] = c.getNrTelefonClient();
            data[cont][5] = String.valueOf(c.getTotal());
            cont++;
        }

        JTable table = new JTable(data, columnName);

        return table;
    }

    public JTable UpdateTable5() {
        String data[][] = new String[100][100];
        String columnName[] = prodComenziBLL.getTableHeaders();
        int idComanda = view.getSelectedCommandIndexTable4();
        int cont = 0;
        //System.out.println("Id comanda : " + idComanda);
        if (idComanda != -1) {
            List<ProdComenzi> prodComenziList = new ArrayList<>();
            prodComenziList = prodComenziBLL.findAll();

            for (ProdComenzi prodCmd : prodComenziList) {
                if (prodCmd.getId() == idComanda) {
                    //System.out.println(prodCmd.toString());
                    data[cont][0] = String.valueOf(prodCmd.getId());
                    data[cont][1] = String.valueOf(prodCmd.getIdProdus());
                    data[cont][2] = prodCmd.getNumeProdus();
                    data[cont][3] = String.valueOf(prodCmd.getCantProdComanda());
                    cont++;
                }
            }
        }
        JTable table = new JTable(data, columnName);
        return table;
    }

    public String billMessageHeader(String nume, String adresa, String telefon, int total){
        String str = new String();
        str = "Nume client: " + nume + "\n" +
                "Adresa client: " + adresa +"\n"+
                "Telefon client: " + telefon +"\n"+
                "Total produse: " + total + "\n";
        return str;
    }

    public String addProdToBill(String str, String numeProd, int pret, int cant) {
        str=str+
            "Produs: " + numeProd + ", Pret: " + pret + ", Cantitate: "+ cant + ", Total/produs: " + pret*cant + " lei\n";
        return str;
    }

    public void writeToFile(String msg) {
        List<Comanda> listComanda = new ArrayList<>();
        listComanda = comandaBLL.findAll();
        int size = listComanda.size()+1;
        String path = "order_" + size + ".txt";
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("Factura generata: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("A aparut o eroare la generarea facturii");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(msg);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("A aparaut o eroare la scrierea facturii");
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "CreateClient":
                try{
                    newClient = view.getNewCLient();
                }catch (Exception exception){
                    exception.printStackTrace();
                }finally {
                    view.setLabelStatus1("Client nu s-a putut adauga!");
                }
                System.out.println("Noua intrare: " + newClient.toString());
                try{
                    clientBLL.insertClient(newClient);
                    view.setLabelStatus1("Client adaugat cu succes!");
                }catch (Exception exception){
                    exception.printStackTrace();
                    view.setLabelStatus1("Client nu s-a putut adauga!");
                }
                view.initTables();
            break;

            case "UpdateClient":
                System.out.println("Update client");
                List<Client> newClientList = view.getUpdatedDataFromTable1();
                for(Client c : newClientList){
                    try{
                        clientBLL.updateClient(c);
                        view.setLabelStatus1("Update realizat cu succes!");
                    }
                    catch (Exception ex) {
                        view.setLabelStatus1("Eroare la update!");
                        ex.printStackTrace();
                    }
                }
                view.initTables();
                break;

            case "DeleteClient":
                int id = view.getSelectedRowClientId();
                System.out.println("Stergere client cu id-ul:" + id);
                try{
                    clientBLL.deleteClient(id);
                    view.setLabelStatus1("Client sters cu succes!");
                }catch (Exception ex) {
                    view.setLabelStatus1("Eroare la stergere!");
                    ex.printStackTrace();
                }
                view.initTables();
                break;

            case "CreateProdus":
                try{
                    newProdus = view.getNewProdus();
                }catch (Exception exception){
                    exception.printStackTrace();
                }finally {
                    view.setLabelStatus2("Client nu s-a putut adauga!");
                }
                System.out.println("Noua intrare: " + newProdus.toString());
                try{
                    produsBLL.insertProdus(newProdus);
                    view.setLabelStatus2("Produs adaugat cu succes!");
                }catch (Exception exception){
                    exception.printStackTrace();
                    view.setLabelStatus2("Produsul nu s-a putut adauga!");
                }
                view.initTables();
                break;
            case "UpdateProdus":
                System.out.println("Update produs");
                List<Produs> newProdusList = view.getUpdatedDataFromTable2();
                for(Produs prod : newProdusList){
                    try{
                        produsBLL.updateProdus(prod);
                        view.setLabelStatus2("Produsul " + prod.getId() + " updatat cu succes!");
                    }
                    catch (Exception ex) {
                        view.setLabelStatus2("Eroare la update!");
                        ex.printStackTrace();
                    }
                }
                view.initTables();
                break;
            case "DeleteProdus":
                System.out.println("Delete produs");
                int idProdus = view.getSelectedRowProdusId();
                System.out.println("Stergere produsului cu id-ul:" + idProdus);
                try{
                    produsBLL.deleteProdus(idProdus);
                    view.setLabelStatus2("Produs sters cu succes!");
                }catch (Exception ex) {
                    view.setLabelStatus2("Eroare la stergere!");
                    ex.printStackTrace();
                }
                view.initTables();
                break;

            case "AdaugaProdusCos":
                System.out.println("Adauga Produs Cos");
                view.initTable3();
                break;
            case "StergeProdusCos":
                System.out.println("Sterge Produs Cos");
                view.UpdateTable3();
                break;
            case "FinalizareComanda":
                System.out.println("Finalizare Comanda");
                String str = view.getLabelPret();
                int strSize = str.length();
                int pret = Integer.parseInt(str.substring(7,strSize));
                if(pret == 0)
                    view.setLabelStatus3("Nu ati adaugat nimic in cos, comanda invalida!");
                else
                {
                    String msgToPrint = new String();
                    Client c = clientBLL.findClientById(view.getSelectedClientId());
                    Comanda cmd = new Comanda(-1,c.getId(),c.getNume() + " " +c.getPrenume(), c.getAdresa(), c.getNrTelefon(), pret);
                    comandaBLL.insertComanda(cmd);
                    msgToPrint = billMessageHeader(c.getNume() + " " +c.getPrenume(), c.getAdresa(), c.getNrTelefon(), pret);

                    String data[][] = new String[100][10];
                    data = view.getTable3Data();
                    int idProdT, pretProdT, cantProdT;
                    String numeProdT;

                    List<Comanda> listaComenzi = comandaBLL.findAll();
                    Comanda lastCommand = listaComenzi.get(listaComenzi.size()-1);
                    for(int i=0; i<view.getTable3RowCount(); i++){
                        if(data[i][0]!= null){
                            idProdT = Integer.parseInt(data[i][0]);
                            numeProdT = data[i][1].toString();
                            pretProdT = Integer.parseInt(data[i][2]);
                            cantProdT = Integer.parseInt(data[i][3]);

                            msgToPrint = addProdToBill(msgToPrint,numeProdT,pretProdT,cantProdT);

                            Produs oldProd = produsBLL.findProdusById(idProdT);
                            Produs newProd = new Produs(oldProd.getId(),oldProd.getNumeProdus(),oldProd.getPretProdus(),oldProd.getCantitate() - cantProdT);
                            System.out.println(newProd.toString());
                            produsBLL.updateProdus(newProd);

                            ProdComenzi prodCmd = new ProdComenzi(lastCommand.getId(),idProdT,numeProdT,cantProdT);
                            prodComenziBLL.insertProdComanda(prodCmd);
                        }
                        else {
                            break;
                        }
                    }
                    writeToFile(msgToPrint);
                }
                view.setLabelPret("0");
                view.resetTable3();
                view.initTables();
                break;
            case "StergeComanda":
                System.out.println("Sterge Comanda");
                int idComanda = view.getSelectedCommandIndexTable4();
                try {
                    comandaBLL.deleteComanda(idComanda);
                    view.setLabelStatus4("Comanda stearsa cu succes!");
                }catch (Exception exe){
                    view.setLabelStatus4("Eroare la stergerea comenzii!");
                }
                view.initTables();
                break;
        }
    }
}
