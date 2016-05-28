package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaArray;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaException;

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

                    return new ArrobaException("Loop expects argument 1 to be an array");
                }

                @Override
                public String toString() {
                    return "<Native Loop>";
                }
            };
        }

        return new ArrobaException("Print expects argument 1 to be a function");
    }

    @Override
    public String toString() {
        return "<Native Function> all(fn)";
    }
}
