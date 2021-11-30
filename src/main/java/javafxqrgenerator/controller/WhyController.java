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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karanja
 */
public class WhyController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private AnchorPane apPopup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void close(Stage s) {
        btnClose.setOnAction((ActionEvent event) -> {
            s.close();
        });

    }

    public AnchorPane getPopup() {
        return apPopup;
    }

}
