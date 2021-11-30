/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxqrgenerator.controller;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafxqrgenerator.animation.MyAnimation;
import javafxqrgenerator.config.Config;
import javafxqrgenerator.model.GeneratorModel;

/**
 * FXML Controller class
 *
 * @author Karanja
 */
public class GeneratorUIController implements Initializable {
    
    @FXML
    private AnchorPane apWrapper;
    @FXML
    private ImageView ivPreview;
    @FXML
    private Label lbSize;
    @FXML
    private Label lbStatus;
    @FXML
    private Label lbDateGenerated;
    @FXML
    private Label lbDimension;
    @FXML
    private Label lbDecodedQRData;
    @FXML
    private Label lbExportOpt;
    @FXML
    private Button btnExportFromRecent;
    @FXML
    private Button btnExport;
    @FXML
    private ProgressBar pbGenerateProgress;
    @FXML
    private Button btnGenerate;
    @FXML
    private TextArea taQRData;
    @FXML
    private ImageView ivGeneratedCode;
    @FXML
    private ListView<String> lvRecent;
    @FXML
    private ProgressIndicator piLoadImages;
    @FXML
    private Hyperlink hlExplain;
    
    private ObservableList<String> recentImages;
    private final ArrayList<String> recentImagesName = new ArrayList<>();
    private final SimpleDateFormat sdf;
    private final GeneratorModel model;
    private final MyAnimation animation = new MyAnimation();
    private final int animationTimeOut = 5000;
    private int selectedFileIndex = 0;
    private final Config config;
    
    public GeneratorUIController() {
        this.sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        this.model = new GeneratorModel();
        this.config = new Config();
        try {
            model.initDevCode();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    private void loadImagesFromAutoSave() {
        if (model.getQRImagesList().size() > 0) {
            for (File f : model.getQRImagesList()) {
                recentImagesName.add(sdf.format(f.lastModified()));
            }
            recentImages = FXCollections.observableArrayList(recentImagesName);
            lvRecent.setItems(recentImages);
        }
    }
    
    public File getSelectedFile(int index) {
        return model.getQRImagesList().get(index);
    }
    
    public void updatePreview(File f) {
        if (f.isFile()) {
            double bytes = f.length();
            double kilobytes = (bytes / 1024);
            lbDateGenerated.setText(sdf.format(f.lastModified()));
            lbSize.setText(kilobytes + "Kb");
            Image preview = new Image(f.toURI().toString());
            int w = (int) preview.getWidth();
            int h = (int) preview.getHeight();
            lbDimension.setText(w + " x " + h + " px");
            ivPreview.setImage(preview);
            lbDecodedQRData.setText(model.getQRDecodedData(f));
        }
        
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivGeneratedCode.setOpacity(0.0);
        btnExport.setOpacity(0.0);
        lbExportOpt.setOpacity(0.0);
        QRGeneratorBot qrgb = new QRGeneratorBot();
        ImageLoaderBot ilb = new ImageLoaderBot();
        ilb.start();
        ilb.setOnSucceeded((WorkerStateEvent event) -> {
            piLoadImages.setOpacity(0.0);
            loadImagesFromAutoSave();
        });
        lvRecent.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            selectedFileIndex = lvRecent.getSelectionModel().getSelectedIndex();
            File f = getSelectedFile(selectedFileIndex);
            updatePreview(f);
        });
        qrgb.setOnSucceeded((WorkerStateEvent event) -> {
            File file = new File(model.getQRImage());
            Image generatedImage = new Image(file.toURI().toString());
            ivGeneratedCode.setImage(generatedImage);
            ivGeneratedCode.setOpacity(0.0);
            animation.fadeOut(ivGeneratedCode, animationTimeOut).play();
            animation.fadeOut(lbExportOpt, animationTimeOut).play();
            recentImages.add(sdf.format(file.lastModified()));
            lbStatus.setText("Generated QR Preview");
            animation.fadeOut(btnExport, animationTimeOut).play();
            qrgb.reset();
            
        });
        pbGenerateProgress.progressProperty().bind(qrgb.progressProperty());
        btnGenerate.setOnAction((ActionEvent event) -> {
            if (taQRData.getText().isEmpty()) {
                config.showPopupDialog(((Button) event.getSource()).getParent().getScene().getWindow(), "Ensure you have entered QR data", taQRData, "red");
            } else {
                ivGeneratedCode.setOpacity(1.0);
                lbStatus.setText("Generating QR Image. Hold on..");
                Image loaderImage = new Image(getClass().getResourceAsStream("/javafxqrgenerator/resources/windows.gif"));
                ivGeneratedCode.setImage(loaderImage);
                qrgb.start();
            }
        });
        btnExport.setOnAction((ActionEvent event) -> {
            exportQRImage(event);
            
        });
        btnExportFromRecent.setOnAction((ActionEvent event) -> {
            exportQRImageFromRecent(event);
        });
        hlExplain.setOnAction((ActionEvent event) -> {
          config.showWhyDialog(((Hyperlink) event.getSource()).getParent().getScene().getWindow(), (Hyperlink) event.getSource());
        });

        
    }

    private void exportQRImageFromRecent(ActionEvent event) {
        final FileChooser saveQRImage = new FileChooser();
        File file = getSelectedFile(selectedFileIndex);
        saveQRImage.setInitialFileName(getSelectedFile(selectedFileIndex).getName());
        saveQRImage.setTitle("Export QRCode Image From Recent");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png)", "*.png");
        saveQRImage.getExtensionFilters().add(extFilter);
        File f = saveQRImage.showSaveDialog(((Button) event.getSource()).getParent().getScene().getWindow());
        String QRPath = f.getAbsolutePath();
        model.exportQRImageFromRecent(QRPath, file);
        config.showPopupDialog(((Button) event.getSource()).getParent().getScene().getWindow(), "QR exported to: " + QRPath, (Button) event.getSource(), "green");
    }

    private void exportQRImage(ActionEvent event) {
        final FileChooser saveQRImage = new FileChooser();
        saveQRImage.setInitialFileName(getSelectedFile(selectedFileIndex).getName());
        saveQRImage.setTitle("Export QRCode Image File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.JPG)", "*.JPG");
        saveQRImage.getExtensionFilters().add(extFilter);
        File f = saveQRImage.showSaveDialog(((Button) event.getSource()).getParent().getScene().getWindow());
        String QRPath = f.getAbsolutePath();
        model.exportQRImage(QRPath);
        config.showPopupDialog(((Button) event.getSource()).getParent().getScene().getWindow(), "QR exported to: " + QRPath, (Button) event.getSource(), "green");
    }
    
    private void generate() {
        model.setQRData(taQRData.getText());
        model.generateQRCode();
        if (!model.getQRImage().isEmpty()) {
        }
        
    }
    
    private class QRGeneratorBot extends Service<Integer> {
        
        private boolean generating = true;
        
        @Override
        protected Task<Integer> createTask() {
            return new Task<Integer>() {
                
                @Override
                @SuppressWarnings("SleepWhileInLoop")
                protected Integer call() throws Exception {
                    int workDone = 0;
                    int totalWork = 100;
                    while (generating) {
                        if (workDone == totalWork - 1) {
                            generate();
                            generating = false;
                        }
                        Thread.sleep(100);
                        workDone++;
                        updateProgress(workDone, totalWork);
                    }
                    
                    return workDone;
                }
            };
            
        }
    ;
    
    }
     private class ImageLoaderBot extends Service<Void> {
        
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                
                @Override
                protected Void call() throws Exception {
                    model.loadAutoSavedQRImages();
                    return null;
                    
                }
            };
            
        }
    ;
    
}
}
