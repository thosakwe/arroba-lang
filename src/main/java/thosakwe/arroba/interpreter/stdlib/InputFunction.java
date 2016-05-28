package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaString;

import java.util.List;
import java.util.Scanner;

public class InputFunction extends ArrobaFunction {
    private ArrobaInterpreter interpreter;

    public InputFunction(ArrobaInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        for (ArrobaDatum arg: args) {
            System.out.print(arg.toString() + " ");
        }

        return new ArrobaString(interpreter.scanner.nextLine(), null, null);
    }

    @Override
    public String toString() {
        return "<Native Function> input()";
    }
}
