package sample.pnn;

import sample.iris.Iris;

import java.util.ArrayList;
import java.util.List;

public class PNN {
    public static List<Double> calcR(List<? extends Iris> testSample, Iris iris) {

        List<Double> r = new ArrayList<>();

        for (Iris testIris : testSample) {
            double sum = 0;
            for (int j = 0; j < testIris.getParameters().size(); j++) {
                sum += Math.pow(testIris.getParameters().get(j) - iris.getParameters().get(j), 2);
            }
            r.add(Math.sqrt(sum));
        }

        return r;
    }

    public static List<Double> calcD(List<Double> r, double sigma) {

        List<Double> d = new ArrayList<>();

        for (Double ri : r) {
            d.add(Math.exp(-(ri * ri) / (sigma * sigma)));
        }

        return d;
    }

    public static List<Double> calcP(List<Double>... ds) {
        List<Double> p = new ArrayList<>();

        double sumD = 0;

        for (List<Double> di : ds) {
            sumD += sum(di);
        }

        for (List<Double> di : ds) {
            p.add(sum(di) / sumD);
        }

        return p;
    }

    private static double sum(List<Double> list) {

        double sum = 0;

        for (Double i : list) {
            sum += i;
        }

        return sum;
    }
}
