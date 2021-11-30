/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxqrgenerator.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ControllerUtility {
@FXML
private Label lbMessage;

public void setMessage(String msg){
    lbMessage.setText(msg);
}
}
