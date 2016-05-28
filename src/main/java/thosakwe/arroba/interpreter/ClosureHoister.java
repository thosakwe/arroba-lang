package thosakwe.arroba.interpreter;

import org.antlr.v4.runtime.tree.TerminalNode;
import thosakwe.arroba.antlr.ArrobaBaseVisitor;
import thosakwe.arroba.antlr.ArrobaParser;

import java.util.ArrayList;
import java.util.List;

public class ClosureHoister extends ArrobaBaseVisitor {
    private List<String> localSymbols = new ArrayList<>();
    List<String> externalSymbols = new ArrayList<>();
    private ArrobaParser.FunctionExprContext source;
    private Boolean okToHoistParams = false;

    ClosureHoister(boolean okToHoistParams) {
        this.okToHoistParams = okToHoistParams;
    }

    @Override
    public Object visitTryStmt(ArrobaParser.TryStmtContext ctx) {
        if (ctx.ID() != null) {
            localSymbols.add(ctx.ID().getText());
        }
        return super.visitTryStmt(ctx);
    }

    @Override
    public Object visitFunctionExpr(ArrobaParser.FunctionExprContext ctx) {
        source = ctx;
        return super.visitFunctionExpr(ctx);
    }

    private Boolean notInParamSpec(String symbol) {
        if (okToHoistParams)
            return true;

        if (source != null && symbol != null) {
            for (TerminalNode id : source.paramSpec().ID()) {
                if (id.getText().equals(symbol))
                    return false;
            }
        }

        return true;
    }

    @Override
    public Object visitIdExpr(ArrobaParser.IdExprContext ctx) {
        if (!localSymbols.contains(ctx.ID().getText())
                && !externalSymbols.contains(ctx.ID().getText())
                && notInParamSpec(ctx.ID().getText()))
            externalSymbols.add(ctx.ID().getText());
        return super.visitIdExpr(ctx);
    }

    @Override
    public Object visitLocalExpr(ArrobaParser.LocalExprContext ctx) {
        if (!localSymbols.contains(ctx.ID().getText()))
            localSymbols.add(ctx.ID().getText());
        return super.visitLocalExpr(ctx);
    }
}
