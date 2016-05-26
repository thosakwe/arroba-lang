package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaNumber;

import java.util.List;

public class TaskFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (!args.isEmpty()) {
            if (args.get(0) instanceof ArrobaFunction) {
                return new ArrobaTask((ArrobaFunction) args.get(0));
            }

            System.err.println("Task expects argument 1 to be a function");
            return null;
        }

        System.err.println("Task expects 1 argument");
        return null;
    }

    @Override
    public String toString() {
        return "<Native Function> Task(fn)";
    }
}

class ArrobaTaskResult extends ArrobaDatum {
    private Boolean success;

    ArrobaTaskResult(Boolean success) {
        this.success = success;

        members.put("success", new ArrobaNumber(success ? 1.0 : 0.0));
    }

    @Override
    public String toString() {
        return "<Task Result: " + (success ? "Success" : "Failure") + ">";
    }
}