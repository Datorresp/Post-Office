package controllers;

import dataStructures.ObjInMatrix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.ControllerModel;
import model.Wholesaler;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRoute implements Initializable {

    @FXML
    private ComboBox<Wholesaler> cb1;

    private ControllerMain cm;

    public ControllerRoute(ControllerMain controllerMain) {
        cm = controllerMain;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerModel c = cm.getCm();
        ObservableList<Wholesaler> ol1 = FXCollections.observableArrayList(c.getWholesalers());
        cb1.setItems(ol1);
    }

    @FXML
    void connections() {
        try {
            ControllerModel c = cm.getCm();
            Wholesaler wl = cb1.getValue();
            ObjInMatrix[][] dj = c.dijkstra(wl);
            for (int x=0; x < dj.length; x++) {
                System.out.print("|");
                for (int y=0; y < dj[x].length; y++) {
                    System.out.print (dj[x][y]);
                    if (y!=dj[x].length-1) System.out.print("\t");
                }
                System.out.println("|");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void connections1() {
        try {
            ControllerModel c = cm.getCm();
            double[][] dj = c.floydWarshall();
            for (int x=0; x < dj.length; x++) {
                System.out.print("|");
                for (int y=0; y < dj[x].length; y++) {
                    System.out.print (dj[x][y]);
                    if (y!=dj[x].length-1) System.out.print("\t");
                }
                System.out.println("|");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
