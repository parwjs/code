package bean;

public class Calculator {
    private double firstNum;
    private double secondNum;
    private char Operator = '+';
    private double result;

    //JavaBean提供了计算功能
    public void calculate() {
        switch (this.Operator) {
            case '+' :
                this.result = this.firstNum + this.secondNum;
                break;
            case '-' :
                this.result = this.firstNum - this.secondNum;
                break;
            case '*' :
                this.result = this.firstNum * this.secondNum;
                break;
            case '/' :
                this.result = this.firstNum / this.secondNum;
                break;
            default:
                throw new RuntimeException("传入非法字符！");
        }
    }

    public char getOperator() {
        return Operator;
    }

    public double getFirstNum() {
        return firstNum;
    }

    public double getSecondNum() {
        return secondNum;
    }

    public double getResult() {
        return result;
    }

    public void setOperator(char operator) {
        Operator = operator;
    }

    public void setFirstNum(double firstNum) {
        this.firstNum = firstNum;
    }

    public void setSecondNum(double secondNum) {
        this.secondNum = secondNum;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
