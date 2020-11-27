package controllers;

import javafx.fxml.Initializable;
import model.ControllerModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMain implements Initializable {

    private ControllerModel cm;

    private ControllerAddWholesaler controllerAddWholesaler;
    private ControllerAddConnections controllerAddConnections;
    private ControllerRoute controllerRoute;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public ControllerMain() {
        cm = new ControllerModel();
        controllerAddWholesaler = new ControllerAddWholesaler(this);
        controllerAddConnections = new ControllerAddConnections(this);
        controllerRoute = new ControllerRoute(this);
    }
}
