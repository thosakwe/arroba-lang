package thosakwe.arroba.interpreter;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.data.ArrobaDatum;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrobaFunction extends ArrobaDatum {
    public Map<String, ArrobaDatum> hoistedData = new HashMap<>();

=======
import java.util.List;

public class ArrobaFunction extends ArrobaDatum {
>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
    protected ArrobaFunction() {
        addEquals();
    }

    ArrobaFunction(ArrobaParser.ExprContext source) {
        super(source);
        addEquals();
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
}
