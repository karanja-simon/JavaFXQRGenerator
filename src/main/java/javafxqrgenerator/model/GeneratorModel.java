/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxqrgenerator.model;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javafxqrgenerator.utils.QRGenerator;
import javafxqrgenerator.utils.Settings;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author RESEARCH_2
 */
public class GeneratorModel {

    private String QRData;
    private String QRImage;
    private final ArrayList<File> QRImagesList;
    private final Settings preference = new Settings();

    public GeneratorModel() {
        this.QRImagesList = new ArrayList<>();
    }

    public String getQRImage() {
        return QRImage;
    }

    public String getQRData() {
        return QRData;
    }

    public void setQRData(String data) {
        this.QRData = data;
    }

    @Override
    public String toString() {
        return "GeneratorModel{" + "data=" + QRData + '}';
    }

    public void generateQRCode() {
        System.out.println(toString());
        if (QRGenerator.generateQRImage(QRData)) {
            this.QRImage = QRGenerator.getGeneratedQR();
        }
    }

    public String getQRDecodedData(File f) {
        try {
            BufferedImage image = ImageIO.read(f);
            LuminanceSource source = new BufferedImageLuminanceSource(image);

            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            Result result = reader.decode(bitmap);
            return result.getText();
        } catch (IOException | NotFoundException | ChecksumException | FormatException e) {
            System.out.println("Error reading QR: " + e.getMessage());
        }
        return null;
    }

    public void exportQRImage(String destPath) {
        File dest = new File(destPath);
        try {
            if (!destPath.endsWith(".jpg")) {
                dest = new File(destPath + ".jpg");
            }
            FileUtils.copyFile(new File(QRImage), dest);
            QRImage = dest.getAbsolutePath();
        } catch (IOException iOException) {
            System.out.println(iOException);
        }
    }

    public void exportQRImageFromRecent(String destPath, File f) {
        File dest = new File(destPath);
        try {
            if (!destPath.endsWith(".jpg")) {
                dest = new File(destPath + ".jpg");
            }
            FileUtils.copyFile(f, dest);
            QRImage = dest.getAbsolutePath();
        } catch (IOException iOException) {
            System.out.println(iOException);
        }
    }

    public ArrayList<File> getQRImagesList() {
        return QRImagesList;
    }

    /**
     * Load articles from articles directory and add them to articles list The
     * articles have timestamp as their file names with a matching timestamp on
     * the images folder to match the article with its associated image
     *
     * @return
     */
    public boolean loadAutoSavedQRImages() {
        File folder = new File("codes/");
        File[] listOfFiles = folder.listFiles();
        int historySize = listOfFiles.length;
        if (preference.getHistorySize() > 0) {
            if (preference.getHistorySize() < historySize) {
                historySize = preference.getHistorySize();
            }

        }

        for (int i = 0; i < historySize; i++) {
            if (listOfFiles[i].isFile()) {
                QRImagesList.add(listOfFiles[i]);

            }
        }

        return true;

    }

    public void initDevCode() throws Exception {
        InputStream input = getClass().getResourceAsStream("/javafxqrgenerator/dev/devqr.jpg");
        Boolean makeDir = new File("codes").mkdir();
        OutputStream outputStream = new FileOutputStream(new File("codes/1435955064074.JPG"));
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = input.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        System.out.println("Done writting...");
    }
}
