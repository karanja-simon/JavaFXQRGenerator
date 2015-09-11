/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxqrgenerator.animation;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author Karanja
 */
public class MyAnimation {

    public FadeTransition fadeOut(Node node, int time) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(time), node);
        fadeOut.setFromValue(0.0);
        fadeOut.setToValue(1.0);
        return fadeOut;
    }

    public FadeTransition fadeIn(Node node, int time) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(time), node);
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.0);
        return fadeIn;
    }

    public TranslateTransition slideOut(AnchorPane node, int time) {
        TranslateTransition slide = new TranslateTransition();
        slide.setNode(node);
        slide.setDuration(Duration.millis(time));
        slide.fromXProperty();
        slide.setToX(-node.getWidth() / 1.3);
        slide.toXProperty();
        return slide;
    }

    public TranslateTransition slideIn(AnchorPane node, int time) {
        TranslateTransition slide = new TranslateTransition();
        slide.setNode(node);
        slide.setDuration(Duration.millis(time));
        slide.fromXProperty();
        slide.setToX(0);
        slide.toXProperty();
        return slide;
    }

}
