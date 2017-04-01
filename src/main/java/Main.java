import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by ms on 01-04-17.
 */
public class Main {
    public static void main(String[] args)  {

        Random rand = new Random();
        List<IQueries> queries = new ArrayList<IQueries>();
        queries.add(new MySQL("MySQL"));
        queries.add(new Cypher("Neo4J"));



        int[] idArray = new int[20];

        for(int i = 0; i <20; i++){
            idArray[i] = rand.nextInt(500000) +1;
        }

        Experiment experiment = new Experiment();
        for (IQueries query: queries) {
            experiment.executeExperiment(query, idArray);
        }



    }


}
