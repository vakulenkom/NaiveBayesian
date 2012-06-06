package Bayesian;

/**
 * User: mike
 * e-mail: vakulenkom@gmail.com
 * Date: 04.06.12
 * Time: 16:36
 */
public class Bayesian {
    private int vectorsNumber;
    private int vectorSize;
    double[] classFreq;
    Pnormal[][] featFreq;
    int[] expectedClasses;
    public static int classNumber = 3;

    public Bayesian(int vectorsNumber, int vectorSize){
        this.vectorSize = vectorSize;
        this.vectorsNumber = vectorsNumber;
    }

    public void teach(double vectors[][],int expectedClasses[]){
        this.expectedClasses = expectedClasses;
        classFreq = new double[classNumber];
        featFreq = new Pnormal[classNumber][vectorSize];
        double[] M = new double[vectorSize];
        double[] D = new double[vectorSize];

        int num = 0;
        int[] numberOfClass = new int[classNumber];
        for (int c=0; c<=2; c++){
            for (int j=0; j<vectorSize; j++){
                M[j] = 0;
                D[j] = 0;
            }
            for (int i=0; i<vectorsNumber; i++ ){
                if (expectedClasses[i] == c){
                    for (int j=0; j<vectorSize; j++){
                        M[j] += vectors[i][j];
                    }
                numberOfClass[c]++;
                }
            }
            for (int j=0; j<vectorSize; j++){
                M[j] /= numberOfClass[c];
            }
            for (int i=0; i<vectorsNumber; i++ ){
                if (expectedClasses[i] == c){
                    for (int j=0; j<vectorSize; j++){
                        D[j] += Math.abs(vectors[i][j] - M[j]);
                    }
                }
            }
            for (int j=0; j<vectorSize; j++){
                D[j] = (Math.pow(D[j], 2) / (double) numberOfClass[c]);
            }
            for (int j=0; j<vectorSize; j++){
                featFreq [c][j] = new Pnormal(M[j], D[j]);
            }
            classFreq[c] = (double) numberOfClass[c]/(double) vectorsNumber;
        }
    }

    public int classify(double[] feat) {
        double P = 1;
        double F = 0;
        int arg=-1;
        for  (int i=0; i<classNumber; i++){
            P = 1;
            for (int j=0; j<feat.length; j++){
                    P *= featFreq[i][j].density(feat[j]);
            }
//            System.out.println("class number = "+i+" P= "+P+" and classFr = "+classFreq[j]);
            if (classFreq[i]*P > F){
                F = classFreq[i]*P;
                arg = i;
            }
        }
//        System.out.print(arg);
        return arg;
//        return 0;
    }

//    public int classify(double vector[]) {
//        return int class;
//    }
}
