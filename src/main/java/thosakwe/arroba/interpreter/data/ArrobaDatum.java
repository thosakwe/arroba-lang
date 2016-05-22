package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;

import java.util.HashMap;
import java.util.Map;

public class ArrobaDatum {
    protected ArrobaParser.ExprContext source;
    public Map<String, ArrobaDatum> members = new HashMap<>();

    protected ArrobaDatum() {

    }

    public ArrobaDatum(ArrobaParser.ExprContext source) {
        this.source = source;
    }

    public ArrobaDatum resolve(String memberName) {
        return null;
    }
}
