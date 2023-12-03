/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainPkg;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anika
 */
public class U1_OperationBudgetFXMLController implements Initializable {

    @FXML
    private PieChart budgetPieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        FileInputStream fis = null;
        DataInputStream dis = null;

        try {

            File f = new File("Budget.bin");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);

            if (f.exists()) {

                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

                while (dis.available() > 0) {

                    String SavedZone, SavedBranch;

                    int budget;
                    String operationtype, operationPlace;

                    budget = dis.readInt();
                    operationtype = dis.readUTF();
                    operationPlace = dis.readUTF();

                    if (operationtype.equals("Terrorism")) {
                        PieChart.Data data = new PieChart.Data(operationPlace, budget);

                        pieChartData.add(data);

                        data.nameProperty().bindBidirectional(new SimpleStringProperty(data.getName() + ": " + data.getPieValue()));
                    }

                }

                 budgetPieChart.setData(pieChartData);

            } else {

            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    @FXML
    private void returnButtonOnClick(ActionEvent event) throws IOException {
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("U1_BudgetAllocationFXML.fxml"));
        root = (Parent) someLoader.load();
        Scene someScene = new Scene(root);

        Stage someStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
    }

}
