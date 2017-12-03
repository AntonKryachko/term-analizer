package term_work.translator_assemb_lang.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import term_work.translator_assemb_lang.Main;
import term_work.translator_assemb_lang.model.CreateObjectiveCodeSingleton;
import term_work.translator_assemb_lang.model.Result;

public class CompilingFrameController {
    private Stage stage;
    private Main main;
    private CreateObjectiveCodeSingleton codeSingleton = CreateObjectiveCodeSingleton.getInstance();

    @FXML
    private TableView<Result> resultTable;
    @FXML
    private TableColumn<Result, String> address;
    @FXML
    private TableColumn<Result, String> objCode;
    @FXML
    private TableColumn<Result, String> code;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setMain(Main main){
        this.main = main;
    }
    @FXML
    public void initialize(){
        resultTable.setItems(codeSingleton.getResults());
        address.setCellValueFactory(cell -> cell.getValue().adressProperty());
        objCode.setCellValueFactory(cell -> cell.getValue().objectCodeProperty());
        code.setCellValueFactory(cell -> cell.getValue().progCodeProperty());
    }
}
