package controllers;

import exceptions.NullObjectException;
import exceptions.SomethingIsMissingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.ControllerModel;
import model.Wholesaler;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAddWholesaler implements Initializable {

    private final ControllerMain cm;

    @FXML
    private TextField tfi;

    @FXML
    private TextField tfName;

    public ControllerAddWholesaler(ControllerMain controllerMain) {
        cm = controllerMain;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addWholesaler() {
        ControllerModel c = cm.getCm();
        try{
            String id = tfi.getText();
            String name = tfName.getText();
            if (id.equals("") || name.equals(""))
                throw new SomethingIsMissingException();
            Wholesaler newW = new Wholesaler(name, id);
            c.addNewWholesalers(newW);
            clean();
            alert1(id, name);
        }catch (SomethingIsMissingException | NullObjectException e) {
            alert2(e);
        }
    }

    private void clean() {
        tfi.setText("");
        tfName.setText("");
    }

    private void alert1(String id, String name) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Creation of new Wholesaler");
        a.setContentText("You will create a new Wholesaler: \n" +
                "- "+ name +"\n"+
                "- "+ id);
        a.showAndWait();
    }

    private void alert2(Exception e) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("Exception");
        a.setContentText(e.getMessage());
        a.showAndWait();
    }


}
