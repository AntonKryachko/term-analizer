package term_work.translator_assemb_lang.model;

class Store {

    private static String[] mn = {"MOV", "ADD", "SAR", "JZ"};
    private static String[] spec = {"SEGMENT", "ASSUME", "END", "ENDS", "ORG", "INT", "DW", "DB"};
    private static String[] specWordToVar = {"DW", "DB"};
    private static String[][] mod11 = {
            {" ", "000", "001", "010", "011", "100", "101", "110", "111"},
            {"0",  "AL",  "CL",  "DL",  "BL",  "AH",  "CH",  "DH", "BH"},
            {"1",  "AX",  "CX",  "DX",  "BX",  "SP",  "BP",  "SI", "DI"}
    };

    private static String[][] doings = {
            {"MOV", "100010", "1100011", "1011", "101000", "10001110", "10001100"},
            {"ADD", "000000", "0000010", "100000", "-", "-", "-"},
            {"SAR", "110100", "-", "-", "-", "-", "-"},
            {"JZ", "01110100","-", "-", "-", "-", "-"}
    };

    public static String[][] getDoings() {
        return doings;
    }

    public static int _16_ = 16;
    public static int _8_ = 16;
    private static String[][] mod00 = {
            {"   ", "00"},
            {"000", "[BX]+[SI]"},
            {"001", "[BX]+[DI]"},
            {"010", "[BP]+[SI]"},
            {"011", "[BP]+[DI]"},
            {"100", "[SI]"},
            {"101", "[DI]"},
            {"110", "direct"},
            {"111", "[BX]"}
    };
    private static String mnemoRegExe = createRegExe(mn);
    private static String specialWordRegExe = createRegExe(spec);
    private static String variablesRegExe = createRegExe(specWordToVar);
    Store(){}
    private static String createRegExe(String[] strings){
        StringBuilder regex = new StringBuilder();
        regex.append("(");
        for (String s: strings){
            regex.append("(").append(s).append(")").append("|");
        }
        regex.deleteCharAt(regex.toString().length() - 1);
        regex.append(")");
        return regex.toString();
    }
    public static String getMnemoRegExe() {
        return "^" + mnemoRegExe;
    }
    public static String getSpecialWordRegExe() {
        return specialWordRegExe;
    }
    public static String getVariablesRegExe() {
        return variablesRegExe;
    }
    public static String[][] getMod11() {
        return mod11;
    }
    public static String[][] getMod00() {
        return mod00;
    }
    public static String[] getMn() {
        return mn;
    }
    public static String[] getSpec() {
        return spec;
    }
    public static String[] getSpecWordToVar() {
        return specWordToVar;
    }
    public static String binToHex(String bin){
        StringBuilder sb = new StringBuilder();
        char[] c = bin.toCharArray();
        for (int i = 0; i < c.length; i+=4) {
            sb.append(Integer.toHexString(Integer.parseInt(bin.substring(i, i + 4), 2)));
        }
        return sb.toString();
    }
}
