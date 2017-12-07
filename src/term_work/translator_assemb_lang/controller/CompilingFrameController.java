package term_work.translator_assemb_lang.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import term_work.translator_assemb_lang.Main;
import term_work.translator_assemb_lang.model.*;

import java.util.List;

public class CompilingFrameController {
    private Stage stage;
    private Main main;

    private CompileTextSingleton compileTextSingleton = CompileTextSingleton.getInstance();
    private ObservableList<Result> results = FXCollections.observableArrayList();
    private List<LineWithMnem> lineWithMnems = compileTextSingleton.getLineWithMnems();
    private List<String> variables = compileTextSingleton.getVariables();
    private List<String> specSymbols = compileTextSingleton.getSpecialWords();
    private String shift;
    private int i;

    @FXML
    private TableView<Result> resultTable;
    @FXML
    private TableColumn<Result, String> address;
    @FXML
    private TableColumn<Result, String> objCode;
    @FXML
    private TableColumn<Result, String> code;




    public void perfWithMnemlines(){
        for (LineWithMnem line : lineWithMnems) {
            formObjCode(line);
        }
        i = 0;
    }
    private void formObjCode(LineWithMnem line){
        String operator = line.getOperator();
        String operand_1 = line.getOperand1();
        String operand_2 = line.getOperand2();
        StringBuilder sb = new StringBuilder();

        String objCode = "";
        try{
            objCode = Store.binToHex(getOperandsObjCode(operator,operand_1, operand_2));
        }catch (Exception e){
            e.printStackTrace();
        }
        String address = "07" + (Integer.parseInt(shift) + i);
        i += objCode.length() / 2;
        String code;
        if(!operand_2.equalsIgnoreCase("")){
            code = sb.append(operator).append(" ").append(operand_1).append(", ").append(operand_2).toString();
        }else {
            code = sb.append(operator).append(" ").append(operand_1).toString();
        }
        results.add(new Result(address,objCode, code));
    }
    private String getOperandsObjCode(String operator, String operand_1, String operand_2){
        operand_1 = normalizeOperand(operand_1);
        operand_2 = normalizeOperand(operand_2);
        String COP = "";
        String W = "";
        String D = "";
        String REG = "";
        String MOD = "";
        String RM = "";
        String DATA_LOW = "";
        String DATA_HIGH = "";
        String DISP_LOW = "";
        String DISP_HIGH = "";
        String W1 = "";
        String W2 = "";
        String opd1mod00 = findInMatrix(Store.getMod00(), operand_1);
        String opd2mod00 = findInMatrix(Store.getMod00(), operand_2);
        String opd1mod11 = findInMatrix(Store.getMod11(), operand_1);
        String opd2mod11 = findInMatrix(Store.getMod11(), operand_2);
        String db = Store.getValFromList(List.of(Store.getSpecWordToVar()), "DB");
        String dw = Store.getValFromList(List.of(Store.getSpecWordToVar()), "DW");



        if(!opd1mod11.equalsIgnoreCase("")){
            MOD = "11";
            D = "1";
            W1 = opd1mod11.substring(0,1);
            REG = opd1mod11.substring(1);
            if(!opd2mod11.equalsIgnoreCase("")){
                W2 = opd2mod11.substring(0, 1);
                RM = opd2mod11.substring(1);
            } else if(!opd2mod00.equalsIgnoreCase("")){
                RM = opd2mod00.substring(0, opd2mod00.length() - 2);
                MOD = opd2mod00.substring(opd2mod00.length() - 2);
            }else {
                try{
                    RM = opd1mod11.substring(1);
                    String str = String.format("%s", Integer.toHexString( 0x10000 | Integer.parseInt(operand_2)).substring(1));
                    if(W1.equals("1")){
                        DATA_LOW = String.format("%s", Integer.toBinaryString(100000000 | Integer.parseInt(str.substring(2), 16)).substring(1)).substring(18);
                        DATA_HIGH = String.format("%s", Integer.toBinaryString(100000000 | Integer.parseInt(str.substring(0,2), 16)).substring(1)).substring(18);
                    }else {
                        DATA_LOW = String.format("%s", Integer.toBinaryString(100000000 | Integer.parseInt(str.substring(2), 16)).substring(1)).substring(18);
                    }

                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        }else if(!opd2mod11.equalsIgnoreCase("")){
            D = "0";
            MOD = "11";
            W2 = opd2mod11.substring(0,1);
            REG = opd2mod11.substring(1);
            if(!opd1mod11.equalsIgnoreCase("")){
                W1 = opd1mod11.substring(0, 1);
                RM = opd1mod11.substring(1);
            } else if(!opd1mod00.equalsIgnoreCase("")){
                RM = opd1mod00.substring(0, opd1mod00.length() - 2);
                MOD = opd1mod00.substring(opd1mod00.length() - 2);
            }
        }

        if ((W1.equals(W2) || (W2.equals(W1))) && !W1.equals("")){
            W = W1;
        }else if(W1.equals("")){
            W = W2;
        }else W = W1;

        String s = "";
        if(DATA_LOW.equals("")){
            switch (operator){
                case "MOV": COP = MOV(1);
                    s = add(COP,D,W,"",MOD,REG,RM,"","","","");
                    break;
                case "ADD": COP = ADD(1);
                    s = add(COP,D,W,"",MOD,REG,RM,"","","","");
                    break;
                case "SAR": COP = SAR(1);
                    if(RM.equalsIgnoreCase("001")){
                        s = add(COP,"","1",W,MOD,"111",REG,
                                "", "","","");
                    }
                    break;
                case "JZ": COP = JZ(1);
                    DISP_HIGH = "0000";
                    DISP_LOW = "0000";
                    s = add(COP,"","","","","","","","", DISP_LOW, DISP_HIGH);
                    break;
                default:
                    new AlertData(
                            stage,
                            "Операции нет",
                            "Такой операции нету",
                            "Проверьте правильность написания",
                            "ERROR"
                    );
            }
        }else {
            switch (operator){
                case "MOV": COP = MOV(3);
                    s = add(COP, "", W,"","", REG,"",DATA_LOW, DATA_HIGH,"","");
                    break;
                case "ADD":
                    if(RM.equalsIgnoreCase("000")) {
                        COP = ADD(2);
                        s = add(COP, "", W, "", "", "", "", DATA_LOW, DATA_HIGH, "", "");
                    }else {
                        COP = ADD(3);
                        s = add(COP, "0", W,"",MOD, "000",RM,DATA_LOW, DATA_HIGH,"","");
                    }
                    break;
                case "SAR":COP = SAR(1);
                    s = add(COP,"","0",W,MOD,"111",REG,
                            "", "","","");
                    break;
                default:
                    new AlertData(
                            stage,
                            "Операции нет",
                            "Такой операции нету",
                            "Проверьте правильность написания",
                            "ERROR"
                    );
            }
        }
        return s;
    }

    private String add (
            String COP,
            String D,
            String C,
            String W,
            String MOD,
            String REG,
            String RM,
            String DATA_LOW,
            String DATA_HIGH,
            String DISP_LOW,
            String DISP_HIGH
    ){
        StringBuilder sb = new StringBuilder();
        return sb.append(COP)
                .append(D)
                .append(C)
                .append(W)
                .append(MOD)
                .append(REG)
                .append(RM)
                .append(DATA_LOW)
                .append(DATA_HIGH)
                .append(DISP_LOW)
                .append(DISP_HIGH).toString();
    }
    private String normalizeOperand(String operand){
        if(operand.equals("")) return "";
        if(!operand.contains("+")) return operand;
        String[] smas = operand.split("[+]");
        if(smas.length > 2) {
            new AlertData(
                    stage,
                    "Много плюсов",
                    "Не предусмотренная ошибка",
                    "Данный язык не предназначен для такого количетсва плюсов в строке" + i,
                    "ERROR"
            );
        }
        for (int i = 0; i < smas.length; i++){
            smas[i] = smas[i].replaceAll("[\\[\\]]","");
        }
        return "[" + smas[0] + "]+[" + smas[1] + "]";
    }
    private String MOV(int op){
        switch (op){
            case 1: return Store.getDoings()[0][1];
            case 2: return Store.getDoings()[0][2];
            case 3: return Store.getDoings()[0][3];
            case 4: return Store.getDoings()[0][4];
            default:
                System.out.println("ERROR");
        }
        return null;
    }
    private String ADD(int op){
        switch (op){
            case 1: return Store.getDoings()[1][1];
            case 2: return Store.getDoings()[1][2];
            case 3: return Store.getDoings()[1][3];
            default:
                System.out.println("ERROR");
        }
        return null;
    }
    private String SAR(int op){
        switch (op){
            case 1: return Store.getDoings()[2][1];
            default:
                System.out.println("ERROR");
        }
        return null;
    }
    private String JZ(int op){
        switch (op){
            case 1: return Store.getDoings()[3][1];
            default:
                System.out.println("ERROR");
        }
        return null;
    }
    private String findInMatrix(String[][] table, String value) {
        StringBuilder sb = new StringBuilder();
        int x = -1, y = -1;
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (table[i][j].equalsIgnoreCase(value)) {
                    x = i;
                    y = j;
                }
            }
        }
        try {
            sb.append(table[x][0]).append(table[0][y]);
        }catch (IndexOutOfBoundsException e){/*NOP*/}
        return sb.toString();
    }
    public void setShift(String shift) {
        if(shift == null){
            this.shift = "0";
        }else {
            this.shift = shift.substring(3, shift.length() - 1);
        }

    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setMain(Main main){
        this.main = main;
    }
    @FXML
    public void initialize(){
        textValidator();
        resultTable.setItems(results);
        address.setCellValueFactory(cell -> cell.getValue().adressProperty());
        objCode.setCellValueFactory(cell -> cell.getValue().objectCodeProperty());
        code.setCellValueFactory(cell -> cell.getValue().progCodeProperty());
    }

    private void textValidator(){
        int i = 0;
        for (String s:specSymbols) {
                if(s.contains("SEGMENT")){
                    i++;
                }
                if(s.contains("ASSUME")){
                    i++;
                }
                if(s.contains("ENDS")){
                    i++;
                }
                if(s.contains("END") && !s.contains("ENDS")){
                    i++;
                }
                if(s.contains("ORG")){
                    i++;
                }
                if(s.contains("INT")){
                    i++;
                }
        }
        System.out.println(i);
        if(i < Store.getSpec().length){
            new AlertData(
                    stage,
                    "Неправильный синтаксис",
                    "Правильно введите программу",
                    "Не хватает некоторых элементов,\n смотреть листинг",
                    "ERROR"
            );
            results.clear();
        }else{
            setShift(Store.getValFromList(compileTextSingleton.getSpecialWords(), "ORG"));
            perfWithMnemlines();
        }

    }
}
