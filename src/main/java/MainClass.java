import javax.swing.*;

import BussinesLogic.ComandaBLL;
import Presentation.*;

import java.util.logging.Logger;

public class MainClass {
    protected static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        JFrame frame = new View("Orders Mangament App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        /*
        Client cl1 = new Client(1, "Popa", "Cristian", 21, "cristi.popa_16@yahoo.com", "Str.Fantanele", "0786150483");
        Client cl2 = new Client(2, "Toderici", "Diana", 19, "diana.tod@yahoo.com", "Str.Parangului", "0837839932");
        Client cl3 = new Client(3, "Popa", "Gheorghe", 21, "gggg.popa@yahoo.com", "Str.Fantanele", "0789999483");

        ClientBLL clbll = new ClientBLL();
        Client ctest = null;

        try{
            clbll.updateClient(cl3);
        }
        catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
        }

        ProdusBLL produsBLL = new ProdusBLL();
        List<Produs> rez = produsBLL.findAll();

        Comanda newComanda = new Comanda(1,2,"Popa","Str. Fantanele", "0786150483",100);
        ComandaBLL cmdBLL = new ComandaBLL();
        Comanda c =  cmdBLL.findComandaById(1);
        */
    }
}