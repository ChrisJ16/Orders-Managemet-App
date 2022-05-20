package DataAcces;

import Model.Comanda;
import Connection.*;
import Model.ProdComenzi;
import Model.Produs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdComenziDao extends AbstractDAO<ProdComenzi>{

    protected static final Logger LOGGER = Logger.getLogger(ProdComenziDao.class.getName());
    private static final String insertStatementString = "INSERT INTO prodComenzi (id,idProdus,numeProdus,cantProdComanda)"
            + " VALUES (?,?,?,?)";

    @Override
    public ProdComenzi insert(ProdComenzi prodCmd){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, prodCmd.getId());
            insertStatement.setInt(2, prodCmd.getIdProdus());
            insertStatement.setString(3, prodCmd.getNumeProdus());
            insertStatement.setInt(4, prodCmd.getCantProdComanda());
            insertStatement.executeUpdate();

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProdComenziDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }
        return prodCmd;
    }
}
