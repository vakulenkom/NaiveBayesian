package Bayesian;
import java.lang.Math;
/**
 * User: mike
 * e-mail: vakulenkom@gmail.com
 * Date: 06.06.12
 * Time: 16:53
 */
public class Pnormal {
    double m;
    double d;
    public Pnormal (double m, double d ){
        this.m = m;
        this.d = d;
    }

    public double density (double x){
        return Math.exp(-(Math.pow(x - m, 2) / (2*d) )) / (Math.sqrt(d*2*Math.PI)) ;
    }
}
