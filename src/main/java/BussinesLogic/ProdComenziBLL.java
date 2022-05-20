package BussinesLogic;

import DataAcces.ComandaDao;
import DataAcces.ProdComenziDao;
import Model.Comanda;
import Model.ProdComenzi;

import java.util.List;
import java.util.NoSuchElementException;

public class ProdComenziBLL {
    private ProdComenziDao prodComandaDao = new ProdComenziDao();

    public List<ProdComenzi> findAll() {
        List<ProdComenzi> rez = prodComandaDao.findAll();
        return rez;
    }

    public ProdComenzi findProdComenziById(int id){
        ProdComenzi prodCmd = prodComandaDao.findById(id);
        if(prodCmd.equals(null)){
            throw new NoSuchElementException("The prodCmd with id =" + id + " was not found!");
        }
        return prodCmd;
    }

    public void insertProdComanda(ProdComenzi cmd){prodComandaDao.insert(cmd);}

    public void deleteProdComanda(int id){prodComandaDao.delete(id);}

    public String[] getTableHeaders(){return prodComandaDao.collumnNames();}
}
