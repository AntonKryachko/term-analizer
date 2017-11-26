package term_work.translator_assemb_lang.model;

public class LineWithMnem {
    private String Operator;
    private String Operand1;
    private String Operand2;


    public LineWithMnem(String operator, String operand1, String operand2) {
        Operator = operator;
        Operand1 = operand1;
        Operand2 = operand2;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    public String getOperand1() {
        return Operand1;
    }

    public void setOperand1(String operand1) {
        Operand1 = operand1;
    }

    public String getOperand2() {
        return Operand2;
    }

    public void setOperand2(String operand2) {
        Operand2 = operand2;
    }

    @Override
    public String toString() {
        return "LineWithMnem{" +
                "Operator='" + Operator + '\'' +
                ", Operand1='" + Operand1 + '\'' +
                ", Operand2='" + Operand2 + '\'' +
                '}';
    }
}
