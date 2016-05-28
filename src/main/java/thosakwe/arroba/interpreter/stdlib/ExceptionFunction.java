package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaException;

import java.util.List;

public class ExceptionFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        ArrobaException exception = new ArrobaException();

        //exception.message = "An unhandled exception occurred.";

        exception.message = "An unhandled exception occurred.";


        if (args.size() > 0)
            exception.message = args.get(0).toString();

        if (args.size() > 1)
            exception.value = args.get(1);

        if (args.size() > 2)
            exception.stackTrace = args.get(2).toString();

        exception.addMembers();
        return exception;
    }

    @Override
    public String toString() {
        return "<Native Function> Exception(msg?, value?, stack?)";
    }
}
