package thosakwe.arroba.interpreter;

import org.antlr.v4.runtime.tree.ParseTree;
import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.data.ArrobaDatum;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrobaFunction extends ArrobaDatum {
    Map<String, ArrobaDatum> hoistedData = new HashMap<>();

    ArrobaFunction(ArrobaParser.ExprContext source) {
        super(source);
        addEquals();
    }

    public ArrobaFunction() {
    }

    void hoist(ParseTree ctx, ArrobaInterpreter interpreter, Boolean inline) {
        ClosureHoister hoister = new ClosureHoister(true);

        if (inline)
            hoister.visitInlineFunctionExpr((ArrobaParser.InlineFunctionExprContext) ctx);
        else hoister.visitFunctionExpr((ArrobaParser.FunctionExprContext) ctx);

        // Copy necessary data to the given function's scope...
        for (String symbol : hoister.externalSymbols) {
            ArrobaDatum resolvedValue = interpreter.value(symbol);

            if (resolvedValue != null) {
                //System.out.println("Hoisting " + symbol + " -> " + interpreter.value(symbol));
                hoistedData.put(symbol, interpreter.value(symbol));
            }
        }

        for (String symbol : hoistedData.keySet()) {
            interpreter.value(symbol, hoistedData.get(symbol));
        }
    }

    void hoist(ParseTree ctx, ArrobaInterpreter interpreter) {
        hoist(ctx, interpreter, false);
    }

    public ArrobaDatum invoke(ArrobaDatum arg) {
        List<ArrobaDatum> args = new ArrayList<>();
        args.add(arg);
        return invoke(args);
    }

    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        return null;
    }

    void loadParams(List<ArrobaDatum> args, ArrobaParser.ParamSpecContext paramSpec, ArrobaInterpreter interpreter) {
        // Load arguments into a new scope
        for (int i = 0; i < paramSpec.ID().size() && i < args.size(); i++) {
            String targetSymbol = paramSpec.ID(i).getText();
            interpreter.value(targetSymbol, args.get(i), true);

            /*System.out.println("Passing argument: " + targetSymbol + " = " + args.get(i));
            System.out.println(targetSymbol + " now resides in scope " + (interpreter.scopes.size() - 1) + ".");
            System.out.println("There are " + interpreter.scopes.size() + " total scopes.");
            interpreter.dumpScopes();*/
        }
    }

    @Override
    public String toString() {
        return "<Function>";
    }
}
