package DataAcces;
import Model.Client;
import Connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientDao extends AbstractDAO<Client>{

    protected static final Logger LOGGER = Logger.getLogger(ClientDao.class.getName());
    private static final String insertStatementString = "INSERT INTO client (nume,prenume,varsta,email,adresa,nrTelefon)"
            + " VALUES (?,?,?,?,?,?)";

    private static final String updateStatementString = "UPDATE client SET nume=?,prenume=?,varsta=?,email=?,adresa=?,nrTelefon=?"
            + " WHERE id=?";

    @Override
    public Client insert(Client cl){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId=-1;
        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, cl.getNume());
            insertStatement.setString(2, cl.getPrenume());
            insertStatement.setInt(3, cl.getVarsta());
            insertStatement.setString(4, cl.getEmail());
            insertStatement.setString(5, cl.getAdresa());
            insertStatement.setString(6, cl.getNrTelefon());
            insertStatement.executeUpdate();

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }
        return cl;
    }

    @Override
    public Client update(Client cl){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, cl.getNume());
            updateStatement.setString(2, cl.getPrenume());
            updateStatement.setInt(3, cl.getVarsta());
            updateStatement.setString(4, cl.getEmail());
            updateStatement.setString(5, cl.getAdresa());
            updateStatement.setString(6, cl.getNrTelefon());
            updateStatement.setInt(7, cl.getId());
            updateStatement.executeUpdate();

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
        return cl;
    }
}
