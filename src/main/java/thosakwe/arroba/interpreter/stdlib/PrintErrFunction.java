package thosakwe.arroba.interpreter.stdlib;

public class PrintErrFunction extends PrintFunction {
    public PrintErrFunction() {
        super();
        output = System.err;
    }

    @Override
    public String toString() {
        return "<Native Function> printErr(args)";
    }
}
