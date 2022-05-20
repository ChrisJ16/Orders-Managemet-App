package DataAcces;

import Model.Comanda;
import Connection.*;
import Model.Produs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComandaDao extends AbstractDAO<Comanda>{

    protected static final Logger LOGGER = Logger.getLogger(ComandaDao.class.getName());
    private static final String insertStatementString = "INSERT INTO comanda(idClient,numeClient,adresClient,nrTelefonClient,total)"
            + " VALUES (?,?,?,?,?)";

    private static final String updateStatementString = "UPDATE comanda SET idClient=?,numeClient=?,adresClient=?,nrTelefonClient=?,total=? WHERE id=?";

    @Override
    public Comanda insert(Comanda cmd){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, cmd.getIdClient());
            insertStatement.setString(2, cmd.getNumeClient());
            insertStatement.setString(3, cmd.getAdresClient());
            insertStatement.setString(4, cmd.getNrTelefonClient());
            insertStatement.setInt(5, cmd.getTotal());
            insertStatement.executeUpdate();

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ComandaDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }
        return cmd;
    }

    @Override
    public Comanda update(Comanda cmd){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, cmd.getIdClient());
            updateStatement.setString(2, cmd.getNumeClient());
            updateStatement.setString(3, cmd.getAdresClient());
            updateStatement.setString(4, cmd.getNrTelefonClient());
            updateStatement.setInt(5, cmd.getTotal());
            updateStatement.executeUpdate();

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ComandaDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
        return cmd;
    }
}
