import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Flashed on 01-04-2017.
 */
public class Experiment {


    public  void executeExperiment(IQueries query, int[] persons) {

        int max, min;
        double avg, timeAvg;
        long timeMax, timeMin, startTime, stopTime = 0;
        List<Integer> values;
        List<Long> valuesTime;

        //For each depth
        for(int j = 0; j <3; j++){ //SET THE DEPTH INTERVAL HERE

            values = new ArrayList<Integer>();
            valuesTime = new ArrayList<Long>();
            //For each person
            for(int i = 0; i < persons.length; i++){
                startTime = System.currentTimeMillis();
                switch(j) {
                    case 0:
                        values.add(query.depthOne(i));
                        stopTime = System.currentTimeMillis();
                        break;
                    case 1:
                        values.add(query.depthTwo(i));
                        stopTime = System.currentTimeMillis();
                        break;
                    case 2:
                        values.add(query.depthThree(i));
                        stopTime = System.currentTimeMillis();
                        break;
                    case 3:
                        values.add(query.depthFour(i));
                        stopTime = System.currentTimeMillis();
                        break;
                    case 4:
                        values.add(query.depthFive(i));
                        stopTime = System.currentTimeMillis();
                        break;
                    default:
                        break;
                }
                valuesTime.add(stopTime-startTime);
            }
            max = Collections.max(values);
            min = Collections.min(values);


            double sum = 0;
            for(Integer value : values) { sum += value;}
            avg = sum/values.size();

            System.out.println("Endorsments for: " + query.getQueryName() + " at depth " + (j+1) + " = Max: " + max + ", Min: " + min + ", Avg: " + avg);

            timeMax = Collections.max(valuesTime);
            timeMin = Collections.min(valuesTime);

            double sumTime = 0;
            for(long value : valuesTime) { sumTime += value;}
            timeAvg = sumTime/values.size();
            System.out.println("Time for: " + query.getQueryName() + " at depth " + (j+1) + " = Max: " + timeMax + "ms, Min: " + timeMin + "ms, Avg: " + timeAvg + "ms");
        }



    }
}
