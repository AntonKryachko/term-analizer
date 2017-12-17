package term_work.translator_assemb_lang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import term_work.translator_assemb_lang.controller.AboutLayerController;
import term_work.translator_assemb_lang.controller.CompilingFrameController;
import term_work.translator_assemb_lang.controller.MainFrameController;
import term_work.translator_assemb_lang.controller.RootLayerController;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayer;
    private final String NAME_APP = "Translator Assembler";
    private final String KEY_FILE = "filePath";

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle(NAME_APP);
        primaryStage.setResizable(false);
        this.primaryStage = primaryStage;

        initRootLayer();
        showMainFrame();
    }

    private void initRootLayer(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayer.fxml"));
            rootLayer = loader.load();

            Scene scene = new Scene(rootLayer);
            primaryStage.setScene(scene);

            RootLayerController controller = loader.getController();

            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void showMainFrame(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MainFrame.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayer.setCenter(mainPage);

            MainFrameController controller = loader.getController();
            controller.setMain(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void compilingFrame(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/CompilingFrame.fxml"));
            AnchorPane pane = loader.load();

            Stage compilingStage = new Stage();
            compilingStage.setTitle("Result");
            compilingStage.initModality(Modality.WINDOW_MODAL);
            compilingStage.initOwner(primaryStage);
            compilingStage.setResizable(true);

            Scene scene = new Scene(pane);
            compilingStage.setScene(scene);

            CompilingFrameController controller = loader.getController();
            controller.setMain(this);
            controller.setStage(compilingStage);

            compilingStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showAboutAuthor(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/AboutLayer.fxml"));
            AnchorPane pane = loader.load();

            Stage authorStage = new Stage();
            authorStage.setTitle("About author");
            authorStage.initModality(Modality.WINDOW_MODAL);
            authorStage.initOwner(primaryStage);
            authorStage.setResizable(false);

            Scene scene = new Scene(pane);
            authorStage.setScene(scene);

            AboutLayerController controller = loader.getController();
            controller.setAuthorStage(authorStage);

            authorStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public File getFilePath(){
        Preferences preferences = Preferences.userNodeForPackage(getClass());
        String filePath = preferences.get(KEY_FILE, null);
        if(filePath != null){
            return new File(filePath);
        }else {
            return null;
        }
    }
    public void setFilePath(File filePath){
        Preferences preferences = Preferences.userNodeForPackage(getClass());
        if(filePath != null) {
            preferences.put(KEY_FILE, filePath.getPath());
            primaryStage.setTitle(NAME_APP + " \"" + filePath.getName() + "\"");
        }else{
            preferences.remove(KEY_FILE);
            primaryStage.setTitle(NAME_APP);
        }
    }
}
