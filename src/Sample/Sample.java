package Sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Мария
 * Date: 19.04.12
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */

//Класс, читающий набор векторов из файла
public class Sample {
    public double vectors[][];

    public Sample() {
       vectors = null;
    }

    public Sample(String filename) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int countVectors = 0;
            ArrayList<String> lines = new ArrayList<String>();
            while((line = reader.readLine())!=null) {
                if(!line.equals("")){
                    lines.add(line);
                    countVectors++;
                }
            }
            vectors = new double[countVectors][];
            countVectors = 0;
            for(int j=0;j<lines.size();j++){
                String [] data = lines.get(j).split(",");
                vectors[countVectors] = new double[data.length-1];
                for(int i=0; i<data.length-1;i++){
                    vectors[countVectors][i] = Double.parseDouble(data[i]);
                }
                countVectors++;
            }
            reader.close();
        }
        catch(FileNotFoundException except){
            vectors = null;
        }
        catch(IOException except) {
            vectors = null;
        }
    }
}
