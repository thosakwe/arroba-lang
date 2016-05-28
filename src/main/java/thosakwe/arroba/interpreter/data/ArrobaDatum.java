package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.ArrobaFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrobaDatum {
    private ArrobaParser.ExprContext source;
    public Map<String, ArrobaDatum> members = new HashMap<>();
    public int tabCount = 0;

    public ArrobaDatum() {
        addEquals();
    }

    public ArrobaDatum(ArrobaParser.ExprContext source) {
        this.source = source;
        addEquals();
    }

    public ArrobaDatum resolve(String memberName) {
        String[] parts = memberName.split("\\.", 2);

        ArrobaDatum resolved = members.get(parts[0]);

        if (resolved != null) {
            if (parts.length > 1 && !parts[1].isEmpty()) {
                return resolved.resolve(parts[1]);
            } else return resolved;
        }

        /*for (String symbol : members.keySet()) {
            System.out.println("Symbol: " + symbol);
        }*/

        return new ArrobaException("Could not resolve member: " + memberName);
    }

    public ArrobaDatum setMember(String memberName, ArrobaDatum value) {
        if (!memberName.contains(".")) {
            members.put(memberName, value);
        } else {
            ArrobaDatum parent = this;
            String[] parts = memberName.split("\\.");

            for (int i = 0; i < parts.length; i++) {
                ArrobaDatum next = parent.resolve(parts[i]);

                if (i < parts.length - 1) {
                    if (next == null) {
                        return new ArrobaException("Could not resolve member: " + parts[i]);
                    }

                    parent = next;
                } else {
                    parent.setMember(parts[i], value);
                }
            }
        }

        return value;
    }

    public Boolean toBool() {
        return true;
    }

    protected void addEquals() {
        ArrobaDatum parent = this;

        /*members.put("equals", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return args.isEmpty() ? ArrobaNumber.False() : new ArrobaNumber(parent.equalsDatum(args.get(0)) ? 1.0 : 0.0);
            }

            @Override
            public String toString() {
                return "<Native Function> obj.equals(other)";
            }
        });*/
    }

    public Boolean equalsDatum(ArrobaDatum other) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public String toString() {
        String result = "";

        if (members.size() == 0) {
            return "{}";
        }

        for (int i = 0; i < tabCount; i++) {
            result += "    ";
        }
        result += "{";

        int k = 0;
        for (String key : members.keySet()) {
            for (int i = 0; i < tabCount; i++) {
                result += "    ";
            }

            if (k > 0)
                result += ", ";
            result += key + ": ";

            ArrobaDatum found = members.get(key);
            //found.tabCount = tabCount + 1;
            result += found.toString();
            found.tabCount = 0;
            k++;
        }

        result += "";
        for (int i = 0; i < tabCount; i++) {
            result += "    ";
        }
        result += "}";
        return result;
    }
}
