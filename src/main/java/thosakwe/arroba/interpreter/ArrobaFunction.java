package thosakwe.arroba.interpreter;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.data.ArrobaDatum;

import java.util.List;

public class ArrobaFunction extends ArrobaDatum {
    protected ArrobaFunction() {
    }

    ArrobaFunction(ArrobaParser.ExprContext source) {
        super(source);
    }

    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        return null;
    }
}
