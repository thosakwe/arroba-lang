package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaInterpreter;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaString;

import java.util.List;

public class PrintFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        for (ArrobaDatum arg : args) {

            System.out.println(arg == null ? "<null>" : arg.toString());
        }

        return args.isEmpty() ? null : args.get(0);
    }

    @Override
    public String toString() {
        return "<Native Function> print(args)";
    }
}
