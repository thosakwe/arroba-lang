package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ArrobaArray extends ArrobaDatum {
    public List<ArrobaDatum> items = new ArrayList<>();

    ArrobaArray() {
        addMembers();
    }

    ArrobaArray(ArrobaDatum single) {
        items.add(single);
        addMembers();
    }

    public ArrobaArray(Collection<? extends ArrobaDatum> items) {
        this.items.addAll(items);
        addMembers();
    }

    public ArrobaArray(List<ArrobaParser.ExprContext> items, ArrobaInterpreter interpreter) {
        this.items.addAll(items.stream().map(interpreter::visitExpr).collect(Collectors.toList()));
        addMembers();
    }

    private void addMembers() {
        ArrobaArray parent = this;

        members.put("len", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return ArrobaNumber.From(items.size() * 1.0);
            }

            @Override
            public String toString() {
                return "<Native Function> array.len";
            }
        });

        members.put("str", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return new ArrobaString("Array <" + parent.items.size() + ">", null, null);
            }

            @Override
            public String toString() {
                return "<Native Function> str()";
            }
        });
    }

    public ArrobaDatum resolveIndex(ArrobaDatum index) {
        if (index instanceof ArrobaNumber) {
            return items.get(((ArrobaNumber) index).value.intValue());
        }

        System.err.println("Given indexer is not a number: " + index.toString());
        return null;
    }

    @Override
    public String toString() {
        return "Array <" + items.size() + ">";
    }
}
