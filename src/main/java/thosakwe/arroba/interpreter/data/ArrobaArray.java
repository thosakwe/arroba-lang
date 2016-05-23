package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ArrobaArray extends ArrobaDatum {
    private List<ArrobaDatum> items = new ArrayList<>();

    ArrobaArray() {
        addMembers();
    }

    ArrobaArray(ArrobaDatum single) {
        items.add(single);
        addMembers();
    }

    ArrobaArray(Collection<? extends ArrobaDatum> items) {
        this.items.addAll(items);
        addMembers();
    }

    ArrobaArray(List<ArrobaParser.ExprContext> items, ArrobaInterpreter interpreter) {
        this.items.addAll(items.stream().map(interpreter::visitExpr).collect(Collectors.toList()));
        addMembers();
    }

    private void addMembers() {
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
    }
}
