package thosakwe.arroba.interpreter;

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

        loadParams(args, source.paramSpec(), interpreter);

        // Manually execute each statement, until we reach a "ret"
        for (ArrobaParser.StmtContext stmt : source.stmt()) {
            if (stmt.retStmt() != null) {
                result = interpreter.visitExpr(stmt.retStmt().expr());
                break;
            } else interpreter.visitStmt(stmt);
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

        loadParams(args, source.paramSpec(), interpreter);

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