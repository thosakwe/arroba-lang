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

    protected ArrobaArray() {
        addMembers();
    }

    public ArrobaArray(ArrobaDatum single) {
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

        members.put("all", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                ArrobaDatum target = args.get(0);

                if (target != null && target instanceof ArrobaFunction) {
                    List<ArrobaDatum> results = new ArrayList<>();
                    ArrobaFunction targetFn = ((ArrobaFunction) target);

                    for (ArrobaDatum item : items) {
                        ArrobaDatum result = targetFn.invoke(item);
                        //System.out.println(item + " -> " + result);
                        results.add(result);
                    }

                    return new ArrobaArray(results);
                }

                System.err.println("array.all expects argument 1 to be a function");
                return null;
            }

            @Override
            public String toString() {
                return "<Native Function> array.all(fn)";
            }
        });

        members.put("len", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return new ArrobaNumber(new Integer(items.size()).doubleValue());
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
