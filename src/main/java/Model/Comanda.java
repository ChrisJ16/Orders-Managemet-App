package Model;

import java.util.*;

public class Comanda {
    private int id;
    private int idClient;
    private String numeClient;
    private String adresClient;
    private String nrTelefonClient;
    private int total;

    public Comanda(){}

    public Comanda(int id, int idClient, String numeClient, String adresClient, String nrTel, int total){
        this.id=id;
        this.idClient=idClient;
        this.numeClient=numeClient;
        this.adresClient=adresClient;
        this.nrTelefonClient=nrTel;
        this.total=total;
    }

    /*
    public void addComanda(Produs prod)
    {
        listaProduse.add(prod);
    }

    public String prodComanda(){
        String result = new String();
        for(Produs p : listaProduse){
            result = result + p.getNumeProdus() + ", ";
        }
        return result;
    }
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getAdresClient() {
        return adresClient;
    }

    public void setAdresClient(String adresClient) {
        this.adresClient = adresClient;
    }

    public String getNrTelefonClient() {
        return nrTelefonClient;
    }

    public void setNrTelefonClient(String nrTelefonClient) {
        this.nrTelefonClient = nrTelefonClient;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    @Override
    public String toString(){
        return "Comanda[id="+this.id+", id client="+this.idClient+", nume="+this.numeClient+", adresa="+this.adresClient+
                ", telefon="+this.nrTelefonClient+", total="+this.total+"]";
    }
}
