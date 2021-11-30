/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxqrgenerator.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author RESEARCH_2
 */
public class QRGenerator {

    private static String generatedQR = "";
    private static String errorThrown = "";


    /**
     * Get the name of the generated QR code
     *
     * @return
     */
    public static String getGeneratedQR() {
        return generatedQR;
    }

    /**
     * Get the error generated incase the QR image could not be generated
     *
     * @return Error
     */
    public static String getErrorThrown() {
        return errorThrown;
    }

    /**
     * Generate a QR Code and write it to a local file system as .jpg
     *
     * @param data
     * @return the name of the QR code generated.
     */
    public static boolean generateQRImage(String data) {
        String timePrint = String.valueOf(System.currentTimeMillis());
        ByteArrayOutputStream out = QRCode.from(data).to(ImageType.PNG).stream();
        generatedQR = System.getProperty("java.io.tmpdir")+"/codes/" + timePrint + ".JPG";
        try {
            new File(System.getProperty("java.io.tmpdir")+"/codes").mkdir();
            File f = new File(generatedQR);
            f.createNewFile();
            try (FileOutputStream fout = new FileOutputStream(f)) {
                fout.write(out.toByteArray());
                fout.flush();
                fout.flush();
            }
            return true;
        } catch (FileNotFoundException e) {
            errorThrown += e.getMessage();
        } catch (IOException e) {
            errorThrown += e.getMessage();
        }
        return false;
    }
}
