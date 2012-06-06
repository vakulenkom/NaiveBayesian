package Test;
import Exceptions.WrongDataException;
import junit.framework.Assert;
import org.junit.Test;
import Sample.Sample;
import Sample.TeachingSample;
import Sample.AnalyseSample;

/**
 * Created by IntelliJ IDEA.
 * User: Мария
 * Date: 19.04.12
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */   //Класс тестирующий работу пересептрона с пакетом Sample
public class TestSample {
    private String filename = "/Users/mike/Documents/Study/6 semestr/JavaProjects/NaiveBayesian/src/IRIS.txt" ;
    private Sample sample;
    private TeachingSample teachingSample;
    private AnalyseSample analyseSample;
    private double speed = 0.01;
    private int iterations = 5000000;
   
   

    public TestSample() {
        sample = new Sample(filename);
        teachingSample = new TeachingSample(filename);
        try{
            analyseSample = new AnalyseSample(teachingSample,speed,iterations);
        }
        catch(WrongDataException ex){
            ex.printStackTrace(System.err);
        }

    }


    @Test
    public void testSamples(){
        try{
            analyseSample.classify(sample);
            int[] copy = analyseSample.classification.clone();
            analyseSample.classify(teachingSample);
            for(int i=0;i<copy.length;i++){
                Assert.assertEquals(analyseSample.classification[i],copy[i]);
            }
        }
        catch (WrongDataException ex){
            ex.printStackTrace(System.err);
        }

    }

    @Test
    public void testStat(){
        try{
            analyseSample.classify(sample);
            System.out.println(analyseSample.getStatistics(sample,teachingSample.classes));
            Assert.assertTrue(analyseSample.getStatistics(sample,teachingSample.classes)>=50);
        }
        catch(WrongDataException ex){
            ex.printStackTrace(System.err);
        }
    }

}
