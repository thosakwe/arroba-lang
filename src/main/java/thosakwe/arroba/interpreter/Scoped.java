package thosakwe.arroba.interpreter;

import thosakwe.arroba.antlr.ArrobaBaseListener;
import thosakwe.arroba.antlr.ArrobaBaseVisitor;
import thosakwe.arroba.interpreter.data.ArrobaDatum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Scope {
    Boolean dead = false;
    Map<String, ArrobaDatum> symbols = new HashMap<String, ArrobaDatum>();
}

class Scoped extends ArrobaBaseVisitor<ArrobaDatum> {
    Scope globalScope = new Scope();
    public List<Scope> scopes = new ArrayList<Scope>();

    Scoped() {
        scopes.add(globalScope);
    }

    public Scope createChildScope() {
        scopes.add(new Scope());
        return scopes.get(scopes.size() - 1);
    }

    public void dumpScopes() {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Scope scope = scopes.get(i);
            System.out.println("All symbols in scope " + (i + 1) + ":");
            for (String key : scope.symbols.keySet()) {
                System.out.println("\t" + key + ": " + scope.symbols.get(key).toString());
            }
        }
    }

    void exitLastScope() {
        scopes.get(scopes.size() - 1).dead = true;
        scopes.remove(scopes.size() - 1);
    }

    ArrobaDatum value(String symbol) {
        return resolveSymbol(symbol);
    }

    ArrobaDatum value(String symbol, ArrobaDatum value, Boolean self) {
        Map<String, ArrobaDatum> symbolTable = scopes.get(scopes.size() - 1).symbols;
        ArrobaDatum existing = resolveSymbol(symbol);

        if (existing != null) {
            //System.out.println("Reassigning " + symbol);

            // Force new variable if self:
            if (self) {
                symbolTable.put(symbol, value);
                return value;
            }
            // Otherwise, hoist it
            Scope containing = scopeContaining(symbol);

            if (containing != null) {
                containing.symbols.put(symbol, value);
            } else {
                // This will never happen
                System.err.println("Invalid scope???");
            }

        } else symbolTable.put(symbol, value);
        return value;
    }

    ArrobaDatum value(String symbol, ArrobaDatum value) {
        return value(symbol, value, false);
    }

    private ArrobaDatum resolveSymbol(String symbol) {
        //System.out.println("Resolving: " + symbol);
        // Search scopes from bottom to top

        //dumpScopes();

        for (int i = scopes.size() - 1; i >= 0; i--) {
            Scope scope = scopes.get(i);
            /*System.out.println("All symbols in scope " + (i + 1) + ":");
            for (String key : scope.symbols.keySet()) {
                System.out.println("\t" + key + ": " + scope.symbols.get(key).toString());
            }*/

            if (!scope.dead && scope.symbols.containsKey(symbol)) {
                ArrobaDatum found = scope.symbols.get(symbol);
                //System.out.println("Resolved symbol " + symbol + " -> " + found);
                return found;
            }
        }
        return null;
    }

    private Scope scopeContaining(String symbol) {
        // Search scopes from bottom to top
        for (Scope scope : scopes) {
            if (!scope.dead && scope.symbols.containsKey(symbol)) {
                return scope;
            }
        }
        return null;
    }
}
