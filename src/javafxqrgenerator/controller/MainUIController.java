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
import javafxqrgenerator.config.Config;

/**
 * FXML Controller class
 *
 * @author Karanja
 */
public class MainUIController implements Initializable {

    @FXML
    private AnchorPane apWrapper;
    @FXML
    private Button btnAbout;
    @FXML
    private Button btnBack;

    private final Config config;
    @FXML
    private Button btnSettings;

    public MainUIController() {
        this.config = new Config();
    }

    public void loadGeneratorUI() {
        String resource = "/javafxqrgenerator/view/GeneratorUI.fxml";
        config.loadAnchorPane(apWrapper, resource);
        btnBack.setOpacity(0.0);
    }

    public void loadAboutUI() {
        String resource = "/javafxqrgenerator/view/About.fxml";
        config.loadAnchorPane(apWrapper, resource);
        btnBack.setOpacity(1.0);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadGeneratorUI();
        btnAbout.setOnAction((ActionEvent event) -> {
            loadAboutUI();
        });
        btnBack.setOnAction((ActionEvent event) -> {
            loadGeneratorUI();
        });
        btnSettings.setOnAction((ActionEvent event) -> {
            config.showSettingsDialog(((Button) event.getSource()).getParent().getScene().getWindow(), (Button) event.getSource());
        });
        System.out.println("------Am loading.....");
    }

}
