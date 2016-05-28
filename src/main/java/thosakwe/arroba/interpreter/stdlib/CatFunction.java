package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.*;

import java.util.List;

public class CatFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (!args.isEmpty()) {
            return new CatFunctionStub(args.get(0));
        }

        return new ArrobaException("cat expects at least one argument");
    }

    @Override
    public String toString() {
        return "<Native Function> cat(item)";
    }
}

class CatFunctionStub extends ArrobaFunction {
    private final ArrobaDatum item;

    CatFunctionStub(ArrobaDatum item) {
        this.item = item;
    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (!args.isEmpty()) {
            ArrobaDatum parent = args.get(0);

            if (parent instanceof ArrobaString) {
                return new ArrobaPureString(parent.toString() + item.toString());
            } else if (parent instanceof ArrobaNumber) {
                if (item instanceof ArrobaNumber) {
                    ((ArrobaNumber) parent).value += ((ArrobaNumber) item).value;
                    return parent;
                } else if (item instanceof ArrobaString) {
                    return new ArrobaPureString(parent.toString() + item.toString());
                } else {
                    return new ArrobaException("Numbers can only be joined with strings or other numbers");
                }
            } else if (parent instanceof ArrobaArray) {
                ((ArrobaArray) parent).items.add(item);
                return parent;
            } else {
                return new ArrobaException("Expression does not support concatenation: " + parent.toString());
            }
        }

        return new ArrobaException("Concatenator expects at least one argument");
    }

    @Override
    public String toString() {
        return "<Concatenator>";
    }
}
