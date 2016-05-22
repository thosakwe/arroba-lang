package thosakwe.arroba.interpreter;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.data.ArrobaDatum;

import java.util.List;

class ArrobaFullFunction extends ArrobaFunction {
    private ArrobaInterpreter interpreter;
    ArrobaParser.FunctionExprContext source;

    public ArrobaFullFunction(ArrobaParser.FunctionExprContext source, ArrobaInterpreter interpreter) {
        this.source = source;
        this.interpreter = interpreter;
    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        ArrobaDatum result = null;
        interpreter.createChildScope();

        // Load arguments into a new scope
        for (int i = 0; i < source.paramSpec().ID().size() && i < args.size(); i++) {
            String targetSymbol = source.paramSpec().ID(i).getText();
            interpreter.value(targetSymbol, args.get(i), false);
            //System.out.println("Passing argument: " + targetSymbol + " = " + args.get(i));
        }

        // Manually execute each statement, until we reach a "ret"
        for (ArrobaParser.StmtContext stmt : source.stmt()) {
            if (stmt.retStmt() != null) {
                result = interpreter.resolveExpr(stmt.retStmt().expr());
                break;
            } else interpreter.enterStmt(stmt);
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
        ArrobaDatum result = null;
        interpreter.createChildScope();

        // Load arguments into a new scope
        for (int i = 0; i < source.paramSpec().ID().size() && i < args.size(); i++) {
            String targetSymbol = source.paramSpec().ID(i).getText();
            interpreter.value(targetSymbol, args.get(i), false);
            //System.out.println("Passing argument: " + targetSymbol + " = " + args.get(i));
        }

        result = interpreter.resolveExpr(source.expr());

        // Destroy that last scope
        interpreter.exitLastScope();

        return result;
    }

    @Override
    public String toString() {
        return "<Inline Function>(" + source.getText() + ")";
    }
}