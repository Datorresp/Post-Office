package controllers;

import dataStructures.GraphDLabelled;
import exceptions.AlreadyExistConnectionException;
import exceptions.SomethingIsMissingException;
import exceptions.VerticesNotCreatedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.ControllerModel;
import model.Wholesaler;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerAddConnections implements Initializable {

    private final ControllerMain cm;

    @FXML
    private ComboBox<Wholesaler> cb1;

    @FXML
    private ComboBox<Wholesaler> cb2;

    @FXML
    private TextField tfDistance;

    public ControllerAddConnections(ControllerMain controllerMain) {
        cm = controllerMain;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerModel c = cm.getCm();
        ObservableList<Wholesaler> ol1 = FXCollections.observableArrayList(c.getWholesalers());
        cb1.setItems(ol1);
        cb2.setItems(ol1);
    }

    @FXML
    void connections() {
        ControllerModel c = cm.getCm();
        GraphDLabelled<Wholesaler> w = c.getGraphs();
        Wholesaler w1 = cb1.getValue();
        Wholesaler w2 = cb2.getValue();
        try {
            if (w == null){
                throw new VerticesNotCreatedException();
            }
            if (w.existEdge(w1, w2)) {
                throw new AlreadyExistConnectionException(w1, w2);
            }
            int d = Integer.parseInt(tfDistance.getText());
            if (w1 == null || w2 == null) {
                throw new SomethingIsMissingException();
            }
            c.createConnections(w1, w2, d);
            alert2();
            clean();
        }catch (SomethingIsMissingException | NumberFormatException | NoSuchMethodException | AlreadyExistConnectionException | VerticesNotCreatedException e){
            alert1(e);
            clean();
        }

    }

    private void clean() {
        cb1.setValue(null);
        cb2.setValue(null);
        tfDistance.setText("");
    }

    private void alert1(Exception e) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("Exception");
        a.setContentText(e.getMessage());
        a.showAndWait();
    }

    private void alert2() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Creation of the connection");
        a.setContentText("the creation of the connection has been successful");
        a.showAndWait();
    }
}
