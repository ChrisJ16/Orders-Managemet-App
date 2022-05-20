package BussinesLogic;

import Model.*;
import DataAcces.*;

import java.util.List;
import java.util.NoSuchElementException;

public class ComandaBLL {
    private ComandaDao comandaDao = new ComandaDao();

    public List<Comanda> findAll() {
        List<Comanda> rez = comandaDao.findAll();
        return rez;
    }

    public Comanda findComandaById(int id){
        Comanda cmd = comandaDao.findById(id);
        if(cmd.equals(null)){
            throw new NoSuchElementException("The Commands with id =" + id + " was not found!");
        }
        return cmd;
    }

    public void insertComanda(Comanda cmd){comandaDao.insert(cmd);}

    public void updateComanda(Comanda cmd){ comandaDao.update(cmd); }

    public void deleteComanda(int id){comandaDao.delete(id);}

    public String[] getTableHeaders(){return comandaDao.collumnNames();}
}
