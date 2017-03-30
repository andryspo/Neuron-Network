package sample.iris;

import java.util.ArrayList;
import java.util.List;

public class Iris {

    public static final int COUNT_OF_PARAMS = 4;

    private List<Double> parameters;

    public Iris(List<Double> parameters) {
        this.parameters = parameters;
    }

    public Iris(String[] params) {
        parameters = new ArrayList<>();
        if(params.length  >= COUNT_OF_PARAMS) {
            parameters.add(Double.parseDouble(params[0]));
            parameters.add(Double.parseDouble(params[1]));
            parameters.add(Double.parseDouble(params[2]));
            parameters.add(Double.parseDouble(params[3]));
        }
    }

    public List<Double> getParameters() {
        return parameters;
    }

}
