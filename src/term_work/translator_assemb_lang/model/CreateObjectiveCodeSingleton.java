package term_work.translator_assemb_lang.model;

import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class CreateObjectiveCodeSingleton{

    private static CreateObjectiveCodeSingleton instance;
    private CreateObjectiveCodeSingleton(){}
    public static CreateObjectiveCodeSingleton getInstance() {
        if(instance == null){
            instance = new CreateObjectiveCodeSingleton();
        }
        return instance;
    }

    private CompileTextSingleton compileTextSingleton = CompileTextSingleton.getInstance();
    private List<String> results = new ArrayList<>();
    private List<LineWithMnem> lineWithMnems = compileTextSingleton.getLineWithMnems();

    public void perfWithMnemlines(){
        for (LineWithMnem lineWithMnem: lineWithMnems){
            formObjCode(lineWithMnem);
            System.out.println();
        }
        System.out.println(results.toString());
    }
    private void formObjCode(LineWithMnem line){
        String operator = line.getOperator();
        String operand_1 = line.getOperand1();
        String operand_2 = line.getOperand2();

        System.out.println(operator + " " + operand_1 + ", " + operand_2);

        results.add(getOperandsObjCode(operator,operand_1, operand_2));
    }

    private String getOperandsObjCode(String operator, String operand_1, String operand_2){
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
                    String str = String.format("%s", Integer.toHexString( 0x10000 |Integer.parseInt(operand_2)).substring(1));
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
            }
        }
        System.out.println(s);
        return s;
    }

    public String binToHex(String bin){
        StringBuilder sb = new StringBuilder();
        char[] c = bin.toCharArray();
        for (int i = 0; i < c.length; i+=4) {
            sb.append(Integer.toHexString(Integer.parseInt(bin.substring(i, i + 4), 2)));
        }
        System.out.println(sb.toString());
        return null;
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

    private String MOV(int op){
        switch (op){
            case 1: return Store.getDoings()[0][1];
            case 2: return Store.getDoings()[0][2];
            case 3: return Store.getDoings()[0][3];
            case 4: return Store.getDoings()[0][4];
            default:
                System.out.println("fuck off");
        }
        return null;
    }
    private String ADD(int op){
        switch (op){
            case 1: return Store.getDoings()[1][1];
            case 2: return Store.getDoings()[1][2];
            case 3: return Store.getDoings()[1][3];
            default:
                System.out.println("fuck off");
        }
        return null;
    }
    private String SAR(int op){
        switch (op){
            case 1: return Store.getDoings()[2][1];
            default:
                System.out.println("fuck off");
        }
        return null;
    }
    private String JZ(int op){
        switch (op){
            case 1: return Store.getDoings()[3][1];
            default:
                System.out.println("fuck off");
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
}

