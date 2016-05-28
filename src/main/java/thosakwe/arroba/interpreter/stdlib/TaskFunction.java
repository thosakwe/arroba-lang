package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaException;
import thosakwe.arroba.interpreter.data.ArrobaNumber;
import thosakwe.arroba.interpreter.data.ArrobaTask;

import java.util.List;

public class TaskFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (!args.isEmpty()) {
            if (args.get(0) instanceof ArrobaFunction) {
                return new ArrobaTask((ArrobaFunction) args.get(0));
            }

            return new ArrobaException("Task expects argument 1 to be a function");
        }

        return new ArrobaException("Task expects 1 argument");
    }

    @Override
    public String toString() {
        return "<Native Function> Task(fn)";
    }
}

