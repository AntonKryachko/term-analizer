package term_work.translator_assemb_lang.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import term_work.translator_assemb_lang.Main;
import term_work.translator_assemb_lang.model.CompileTextSingleton;
import term_work.translator_assemb_lang.model.CreateObjectiveCodeSingleton;


public class MainFrameController {
    @FXML
    private TextArea compile_text;
    private Main main;
    private CompileTextSingleton text = CompileTextSingleton.getInstance();
    private CreateObjectiveCodeSingleton code = CreateObjectiveCodeSingleton.getInstance();

    public void setMain(Main main){
        this.main = main;
    }
    @FXML
    private void handleAbout(){
        main.showAboutAuthor();
    }
    @FXML
    private void handleNew(){
        System.out.println("New");
    }
    @FXML
    private void handleCompile(){
        System.out.println("Compile");
        trimCompile_text();
        text.setCompileText(compile_text.getText());
        code.perfWithMnemlines();
        main.compilingFrame();
    }
    @FXML
    private void handleSave(){
        System.out.println("Save");
    }
    @FXML
    private void handleOpen(){
        System.out.println("Open");
    }
    public MainFrameController(){}
    @FXML
    public void initialize(){}

    private void trimCompile_text(){
        compile_text.setText(compile_text.getText().trim());
    }
}
