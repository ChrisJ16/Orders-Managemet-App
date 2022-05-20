package DataAcces;

import Model.Produs;
import Connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdusDao extends AbstractDAO<Produs>{

    protected static final Logger LOGGER = Logger.getLogger(ProdusDao.class.getName());
    private static final String insertStatementString = "INSERT INTO produs (numeProdus,pretProdus,cantitate)"
            + " VALUES (?,?,?)";

    private static final String updateStatementString = "UPDATE produs SET numeProdus=?,pretProdus=?,cantitate=? WHERE id=?";

    @Override
    public Produs insert(Produs prod){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, prod.getNumeProdus());
            insertStatement.setInt(2, prod.getPretProdus());
            insertStatement.setInt(3, prod.getCantitate());
            insertStatement.executeUpdate();

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProdusDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }
        return prod;
    }

    @Override
    public Produs update(Produs prod){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, prod.getNumeProdus());
            updateStatement.setInt(2, prod.getPretProdus());
            updateStatement.setInt(3, prod.getCantitate());
            updateStatement.setInt(4, prod.getId());
            updateStatement.executeUpdate();

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProdusDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
        return prod;
    }
}