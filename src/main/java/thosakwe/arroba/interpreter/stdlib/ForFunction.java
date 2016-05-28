package thosakwe.arroba.interpreter.stdlib;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaNumber;

import java.util.ArrayList;
import java.util.List;

public class ForFunction extends ArrobaFunction {

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (args.size() == 0) {
            System.err.println("Error: no arguments provided");
            return null;
        }

        ArrobaDatum target = args.get(0);

        if (target instanceof ArrobaNumber) {
            return new ForFunctionStub(((ArrobaNumber) target).value.intValue());
        } else System.err.println("Given argument is not a number");

        return null;
    }

    @Override
    public String toString() {
        return "<Native Function> for(times)";
    }
}

class ForFunctionStub extends ArrobaFunction {
    private Integer times;

    ForFunctionStub(Integer times) {
        this.times = times;
    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (args.size() == 0) {
            System.err.println("Error: no arguments provided");
            return null;
        }

        ArrobaDatum target = args.get(0);

        if (target instanceof ArrobaFunction) {
            List<ArrobaDatum> arguments = new ArrayList<>();
            ArrobaFunction func = (ArrobaFunction) target;
            for (int i = 0; i < times; i++) {
                func.invoke(arguments);
            }
        } else System.err.println("Given argument is not a function");
        return null;
    }

    @Override
    public String toString() {
        return "<for loop (" + times + " times)>";
    }
}
