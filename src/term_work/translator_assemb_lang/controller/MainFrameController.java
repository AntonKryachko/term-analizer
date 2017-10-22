package term_work.translator_assemb_lang.controller;

import javafx.fxml.FXML;
import term_work.translator_assemb_lang.Main;

public class MainFrameController {
    private Main main;
    public void setMain(Main main){
        this.main = main;
    }
    @FXML
    private void handleAbout(){
        main.showAboutAuthor();
    }
    public MainFrameController(){}
    @FXML
    public void initialize(){}
}
