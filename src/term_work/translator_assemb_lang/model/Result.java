package term_work.translator_assemb_lang.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Result {
    private StringProperty adress;
    private StringProperty objectCode;
    private StringProperty progCode;

    public String getAdress() {
        return adress.get();
    }
    public StringProperty adressProperty() {
        return adress;
    }
    public String getObjectCode() {
        return objectCode.get();
    }
    public StringProperty objectCodeProperty() {
        return objectCode;
    }
    public String getProgCode() {
        return progCode.get();
    }
    public StringProperty progCodeProperty() {
        return progCode;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }
    public void setObjectCode(String objectCode) {
        this.objectCode.set(objectCode);
    }
    public void setProgCode(String progCode) {
        this.progCode.set(progCode);
    }

    public Result(){
        this.adress = new SimpleStringProperty("");
        this.objectCode = new SimpleStringProperty("");
        this.progCode = new SimpleStringProperty("");
    }

    public Result(String adress, String objectCode,String progCode){
        this.adress = new SimpleStringProperty(adress);
        this.objectCode = new SimpleStringProperty(objectCode);
        this.progCode = new SimpleStringProperty(progCode);
    }

    @Override
    public String toString() {
        return "Result{" +
                "adress=" + adress +
                ", objectCode=" + objectCode +
                ", progCode=" + progCode +
                '}';
    }
}
