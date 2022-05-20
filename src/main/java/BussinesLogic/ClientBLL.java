package BussinesLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Model.*;
import DataAcces.*;

public class ClientBLL {
    private ClientDao cldao = new ClientDao();

    public List<Client> findAll() {
        List<Client> rez = cldao.findAll();
        return rez;
    }

    public Client findClientById(int id){
        Client cl = cldao.findById(id);
        if(cl.equals(null)){
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return cl;
    }

    public void insertClient(Client cl){
        cldao.insert(cl);
    }

    public void updateClient(Client cl){
        cldao.update(cl);
    }

    public void deleteClient(int id){cldao.delete(id);}

    public String[] getTableHeaders(){return cldao.collumnNames();}
}
