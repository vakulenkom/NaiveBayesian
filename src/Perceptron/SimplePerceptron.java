package Perceptron;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Мария
 * Date: 19.03.12
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */

//класс простой Персептрон  - делит выборку только на 2 класса

public class SimplePerceptron {
   private int vectorSize;   //размер векторов выборки
   private double weights[];  // массив весов (W0 - последний элемент массива)
   private boolean isTaught;//флаг, показывающий состояние персептрона (обучен/не обучен)


  

    // конструктор - количесвто векторов в выборке, размеры векторов в выборке
    
    public SimplePerceptron(int vectorSize) {
        this.vectorSize = vectorSize;
        this.weights = new double[vectorSize+1];
        for(int i=0;i<weights.length-1;i++)
        {
            weights[i]= Math.random()*0.2+0.1;
        }
        weights[weights.length-1] = -1*(Math.random()*0.2+0.1);
        isTaught = false;
    }


    // метод, классифицирующий вектор из выборки


    public int classify(double vector[]){
        double sum = weights[weights.length-1];
        for(int i=0;i<vectorSize;i++) {
            sum = sum + weights[i]*vector[i] ;
        }
        if(sum>0.0)
            return 1;
        else
            return 0;
    }


    // "исправление" значений весов в ходе обучения персептрона

    private void improveWeights(double vector[],double speed, double error) {
        for(int i=0;i<vector.length;i++)
            weights[i] += vector[i]*speed*error;

    }

    /*обучение персептрона  разделению объектов на два класса  - на вход подается обучающая выборка:
           массив векторов и массив соответсвующих им классов, скорость обучения, количество итераций, допустимых при обучении
           Обучение проводится до тех пор, пока ошибка распознования не станет равной нулю,
           либо не будет превышен допустимый объем итераций
    */

    public int teachSimplePerceptron(double vectors[][], int expectedClass[], double speed, int NumberOfIterations){
        double Error;
        int it = 0;
        int[] recognisedClass = new int[vectors.length];
        while(true){
            Error = 0;
            for(int i=0;i<vectors.length;i++) {
                recognisedClass[i] = classify(vectors[i]);
                double temp = expectedClass[i] - recognisedClass[i];
                Error+=Math.abs(temp);
                improveWeights(vectors[i], speed, Math.signum(temp)*Error);
            }

            it++;
          if (Error==0 || it==NumberOfIterations) {
              break;
          }
        }
        isTaught = true;
        return it;

    }





}
