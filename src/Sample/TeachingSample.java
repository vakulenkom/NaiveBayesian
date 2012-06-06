package Sample;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Мария
 * Date: 19.04.12
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
// Класс задающий обучающую выборку (выборка хранится в файле)
public class TeachingSample extends Sample{
    public int classes[];

    public TeachingSample() {
        classes = null;
    }

    public TeachingSample(String filename) {
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
    }

}
