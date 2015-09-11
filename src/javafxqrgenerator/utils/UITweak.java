/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxqrgenerator.utils;

import javafx.scene.text.Font;
import javafxqrgenerator.JavaFXQRGenerator;

/**
 *
 * @author RESEARCH_2
 */
public class UITweak {

    public static void setUIFont() {
        Font.loadFont(JavaFXQRGenerator.class.getResourceAsStream("/javafxqrgenerator/fonts/SEGOEUISL.TTF"), 10);
    }
}
