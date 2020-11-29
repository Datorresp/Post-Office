package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.ControllerModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMain implements Initializable {

    private final ControllerModel cm;

    private final ControllerAddWholesaler controllerAddWholesaler;
    private final ControllerAddConnections controllerAddConnections;
    private final ControllerRoute controllerRoute;

    @FXML
    private BorderPane bp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public ControllerMain() {
        cm = new ControllerModel();
        controllerAddWholesaler = new ControllerAddWholesaler(this);
        controllerAddConnections = new ControllerAddConnections(this);
        controllerRoute = new ControllerRoute(this);
    }

    public ControllerModel getCm() {
        return cm;
    }

    public BorderPane getBp() {
        return bp;
    }

    @FXML
    void addWholesaler() {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("../windows/fxAddWholesaler.fxml"));
            fl.setController(controllerAddWholesaler);
            Pane p = fl.load();
            bp.setCenter(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addConnections() {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("../windows/fxAddConnections.fxml"));
            fl.setController(controllerAddConnections);
            Pane p = fl.load();
            bp.setCenter(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void createRoute() {
        try{
            FXMLLoader fl = new FXMLLoader(getClass().getResource("../windows/fxCreateRoute.fxml"));
            fl.setController(controllerRoute);
            Pane p = fl.load();
            bp.setCenter(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
