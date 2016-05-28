package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaInterpreter;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaArray;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaString;

import java.io.PrintStream;
import java.util.List;

public class PrintFunction extends ArrobaFunction {
    PrintStream output = System.out;

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        for (ArrobaDatum arg : args) {

            output.println(arg == null ? "<null>" : arg.toString());
        }

        if (args.isEmpty())
            return null;
        else if (args.size() == 1)
            return args.get(0);
        else return new ArrobaArray(args);
    }

    @Override
    public String toString() {
        return "<Native Function> print(args)";
    }
}
