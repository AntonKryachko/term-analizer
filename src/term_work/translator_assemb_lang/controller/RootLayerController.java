package term_work.translator_assemb_lang.controller;

import javafx.fxml.FXML;
import term_work.translator_assemb_lang.Main;

public class RootLayerController {
    private Main main;

    public void setMain(Main main){
        this.main = main;
    }
    @FXML
    private void handleAbout(){
        main.showAboutAuthor();
    }
    @FXML
    private void handleNew(){
        System.out.println("new");
    }
    @FXML
    private void handleOpen(){
        System.out.println("Open");
    }
    @FXML
    private void handleSave(){
        System.out.println("Save");
    }
    @FXML
    private void handleSaveAs(){
        System.out.println("Save as");
    }
    @FXML
    private void handleCompile(){
        System.out.println("Compile");
    }
    @FXML
    private void handleClose() {
        main.getPrimaryStage().close();
    }
    public RootLayerController(){}
    @FXML
    public void initialize(){}
}
