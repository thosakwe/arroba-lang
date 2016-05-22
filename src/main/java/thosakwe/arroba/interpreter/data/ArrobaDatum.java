package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;

public class ArrobaDatum {
    protected ArrobaParser.ExprContext source;

    public ArrobaDatum() {

    }

    public ArrobaDatum(ArrobaParser.ExprContext source) {
        this.source = source;
    }

    public ArrobaDatum resolve(String memberName) {
        return null;
    }
}
