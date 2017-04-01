

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ms on 01-04-17.
 */
public class MySQL implements IQueries {

    private java.sql.Connection connection;
    private String url = "jdbc:mysql://localhost:3306/social";
    private String user = "root";
    private String password = "pwd";
    private String name;


    public MySQL(String name) {
        this.name = name;
        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int depthOne(int nodeId) {

        String sqlString = "SELECT COUNT(DISTINCT end1.source_node_id, end1.target_node_id) FROM " +
                "t_user node INNER JOIN t_endorses end1 on node.id = end1.source_node_id " +
                "WHERE node.id = " + nodeId;
        return executeQuery(sqlString);
    }

    public int depthTwo(int nodeId) {

        String sqlString = "SELECT COUNT(DISTINCT end2.source_node_id, end2.target_node_id) FROM t_user node " +
                "INNER JOIN t_endorses end1 on node.id = end1.source_node_id " +
                "INNER JOIN t_endorses end2 on end1.target_node_id = end2.source_node_id " +
                "WHERE node.id = " + nodeId;
        return executeQuery(sqlString);
    }

    public int depthThree(int nodeId) {
        String sqlString = "SELECT COUNT(DISTINCT end3.source_node_id, end3.target_node_id) FROM t_user node " +
                "INNER JOIN t_endorses end1 on node.id = end1.source_node_id " +
                "INNER JOIN t_endorses end2 on end1.target_node_id = end2.source_node_id " +
                "INNER JOIN t_endorses end3 on end2.target_node_id = end3.source_node_id " +
                "WHERE node.id = " + nodeId;
        return executeQuery(sqlString);
    }

    public int depthFour(int nodeId) {
        String sqlString = "SELECT COUNT(DISTINCT end4.source_node_id, end4.target_node_id) FROM t_user node " +
                "INNER JOIN t_endorses end1 on node.id = end1.source_node_id " +
                "INNER JOIN t_endorses end2 on end1.target_node_id = end2.source_node_id " +
                "INNER JOIN t_endorses end3 on end2.target_node_id = end3.source_node_id " +
                "INNER JOIN t_endorses end4 on end3.target_node_id = end4.source_node_id " +
                "WHERE node.id = " + nodeId;
        return executeQuery(sqlString);
    }

    public int depthFive(int nodeId) {
        String sqlString = "SELECT COUNT(DISTINCT end5.source_node_id, end5.target_node_id) FROM t_user node " +
                "INNER JOIN t_endorses end1 on node.id = end1.source_node_id " +
                "INNER JOIN t_endorses end2 on end1.target_node_id = end2.source_node_id " +
                "INNER JOIN t_endorses end3 on end2.target_node_id = end3.source_node_id " +
                "INNER JOIN t_endorses end4 on end3.target_node_id = end4.source_node_id " +
                "INNER JOIN t_endorses end5 on end4.target_node_id = end5.source_node_id " +
                "WHERE node.id = " + nodeId;
        return executeQuery(sqlString);
    }

    public String getQueryName() {
        return this.name;
    }

    private int executeQuery(String sqlString){


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
