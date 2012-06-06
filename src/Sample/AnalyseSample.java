package Sample;

import Exceptions.WrongDataException;
import Perceptron.Perceptron;

/**
 * Created by IntelliJ IDEA.
 * User: Мария
 * Date: 19.04.12
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */

//Класс, анализирующий выборку - выполняет обучение перцептрона для заданной обучающей выборки
// и дальнейшую классификацию задаваемых наборов векторов

public class AnalyseSample {
    public int[] classification;
    private Perceptron perceptron;

    public AnalyseSample() {
        classification = null;
        perceptron = null;
    }

    public AnalyseSample(TeachingSample sample, double speed, int numberOfIterations) throws WrongDataException {
       perceptron = new Perceptron(sample.classes.length,sample.vectors.length,sample.vectors[0].length);
        try{
            perceptron.teach(sample.vectors,sample.classes,speed,numberOfIterations);
        }
        catch (WrongDataException ex){
            throw ex;
        }
       classification = new int[sample.vectors.length];
    }
    
    public int classify(Sample sample) throws WrongDataException {

        try{
            for(int i=0;i<sample.vectors.length;i++){
               classification[i] = perceptron.classify(sample.vectors[i]);
            }
            return 1;
        }
        catch(WrongDataException ex){
            throw ex;
        }
            

    }

    public double getStatistics(Sample sample, int expectedClasses[]) throws WrongDataException {   //Метод, определяющий процент ошибочно определенных классов в заданной выборке
        double success = 0;
        try{
             classify(sample);
        }
        catch(WrongDataException ex){
            throw ex;
        }
        for(int i=0;i<classification.length;i++){
            if(classification[i]==expectedClasses[i]){
                success++;
            }
        }
        success/=(double)classification.length;
        success*=100.0;
        return success;

    }
    
    
}
