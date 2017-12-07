package term_work.translator_assemb_lang.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import term_work.translator_assemb_lang.Main;
import term_work.translator_assemb_lang.model.AlertData;
import term_work.translator_assemb_lang.model.CompileTextSingleton;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MainFrameController {
    @FXML
    private TextArea compile_text;
    private Main main;
    private CompileTextSingleton text = CompileTextSingleton.getInstance();

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
        compile_text.clear();
        main.setFilePath(null);
    }
    @FXML
    private void handleCompile(){
        System.out.println("Compile");
        trimCompile_text();
        if(!compile_text.getText().equalsIgnoreCase("")){
            text.setCompileText(compile_text.getText(),true);
            main.compilingFrame();
            text.clear();
        }else {
            new AlertData(
                    main.getPrimaryStage(),
                    "Введите текст",
                    "Введите код",
                    "Тут пусто",
                    "WARNING"
            );
        }
    }
    @FXML
    private void handleSave(){
        System.out.println("Save");
        File engFile = main.getFilePath();
        if(engFile != null){
            save(engFile);
        }else {
            handleSaveAs();
        }
    }
    @FXML
    private void handleSaveAs(){
        System.out.println("Save as");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TASM files (*.asm)", "*.asm",
                "All files", "*.*"
        );
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
        if(file != null){
            if(!file.getPath().endsWith(".asm")){
                file = new File(file.getPath() + ".asm");
            }
            save(file);
        }
    }
    private void save(File file){
        try{
            Files.write(Paths.get(file.getPath()),compile_text.getText().getBytes());
            main.setFilePath(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void handleOpen(){
        System.out.println("Open");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "TASM files (*.asm)","*.asm",
                "All files", "*.*"
        );
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        if(file != null){
            StringBuilder sb = new StringBuilder();
            try {
                Files.lines(Paths.get(file.getPath()), Charset.defaultCharset()).forEach(l -> sb.append(l).append("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            compile_text.insertText(0, sb.toString());
            main.setFilePath(file);
        }
    }
    public MainFrameController(){}
    @FXML
    public void initialize(){
    }

    private void trimCompile_text(){
        compile_text.setText(compile_text.getText().trim());
    }
}
