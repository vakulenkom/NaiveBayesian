package Perceptron;

import Exceptions.WrongDataException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Мария
 * Date: 05.04.12
 * Time: 0:41
 * To change this template use File | Settings | File Templates.
 */

// класс сложный Персептрон  - делит выборку на несколько классов

public class Perceptron {
    private int classNumber;
    private int vectorsNumber;
    private int vectorSize;
    private ArrayList<SimplePerceptron> perceptron;
    private boolean isTaught;
    private final int minNumberOfClasses = 2;

//аргументы конструктора - количество классов в выборке, количество векторов в выборке, размерность векторов выборки

    public Perceptron(int classNumber, int vectorsNumber, int vectorSize) {
        this.classNumber = classNumber;
        this.vectorSize = vectorSize;
        this.vectorsNumber = vectorsNumber;
        perceptron = new ArrayList<SimplePerceptron>();
        isTaught = false;
        
    }

    /*обучающий метод  -  входные данные: обучающая выборка,
                                         соответсвующие ей классы,
                                         скорость обучения,
                                         количество допустимых итераций при обучении
    */

    public long teach(double vectors[][],int expectedClass[],double speed, int numberOfIterations) throws WrongDataException {
        long iterations = 0;
        //если число классов обучающей выборки равно минимальному, т.е. двум, то создается 1 простой нейрон и проводится его обучение
        if(classNumber == minNumberOfClasses){
            if(isTaught()){
                perceptron.clear();
            }
            perceptron.add(new SimplePerceptron(vectorSize));
            iterations = perceptron.get(perceptron.size()-1).teachSimplePerceptron(vectors,expectedClass,speed,numberOfIterations);
            for(int i=0;i<vectors.length;i++){
                //classes[i]=perceptron.get(perceptron.size()-1).count(vectors[i]);
            }
            isTaught = true;
            return iterations;
        } else if (classNumber > minNumberOfClasses){  //если число классов больше двух
           if(isTaught()){
                perceptron.clear();
            }
            int count = 0;   //счетчик количества уже определенных классов  == номер определяемого класса
            HashMap<Integer,Integer> detected = new HashMap<Integer, Integer>();    //создается хэш-таблица, где отмечается какие из векторов уже классифицированны (номеру вектора соответсвует номер класса, или -1, если для него класс еще не определен)
            for(int i=0;i<vectors.length;i++){
                detected.put(i,-1);
            }
            while(classNumber - count >= minNumberOfClasses){
                Set<Integer> keysDetected = detected.keySet();
                int numberNotDetected =0;
                Integer checkingInd = null;
                for(Integer ind:keysDetected) {
                    if(detected.get(ind)<0){
                        numberNotDetected++;   //определение количества неклассифицированных векторов на данной итерации
                        checkingInd = ind;
                    }
                }
                if(numberNotDetected<minNumberOfClasses) {
                    detected.put(checkingInd,count);
                    break;
                }
                double tempVectors [][] = new double [numberNotDetected][vectorSize];  //создаем массив который будет состоять из еще неклассифицированных векторов выборки
                int tempClassesMass [] = new int[numberNotDetected]; //массив соответсвующих им классов
                HashMap<Integer,Integer> indexes = new HashMap<Integer, Integer>(); //соответствие векторов новой выборки векторам начальной выборки
                int indexTemp=0;
                for(Integer index : keysDetected) {          //заполнение массива
                    if(detected.get(index)<0) {
                        tempVectors[indexTemp] = vectors[index].clone();
                        indexes.put(indexTemp,index);
                        if(expectedClass[index]-count>0) {
                            tempClassesMass[indexTemp] = 1;
                        }   else {
                            tempClassesMass[indexTemp] = 0;
                        }
                        indexTemp++;
                    }

                }
                SimplePerceptron p = new SimplePerceptron(vectorSize); //новый нейрон
                iterations += p.teachSimplePerceptron(tempVectors,tempClassesMass,speed,numberOfIterations);  //обучение
                for(int i=0;i<tempVectors.length;i++) {
                    if(p.classify(tempVectors[i])==0) {
                        detected.put(indexes.get(i),count);
                    }
                }
                perceptron.add(p);
                count++;
            }

            isTaught = true;
            return iterations;
        } else {
            throw new WrongDataException("Class Perceptron.Perceptron: error in the input data - more classes expected.");
        }

    }


    //метод, выполняющий классификацию заданного вектора

    public int classify(double vector[]) throws WrongDataException {
        if(isTaught){
            if(classNumber==2){
                return  perceptron.get(perceptron.size()-1).classify(vector);
            }else {
                int i;
                for(i=0;i<perceptron.size();i++){
                    if(perceptron.get(i).classify(vector)==0){
                        break;
                    }
                }
                return i;
            }
        } else{
            throw new WrongDataException("Error:Class Perceptron.Perceptron: the perceptron hasn't been taught.");
        }
    }
     /* метод для проверки состояния персептрона возвращает истину, если персептрон обучен, и ложь -
                                                            в противном случае */
     public boolean isTaught(){
         return isTaught;
     }

             
             

    
}
