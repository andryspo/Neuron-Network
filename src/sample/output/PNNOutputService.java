package sample.output;

import java.io.IOException;
import java.util.List;

public class PNNOutputService {

    private FileUtil fileUtil;
    private StringBuilder builder;

    public PNNOutputService(String path) throws IOException {
        fileUtil = new FileUtil(path);
        builder = new StringBuilder();
    }

    public void appendNewClass(String className, List<Double> r, List<Double> d) throws IOException {

        builder.append("\n\nClass name: " + className).append("\nR\t\tD\n");

        if (r.size() == d.size()) {
            for (int i = 0; i < r.size(); i++) {
                builder.
                        append(r.get(i)).
                        append("\t").
                        append(d.get(i)).
                        append("\n");
            }
        }
        builder.append("\n");
        fileUtil.append(builder.toString());
        builder.setLength(0);
    }

    public void appendGeneralInfo(double sigma, List<Double> params) throws IOException {

        builder.append("params: ");
        for (Double p : params) {
            builder.append(p).
                    append("\t");
        }

        builder.append("\nsigma: ").
                append(sigma);

        fileUtil.append(builder.toString());
        builder.setLength(0);
    }

    public void appendResult(List<Double> p, String irisName) throws IOException {
        builder.append("\nResult: p{} = ");
        for (Double pi : p) {
            builder
                    .append(pi)
                    .append("\t");
        }
        builder
                .append("Iris: ")
                .append(irisName);
        fileUtil.append(builder.toString());
        builder.setLength(0);
    }
}
