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
}
