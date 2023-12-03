package rabproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ApplyinhighcourtController implements Initializable {

    @FXML
    private TextField caseid;
    @FXML
    private TextField casetitle;
    @FXML
    private TextField casedetails;
    @FXML
    private TextField time;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<ApplyinhighercourtDetails> ApplyinhighercourtDetailsList = FXCollections.observableArrayList();

    }

    @FXML
    private void applyforhighercourtonclicl(ActionEvent event) {
        // Validate input before creating ApplyinhighercourtDetails instance
        String caseIdText = caseid.getText();

        if (isValidCaseId(caseIdText)) {
            try {
                ApplyinhighercourtDetails ahcd = new ApplyinhighercourtDetails(
                        Integer.parseInt(caseIdText),
                        date.getValue(),
                        casetitle.getText(),
                        casedetails.getText(),
                        time.getText()
                );
                FileOutputStream fos = null;
                ObjectOutputStream oos = null;
                File f = null;
                try {
                    f = new File("Applyinhighercourt.bin");
                    if (f.exists()) {
                        fos = new FileOutputStream(f, true);
                        oos = new AppendableObjectOutputStream(fos);
                    } else {
                        fos = new FileOutputStream(f);
                        oos = new ObjectOutputStream(fos);
                    }

                    oos.writeObject(ahcd);
                    System.out.println("write object sucessfull ");

                } catch (IOException ex) {
                    Logger.getLogger(ApplyinhighcourtController .class
                            .getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (oos != null) {
                            oos.close();

                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ApplyinhighcourtController .class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Applied in HigherCourt");

                // Show the alert
                alert.showAndWait();

                // Use the ahcd instance as needed
                System.out.println("Apply in Higher Court Details submitted: " + ahcd);
            } catch (NumberFormatException e) {
                System.out.println("Error parsing Case ID to integer.");
            }
        } else {
            System.out.println("Invalid Case ID. Please enter a non-empty numeric value.");
        }

    }

    private boolean isValidCaseId(String caseIdText) {
        return !caseIdText.isEmpty() && caseIdText.matches("\\d+");

    }
}
