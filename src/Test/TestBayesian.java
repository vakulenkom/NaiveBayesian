package Test;

import Bayesian.Bayesian;
import junit.framework.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: mike
 * e-mail: vakulenkom@gmail.com
 * Date: 04.06.12
 * Time: 17:32
 */
public class TestBayesian {private double vectors [][];

    private String filename = "/Users/mike/Documents/Study/6 semestr/JavaProjects/NaiveBayesian/src/IRIS.txt" ;
    private int classes[];
    private int classNumber ;
    private int vectorsNumber ;
    private int vectorSize = 3;
    private Bayesian bayesian;

    public TestBayesian() {
    }



    @Test
    public void Test(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int countVectors = 0;
            ArrayList<String> lines = new ArrayList<String>();
            while ((line = reader.readLine())!=null){
                countVectors++;
                lines.add(line);
            }
            vectors = new double[countVectors][];
            classes = new int[countVectors];
            countVectors = 0;
            for(int j=0;j<lines.size();j++){
                String [] data = lines.get(j).split(",");
                vectors[countVectors] = new double[data.length-1];
                for(int i=0; i<data.length-1;i++){
                    vectors[countVectors][i] = Double.parseDouble(data[i]);
                }
                classes[countVectors] = Integer.parseInt(data[data.length-1]);
                countVectors++;
            }
            reader.close();
        }
        catch(FileNotFoundException except){
            System.out.println("File not found.");
            classes = null;
            vectors = null;
        }
        catch(IOException except) {
            classes = null;
            vectors = null;
        }

        bayesian = new Bayesian(vectors.length, 4);

        bayesian.teach(vectors, classes);
        bayesian.classify(vectors[0]);

            int fails = 0;
                for(int i=0;i<vectors.length;i++){
                    if(classes[i]!=bayesian.classify(vectors[i])){
                        fails++;
                    }
                }
        double percentOfSuccess = 1 - (double) fails / (double) vectors.length;
        System.out.println("Percent of success = "+percentOfSuccess);
                Assert.assertTrue(fails < vectors.length / 2);
    }




}