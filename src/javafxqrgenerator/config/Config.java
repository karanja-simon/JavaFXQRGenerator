/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxqrgenerator.config;

import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafxqrgenerator.controller.SettingsController;
import javafxqrgenerator.controller.ToolController;
import javafxqrgenerator.controller.WhyController;

/**
 *
 * @author Karanja
 */
public class Config {

    public void loadAnchorPane(AnchorPane ap, String a) {
        try {
            AnchorPane p = (AnchorPane) FXMLLoader.load(getClass().getResource(a));
            ap.getChildren().setAll(p);

        } catch (IOException e) {

        }
    }

    public void showWhyDialog(Window parent, Node n) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafxqrgenerator/view/Why.fxml"));
            Region root = (Region) fxmlLoader.load();
            WhyController wc = fxmlLoader.<WhyController>getController();
            Stage utilityStage = new Stage();
            UndecoratorScene scene = new UndecoratorScene(utilityStage, StageStyle.UTILITY, root, null);
            scene.setBackgroundOpacity(0.0);
            scene.getStylesheets().add("skin/undecoratorUtilityStage.css");
            utilityStage.setScene(scene);
            utilityStage.initModality(Modality.APPLICATION_MODAL);
            utilityStage.initOwner(parent);

            Undecorator undecorator = scene.getUndecorator();
            root.setOnMouseDragged(null);
            root.setOnMouseClicked(null);
            undecorator.setBackgroundOpacity(0);
            undecorator.setStyle("-fx-background-color: transparent;");
            double width = 427;
            utilityStage.setMinWidth(width);
            utilityStage.setMinHeight(undecorator.getMinHeight());
            utilityStage.setWidth(width);
            utilityStage.setHeight(undecorator.getPrefHeight());
            utilityStage.sizeToScene();
            utilityStage.setResizable(false);

