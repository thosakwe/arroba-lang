package thosakwe.arroba.interpreter;

import org.antlr.v4.runtime.tree.TerminalNode;
import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.data.ArrobaDatum;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ArrobaFullFunction extends ArrobaFunction {
    private ArrobaInterpreter interpreter;
    private ArrobaParser.FunctionExprContext source;

    ArrobaFullFunction(ArrobaParser.FunctionExprContext source, ArrobaInterpreter interpreter) {
=======
import java.util.List;

class ArrobaFullFunction extends ArrobaFunction {
    private ArrobaInterpreter interpreter;
    ArrobaParser.FunctionExprContext source;

    public ArrobaFullFunction(ArrobaParser.FunctionExprContext source, ArrobaInterpreter interpreter) {
>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
        this.source = source;
        this.interpreter = interpreter;
    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        ArrobaDatum result = null;
        interpreter.createChildScope();

<<<<<<< HEAD
        ClosureHoister hoister = new ClosureHoister(true);
        hoister.visitFunctionExpr(source);

        loadParams(args, source.paramSpec(), interpreter);

        // Copy necessary data to the given function's scope...
        for (String symbol : hoister.externalSymbols) {
            //System.out.println("Hoisting " + symbol + " -> " + interpreter.value(symbol));
            hoistedData.put(symbol, interpreter.value(symbol));
        }

        for (String symbol : hoistedData.keySet()) {
            interpreter.value(symbol, hoistedData.get(symbol));
        }

=======
        loadParams(args, source.paramSpec(), interpreter);

>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
        // Manually execute each statement, until we reach a "ret"
        for (ArrobaParser.StmtContext stmt : source.stmt()) {
            result = interpreter.visitStmt(stmt);
            if (stmt.retStmt() != null) {
                break;
            }
        }

        // Destroy that last scope
        interpreter.exitLastScope();

        return result;
    }

    @Override
    public String toString() {
        String result = "<Function(";
        List<TerminalNode> ids = source.paramSpec().ID();

        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) {
                result += ", ";
            }

            result += ids.get(i).getText();
        }
        return result + ")>";
    }
}

class ArrobaInlineFunction extends ArrobaFunction {
    private ArrobaInterpreter interpreter;
    private ArrobaParser.InlineFunctionExprContext source;

    ArrobaInlineFunction(ArrobaParser.InlineFunctionExprContext source, ArrobaInterpreter interpreter) {
        this.source = source;
        this.interpreter = interpreter;
    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        interpreter.createChildScope();

<<<<<<< HEAD
        ClosureHoister hoister = new ClosureHoister(true);
        hoister.visitInlineFunctionExpr(source);

        loadParams(args, source.paramSpec(), interpreter);

        // Copy necessary data to the given function's scope...
        for (String symbol : hoister.externalSymbols) {
            System.out.println("Hoisting " + symbol + " -> " + interpreter.value(symbol));
            hoistedData.put(symbol, interpreter.value(symbol));
        }

        System.err.println(source.getText());

        for (String symbol : hoistedData.keySet()) {
            interpreter.value(symbol, hoistedData.get(symbol));
        }

=======
        loadParams(args, source.paramSpec(), interpreter);

>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
        ArrobaDatum result = interpreter.visitExpr(source.expr());

        // Destroy that last scope
        interpreter.exitLastScope();

        return result;
    }

    @Override
    public String toString() {
        return "<Inline Function>(" + source.getText() + ")";
    }
}