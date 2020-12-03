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
    @FXML
    private ComboBox<String> cb2;

    private ObservableList<String> ol2 = FXCollections.observableArrayList("Dijkstra", "Floyd Warshall", "Prim", "Kruskal", "DFS", "BFS");

    private ControllerMain cm;

    public ControllerRoute(ControllerMain controllerMain) {
        cm = controllerMain;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerModel c = cm.getCm();
        ObservableList<Wholesaler> ol1 = FXCollections.observableArrayList(c.getWholesalers());
        cb1.setItems(ol1);
        cb2.setItems(ol2);
    }

    @FXML
    void change() {
        String op = cb2.getValue();
        if (op.equals("Dijkstra")){
            cb1.setDisable(false);
        }else {
            cb1.setDisable(true);
        }
    }

    @FXML
    void doit() {
        String op = cb2.getValue();
        switch (op) {
            case "Dijkstra":
                connections();
                break;
            case "Floyd Warshall":
                connections1();
                break;
            case "Prim":
                connections2();
                break;
            case "Kruskal":
                connections3();
                break;
            case "DFS":
                connections4();
                break;
            case "BFS":
                connections5();
                break;
        }
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

    @FXML
    void connections2() {
        try {
            ControllerModel c = cm.getCm();
            int[] dj = c.prim();
            for (int i = 1; i < dj.length; i++) {
                System.out.println(dj[i] + " - " + i);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void connections3() {
        try {
            ControllerModel c = cm.getCm();
            System.out.println(c.kruskalMST());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void connections4() {
        try {
            ControllerModel c = cm.getCm();
            c.dfs();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void connections5() {
        try{
            ControllerModel c = cm.getCm();
            System.out.println(c.bfs(0));
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
