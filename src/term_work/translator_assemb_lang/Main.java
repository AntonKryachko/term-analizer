package term_work.translator_assemb_lang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import term_work.translator_assemb_lang.controller.MainFrameController;
import term_work.translator_assemb_lang.controller.RootLayerController;

import java.io.IOException;

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

    public void initRootLayer(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayer.fxml"));
            rootLayer = loader.load();

            Scene scene = new Scene(rootLayer);
            primaryStage.setScene(scene);

            RootLayerController controller = loader.getController();
            controller.setMain(this);

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

}
