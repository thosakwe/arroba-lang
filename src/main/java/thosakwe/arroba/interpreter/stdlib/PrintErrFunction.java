package thosakwe.arroba.interpreter.stdlib;

public class PrintErrFunction extends PrintFunction {
    public PrintErrFunction() {
        super();
        output = System.err;
    }
}