            double[] loc = getNodeLocation(n);
            utilityStage.setX(loc[0] - (utilityStage.getWidth() / 2 - (n.getBoundsInParent().getWidth() / 2)));
            utilityStage.setY(loc[1] - (n.getBoundsInParent().getHeight() / 2 - 2));
            wc.close(utilityStage);
            wc.getPopup().setOnMouseClicked((MouseEvent event) -> {
                utilityStage.close();
            });
            utilityStage.show();
        } catch (IOException ex) {
            System.out.println("DEBUG: " + ex.getMessage());
        }
    }

    public void showSettingsDialog(Window parent, Node n) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafxqrgenerator/view/Settings.fxml"));
            Region root = (Region) fxmlLoader.load();
            SettingsController sc = fxmlLoader.<SettingsController>getController();
            Stage utilityStage = new Stage();
            UndecoratorScene scene = new UndecoratorScene(utilityStage, StageStyle.UTILITY, root, null);
            scene.setBackgroundOpacity(0.0);
            scene.getStylesheets().add("skin/undecoratorUtilityStage.css");
            utilityStage.setScene(scene);
            utilityStage.initModality(Modality.APPLICATION_MODAL);
            utilityStage.initOwner(parent);

            Undecorator undecorator = scene.getUndecorator();
            undecorator.setBackgroundOpacity(0);
            undecorator.setStyle("-fx-background-color: transparent;");
            root.setOnMouseDragged(null);
            root.setOnMouseClicked(null);
            double width = 427;
            utilityStage.setMinWidth(width);
            utilityStage.setMinHeight(undecorator.getMinHeight());
            utilityStage.setWidth(width);
            utilityStage.setHeight(undecorator.getPrefHeight());
            utilityStage.sizeToScene();
            utilityStage.setResizable(false);

            double[] loc = getNodeLocation(n);
            utilityStage.setX(loc[0] - (utilityStage.getWidth() / 2 - 5));
            utilityStage.setY(loc[1] - (n.getBoundsInParent().getHeight() / 2 - 21));
            sc.close(utilityStage);
            utilityStage.show();
        } catch (IOException ex) {
            System.out.println("DEBUG: " + ex.getMessage());
        }
    }

    public void showPopupDialog(Window parent, String s, Node n, String color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafxqrgenerator/view/tool.fxml"));
            Region root = (Region) fxmlLoader.load();
            ToolController uc = fxmlLoader.<ToolController>getController();
            uc.setMessage(s);
            uc.setMessageColor(color);
            Stage utilityStage = new Stage();
            UndecoratorScene scene = new UndecoratorScene(utilityStage, StageStyle.UTILITY, root, null);
            scene.setBackgroundOpacity(0.0);
            scene.getStylesheets().add("skin/undecoratorUtilityStage.css");
            utilityStage.setScene(scene);
            utilityStage.initModality(Modality.APPLICATION_MODAL);
            utilityStage.initOwner(parent);

            Undecorator undecorator = scene.getUndecorator();
            undecorator.setBackgroundOpacity(0);
            undecorator.setStyle("-fx-background-color: transparent;");
            root.setOnMouseDragged(null);
            root.setOnMouseClicked(null);
            int width = s.length() + 5;
            utilityStage.setMinWidth(width);
            utilityStage.setMinHeight(undecorator.getMinHeight());
            utilityStage.setWidth(width);
            utilityStage.setHeight(undecorator.getPrefHeight());
            utilityStage.sizeToScene();
            utilityStage.setResizable(false);

            double[] loc = getNodeLocation(n);
            utilityStage.setX(loc[0]);
            utilityStage.setY(loc[1] - n.getBoundsInParent().getHeight() / 2);

            uc.getPopupPane().setOnMouseClicked((MouseEvent event) -> {
                utilityStage.close();
            });
            utilityStage.show();
        } catch (IOException ex) {
            System.out.println("DEBUG: " + ex.getMessage());
        }
    }

    public void showPopupDialog(Window parent, String s, Button n, String color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafxqrgenerator/view/tool.fxml"));
            Region root = (Region) fxmlLoader.load();
            ToolController uc = fxmlLoader.<ToolController>getController();
            uc.setMessage(s);
            uc.setMessageColor(color);
            Stage utilityStage = new Stage();
            UndecoratorScene scene = new UndecoratorScene(utilityStage, StageStyle.UTILITY, root, null);
            scene.setBackgroundOpacity(0.0);
            scene.getStylesheets().add("skin/undecoratorUtilityStage.css");
            utilityStage.setScene(scene);
            utilityStage.initModality(Modality.APPLICATION_MODAL);
            utilityStage.initOwner(parent);

            Undecorator undecorator = scene.getUndecorator();
            undecorator.setBackgroundOpacity(0);
            undecorator.setStyle("-fx-background-color: transparent;");
            root.setOnMouseDragged(null);
            root.setOnMouseClicked(null);
            int width = s.length() + 5;
            utilityStage.setMinWidth(width);
            utilityStage.setMinHeight(undecorator.getMinHeight());
            utilityStage.setWidth(width);
            utilityStage.setHeight(undecorator.getPrefHeight());
            utilityStage.sizeToScene();
            utilityStage.setResizable(false);

            double[] loc = getNodeLocation(n);
            utilityStage.setX(loc[0] - (n.getWidth() + 20));
            utilityStage.setY(loc[1] - (n.getHeight() * 1.2));

            uc.getPopupPane().setOnMouseClicked((MouseEvent event) -> {
                utilityStage.close();
            });
            utilityStage.show();
        } catch (IOException ex) {
            System.out.println("DEBUG: " + ex.getMessage());
        }
    }

    public double[] getNodeLocation(Node n) {
        double[] location = new double[2];
        Point2D nodeBounds = n.localToScene(0.0, 0.0);
        double posX = nodeBounds.getX() + n.getScene().getX() + n.getScene().getWindow().getX();
        double posY = nodeBounds.getY() + n.getScene().getY() + n.getScene().getWindow().getY();
        location[0] = posX;
        location[1] = posY;
        return location;
    }

}
