/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxqrgenerator;

import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafxqrgenerator.utils.UITweak;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class JavaFXQRGenerator extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/javafxqrgenerator/view/MainUI.fxml"));
        //final UndecoratorScene undecorator = new UndecoratorScene(stage, (Region) root);
        final Undecorator undecorator = new Undecorator(stage, (Region) root);
        // Customize it by CSS if needed:
        undecorator.getStylesheets().add("/skin/undecorator.css");

        // Optional: Enable this node to drag the stage
        // By default the root argument of Undecorator is set as draggable
        //Node node = root.lookup("#draggableNode");
        //undecorator.setAsStageDraggable(stage, node);

        Scene scene = new Scene(undecorator);

        // Install default Accelerators in the Scene
        //undecorator.i
        // Enable fade transition
        //undecorator.setFadeInTransition();
        /*
         * Fade transition on window closing request
         */
//        stage.setOnCloseRequest((WindowEvent we) -> {
//            we.consume();   // Do not hide
//            undecorator.setFadeOutTransition();
//        });

        // Transparent scene and stage
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        // Set minimum size
        stage.setMinWidth(undecorator.getMinWidth());
        stage.setMinHeight(undecorator.getMinHeight());
        stage.setMaxWidth(undecorator.getMaxWidth());
        stage.setMaxHeight(undecorator.getMaxHeight());
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UITweak.setUIFont();
        launch(args);
    }
    
}
