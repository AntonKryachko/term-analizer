package term_work.translator_assemb_lang.model;

public class Result {
    private String generalCode;
    private String COP;
    private String W;
    private String D;
    private String REG;
    private String MOD;
    private String RM;
    private String DATA_LOW;
    private String DATA_HIGH;
    private String DISP_LOW;
    private String DISP_HIGH;

    public String getW() {
        return W;
    }
    public void setW(String w) {
        W = w;
    }
    public String getD() {
        return D;
    }
    public void setD(String d) {
        D = d;
    }
    public String getDISP_LOW() {
        return DISP_LOW;
    }
    public void setDISP_LOW(String DISP_LOW) {
        this.DISP_LOW = DISP_LOW;
    }
    public String getDISP_HIGH() {
        return DISP_HIGH;
    }
    public void setDISP_HIGH(String DISP_HIGH) {
        this.DISP_HIGH = DISP_HIGH;
    }
    public String getGeneralCode() {
        return generalCode;
    }
//    private void setGeneralCode(){}
    public String getCOP() {
        return COP;
    }
    public void setCOP(String COP) {
        this.COP = COP;
    }
    public String getREG() {
        return REG;
    }
    public void setREG(String REG) {
        this.REG = REG;
    }
    public String getMOD() {
        return MOD;
    }
    public void setMOD(String MOD) {
        this.MOD = MOD;
    }
    public String getRM() {
        return RM;
    }
    public void setRM(String RM) {
        this.RM = RM;
    }
    public String getDATA_LOW() {
        return DATA_LOW;
    }
    public void setDATA_LOW(String DATA_LOW) {
        this.DATA_LOW = DATA_LOW;
    }
    public String getDATA_HIGH() {
        return DATA_HIGH;
    }
    public void setDATA_HIGH(String DATA_HIGH) {
        this.DATA_HIGH = DATA_HIGH;
    }
    @Override
    public String toString() {
        return "Result{" +
                "generalCode='" + generalCode + '\'' +
                ", COP='" + COP + '\'' +
                ", W='" + W + '\'' +
                ", D='" + D + '\'' +
                ", REG='" + REG + '\'' +
                ", MOD='" + MOD + '\'' +
                ", RM='" + RM + '\'' +
                ", DATA_LOW='" + DATA_LOW + '\'' +
                ", DATA_HIGH='" + DATA_HIGH + '\'' +
                ", DISP_LOW='" + DISP_LOW + '\'' +
                ", DISP_HIGH='" + DISP_HIGH + '\'' +
                '}';
    }
}
