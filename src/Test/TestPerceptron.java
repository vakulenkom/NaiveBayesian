package Test;
import Exceptions.WrongDataException;
import Perceptron.Perceptron;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: 09Kurbatova
 * Date: 11.04.12
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
    // Класс, тестирующий работу персептрона для заданной выборки
public class TestPerceptron {
    private double vectors [][] = {
            {0,0,0},
            {0,0,1},
            {0,1,0},
            {1,0,0},
            {0,0,2},
            {0,2,0},
            {2,0,0},
            {0,1,1},
            {1,1,0},
            {0,2,2},
            {2,2,0},
            {0,1,2},
            {0,2,1},
            {2,1,0},
            {1,2,0},
            {1,2,1},
            {1,1,2},
            {2,1,1},
            {1,1,1},
            {2,2,2},
            {2,2,1},
            {2,1,2},
            {1,2,2} ,
            {2,0,2}
    }    ;
    private int classes[] = {0,1,1,1,2,2,2,2,2,4,4,3,3,3,3,4,4,4,3,6,5,5,5,4};
    private int classNumber = 7;
    private int vectorsNumber =vectors.length;
    private int vectorSize = 3;
    private double speed = 0.01;
    private int NumberOfIterations = 5000000;
    private Perceptron perceptron;

    public TestPerceptron() {
        perceptron = new Perceptron(classNumber,vectorsNumber,vectorSize);
    }



   @Test
    public void TestIsTaughtRight(){
      try{
          perceptron.teach(vectors,classes,speed,NumberOfIterations);
            int fails = 0;
           try{
                for(int i=0;i<vectors.length;i++){
                   if(classes[i]!=perceptron.classify(vectors[i])){
                       fails++;
                   }
                }
               System.out.println("Number of fails = "+fails);
               Assert.assertTrue(fails<vectors.length/2);
               Assert.assertTrue(perceptron.isTaught());
           }
           catch(WrongDataException ex) {
               ex.printStackTrace(System.err);
           }

      }
    catch(WrongDataException ex){
         ex.fillInStackTrace();
         ex.printStackTrace(System.err);
      }

    }




}
