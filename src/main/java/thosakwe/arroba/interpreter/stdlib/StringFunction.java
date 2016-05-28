package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.*;

import java.util.List;

public class StringFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (!args.isEmpty()) {
            if (args.get(0) instanceof ArrobaArray) {
                Boolean isByteArray = true;

                for (ArrobaDatum item : ((ArrobaArray) args.get(0)).items) {
                    if (!(item instanceof ArrobaNumber)) {
                        isByteArray = false;
                        break;
                    } else if(!((ArrobaNumber) item).isByte()) {
                        isByteArray = false;
                        break;
                    }
                }

                if (isByteArray) {
                    String result = "";

                    for (ArrobaDatum item : ((ArrobaArray) args.get(0)).items) {
                        ArrobaNumber num = (ArrobaNumber) item;
                        Integer intVal = num.value.intValue();

                        for (char ch: Character.toChars(intVal)) {
                            result += ch;
                        }
                    }

                    return new ArrobaPureString(result);
                }
            }

            return new ArrobaPureString(args.get(0).toString());
        }

        return new ArrobaException("String expects at least 1 argument.");
    }

    @Override
    public String toString() {
        return "<Native Function> String(x)";
    }
}
