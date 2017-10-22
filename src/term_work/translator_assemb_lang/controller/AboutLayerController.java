package term_work.translator_assemb_lang.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class AboutLayerController {
    @FXML
    private ImageView image_au;
    private Stage authorStage;

    public void setAuthorStage(Stage authorStage) {
        this.authorStage = authorStage;
    }

    @FXML
    private void handleClose() {
        authorStage.close();
    }

    public AboutLayerController() {
    }
    @FXML
    public void initialize() {
        image_au.setImage(new Image("term_work/translator_assemb_lang/model/res/images/pP3-cy54hvE.jpg"));
    }
}