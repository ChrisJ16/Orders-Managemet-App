package BussinesLogic;
import Model.*;
import DataAcces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProdusBLL {
    private ProdusDao produsDao = new ProdusDao();

    public List<Produs> findAll() {
        List<Produs> rez = produsDao.findAll();
        return rez;
    }

    public Produs findProdusById(int id){
        Produs prod = produsDao.findById(id);
        if(prod.equals(null)){
            throw new NoSuchElementException("The Produs with id =" + id + " was not found!");
        }
        return prod;
    }

    public void insertProdus(Produs prod){
        produsDao.insert(prod);
    }

    public void updateProdus(Produs prod){ produsDao.update(prod); }

    public void deleteProdus(int id){produsDao.delete(id);}

    public String[] getTableHeaders(){return produsDao.collumnNames();}
}
