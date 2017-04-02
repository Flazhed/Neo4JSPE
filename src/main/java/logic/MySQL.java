import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ms on 01-04-17.
 */
public class MySQL implements IQueries {

    private java.sql.Connection connection;
    private String url = "jdbc:mysql://172.17.0.2:3306/mysql";
    private String user = "root";
    private String password = "password";
    private String name;


    public MySQL(String name) {
        this.name = name;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int depthOne(int nodeId) {

        String sqlString = "SELECT count(DISTINCT target_node_id) " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id = " + nodeId;
        return executeQuery(sqlString);
    }

    public int depthTwo(int nodeId) {

        String str = "SELECT count(DISTINCT target_node_id) " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                " FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id = " + nodeId + ")";
        return executeQuery(str);
    }

    public int depthThree(int nodeId) {
        String str = "SELECT count(DISTINCT target_node_id) " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                " FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id = " + nodeId + "))";
        return executeQuery(str);
    }

    public int depthFour(int nodeId) {
        String str = "SELECT count(DISTINCT target_node_id) " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                " FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id = " + nodeId + ")))";

        return executeQuery(str);
    }

    public int depthFive(int nodeId) {
        String str = "SELECT count(DISTINCT target_node_id) " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                " FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id IN (SELECT target_node_id " +
                "FROM t_user " +
                "INNER JOIN t_endorses ON t_user.id = source_node_id " +
                "WHERE source_node_id = " + nodeId + "))))";
        return executeQuery(str);
    }

    public String getQueryName() {
        return this.name;
    }

    private int executeQuery(String sqlString) {


        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlString);
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

}
