/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxqrgenerator.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ToolController implements Initializable {
    
    @FXML
    private AnchorPane apPopup;
    @FXML
    private AnchorPane apArrow;
    @FXML
    private Label lbMsg;
    
    public void setMessage(String msg) {
        lbMsg.setText(msg);
    }
    
    public AnchorPane getPopupPane() {
        return apPopup;
    }

    public void setMessageColor(String color) {
        apPopup.setStyle("-fx-background-color: " + color + ";");
        apArrow.setStyle("-fx-background-color: " + color + ";");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
