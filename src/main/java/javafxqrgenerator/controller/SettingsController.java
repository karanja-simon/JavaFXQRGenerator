/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxqrgenerator.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafxqrgenerator.utils.Settings;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class SettingsController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private RadioButton rbInfinite;
    @FXML
    private RadioButton rbCustom;
    @FXML
    private TextField tfHistorySize;
    @FXML
    private Button btnOk;
    private final ToggleGroup tg = new ToggleGroup();
    @FXML
    private RadioButton tbSortDate;
    @FXML
    private ToggleGroup tgSort;
    @FXML
    private RadioButton tbSortSize;
    @FXML
    private Label lblImages;
    @FXML
    private Label lbMsg;

    private final Settings preference = new Settings();
    private int historySize = 0;
    private String sortBy = "date";
    private boolean save = true;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setControlStatus(false);
        lbMsg.setVisible(false);
        tg.getToggles().add(rbCustom);
        tg.getToggles().add(rbInfinite);
        setUserPrefs();
        rbCustom.setOnAction((ActionEvent event) -> {
            setControlStatus(true);
        });
        rbInfinite.setOnAction((ActionEvent event) -> {
            setControlStatus(false);
        });
        btnOk.setOnAction((ActionEvent event) -> {
            if (rbCustom.isSelected()) {
                if (validateNumber(tfHistorySize.getText())) {
                    save = true;
                } else {
                    save = false;
                }
            }else{
                historySize = 0;
                save = true;
            }
            if (tbSortSize.isSelected()) {
                sortBy = "size";
            }
            if (save) {
                preference.setHistorySize(historySize);
                preference.setSortOrder(sortBy);
                lbMsg.setVisible(true);
                lbMsg.setText("Saved. Restart the App for Settings to take Effect");
            }
        });
    }

    public void setUserPrefs() {
        if (preference.getHistorySize() > 0) {
            setControlStatus(true);
            tfHistorySize.setText(String.valueOf(preference.getHistorySize()));
            rbCustom.setSelected(true);
        } else {
            rbInfinite.setSelected(true);
            setControlStatus(false);
        }
        if (preference.getSortOrder().equalsIgnoreCase("date")) {
            tbSortDate.setSelected(true);
        } else {
            tbSortSize.setSelected(true);
        }
    }

    public void setControlStatus(boolean status) {
        tfHistorySize.setVisible(status);
        lblImages.setVisible(status);
    }

    public void close(Stage s) {
        btnClose.setOnAction((ActionEvent event) -> {
            s.close();
        });

    }

    private boolean validateNumber(String input) {
        int number = 0;
        try {
            number = Integer.parseInt(input);
            tfHistorySize.setStyle("-fx-border-color: black;");
        } catch (NumberFormatException nfe) {
            lbMsg.setVisible(true);
            lbMsg.setText("Ensure you enter a valid history size.");
            tfHistorySize.setStyle("-fx-border-color: red;");
            return false;
        }
        historySize = number;
        return true;
    }

}
