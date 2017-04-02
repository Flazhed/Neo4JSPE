package logic;

import interfaces.IQueries;
import org.neo4j.driver.v1.*;

/**
 * Created by ms on 01-04-17.
 */
public class Neo4j implements IQueries {

    Driver driver;
    Session session;
    private String name;

    public Neo4j(String name){
        this.driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "class" ) );
        this.session  = driver.session();
        this.name = name;
    }

    public int depthOne(int nodeId) {

        String cypherString = "MATCH (a:Person)-[:ENDORSES]->(c)" +
                "WHERE a.id = " + nodeId +
                " RETURN count(distinct(c))";
        return executeQuery(cypherString);
    }

    public int depthTwo(int nodeId) {

        String cypherString = "MATCH (a:Person)-[:ENDORSES*2..2]->(c)" +
                "WHERE a.id = " + nodeId +
                " RETURN count(distinct(c))";
        return executeQuery(cypherString);
    }

    public int depthThree(int nodeId) {

        String cypherString = "MATCH (a:Person)-[:ENDORSES*3..3]->(c)" +
                "WHERE a.id = " + nodeId +
                " RETURN count(distinct(c))";
        return executeQuery(cypherString);
    }

    public int depthFour(int nodeId) {

        String cypherString = "MATCH (a:Person)-[:ENDORSES*4..4]->(c)" +
                "WHERE a.id = " + nodeId +
                " RETURN count(distinct(c))";
        return executeQuery(cypherString);
    }

    public int depthFive(int nodeId) {

        String cypherString = "MATCH (a:Person)-[:ENDORSES*5..5]->(c)" +
                "WHERE a.id = " + nodeId +
                " RETURN count(distinct(c))";
    return executeQuery(cypherString);
    }

    public String getQueryName() {
        return this.name;
    }

    private int executeQuery(String query){

        StatementResult result = session.run(query);

        Record record = result.next();
        return record.get(0).asInt();


    }

}
