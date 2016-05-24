package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.ArrobaFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrobaDatum {
    protected ArrobaParser.ExprContext source;
    public Map<String, ArrobaDatum> members = new HashMap<>();

    public ArrobaDatum() {
    }

    public ArrobaDatum(ArrobaParser.ExprContext source) {
        this.source = source;
    }

    public ArrobaDatum resolve(String memberName) {
        String[] parts = memberName.split("\\.", 2);

        ArrobaDatum resolved = members.get(parts[0]);

        if (resolved != null) {
            if (parts.length > 1 && !parts[1].isEmpty()) {
                return resolved.resolve(parts[1]);
            } else return resolved;
        }

        for (String symbol : members.keySet()) {
            System.out.println("Symbol: " + symbol);
        }
        System.err.println("Could not resolve member: " + memberName);
        return null;
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
                        System.err.println("Could not resolve member: " + parts[i]);
                        return null;
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
}
