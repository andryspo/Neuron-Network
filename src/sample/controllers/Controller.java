package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.exceptions.UnsupportedIrisType;
import sample.iris.*;
import sample.output.PNNOutputService;
import sample.pnn.PNN;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextField sigmaTF;
    @FXML
    private TextField par1;
    @FXML
    private TextField par2;
    @FXML
    private TextField par3;
    @FXML
    private TextField par4;
    @FXML
    private TextArea resultTA;


    @FXML
    private void initialize() {
        sigmaTF.setText("0.2");
        par1.setText("5.2");
        par2.setText("1.4");
        par3.setText("3.4");
        par4.setText("0.2");
    }

    public void calc(ActionEvent actionEvent) throws IOException {

        File file = new File(getClass().getClassLoader().getResource("training_sample.txt").getFile());

        List<Verginica> verginicaIris = new ArrayList<>();
        List<Versicolor> versicolorIris = new ArrayList<>();
        List<Setosa> setosaIris = new ArrayList<>();

        List<Double> vergR;
        List<Double> versicolorR;
        List<Double> setosaR;

        List<Double> vergD;
        List<Double> versicolorD;
        List<Double> setosaD;

        List<Double> p;
        String irisType;

        PNNOutputService outputService = new PNNOutputService("result.txt");

        double sigma = Double.parseDouble(sigmaTF.getText());

        Iris iris = new Iris(new String[]{
                par1.getText(),
                par2.getText(),
                par3.getText(),
                par4.getText()
        });

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = reader.readLine()) != null) {
                String[] params = str.split(" ");
                switch (params[4]) {
                    case IrisTypes.SETOSA:
                        setosaIris.add(new Setosa(params));
                        break;
                    case IrisTypes.VERGINICA:
                        verginicaIris.add(new Verginica(params));
                        break;
                    case IrisTypes.VERSICOLOR:
                        versicolorIris.add(new Versicolor(params));
                        break;
                    default:
                        throw new UnsupportedIrisType();
                }
            }
        }

        vergR = PNN.calcR(verginicaIris, iris);
        setosaR = PNN.calcR(setosaIris, iris);
        versicolorR = PNN.calcR(versicolorIris, iris);

        vergD = PNN.calcD(vergR, sigma);
        setosaD = PNN.calcD(setosaR, sigma);
        versicolorD = PNN.calcD(versicolorR, sigma);

        outputService.appendGeneralInfo(sigma, iris.getParameters());
        outputService.appendNewClass(IrisTypes.SETOSA, setosaR, setosaD);
        outputService.appendNewClass(IrisTypes.VERGINICA, vergR, vergD);
        outputService.appendNewClass(IrisTypes.VERSICOLOR, versicolorR, versicolorD);

        p = PNN.calcP(setosaD, vergD, versicolorD);

        if (p.get(0) > p.get(1) && p.get(0) > p.get(2)) {
            irisType = IrisTypes.SETOSA;
        } else if (p.get(1) > p.get(0) && p.get(1) > p.get(2)) {
            irisType = IrisTypes.VERGINICA;
        } else {
            irisType = IrisTypes.VERSICOLOR;
        }


        outputService.appendResult(p, irisType);
    }
}
