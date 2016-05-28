package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaDatum;

import java.util.List;

public class AnyFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        return new ArrobaDatum();
    }

    @Override
    public String toString() {
        return "<Native Function> any()";
    }
}
