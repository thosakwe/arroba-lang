package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaArray;
import thosakwe.arroba.interpreter.data.ArrobaDatum;

import java.util.ArrayList;
import java.util.List;

public class AllFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        ArrobaDatum target = args.get(0);

        if (target != null && target instanceof ArrobaFunction) {
            ArrobaFunction targetFn = (ArrobaFunction) target;

            return new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    ArrobaDatum target = args.get(0);

                    if (target != null && target instanceof ArrobaArray) {
                        List<ArrobaDatum> results = new ArrayList<>();
                        ArrobaArray targetArr = (ArrobaArray) target;

                        for (ArrobaDatum datum : targetArr.items) {
                            List<ArrobaDatum> arguments = new ArrayList<>();
                            arguments.add(datum);
                            results.add(targetFn.invoke(arguments));
                        }

                        return new ArrobaArray(results);
                    }

                    System.err.println("Loop expects argument 1 to be an array");
                    return null;
                }

                @Override
                public String toString() {
                    return "<Native Loop>";
                }
            };
        }

        System.err.println("Print expects argument 1 to be a function");
        return null;
    }

    @Override
    public String toString() {
        return "<Native Function> all(fn)";
    }
}
