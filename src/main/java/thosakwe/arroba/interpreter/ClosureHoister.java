package thosakwe.arroba.interpreter;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import thosakwe.arroba.antlr.ArrobaBaseVisitor;
import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.cli.AstGen;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public Object visitStringExpr(ArrobaParser.StringExprContext ctx) {
        String text = ctx.getText();
        Pattern interpolator = Pattern.compile("\\$\\{([^\\}]+)\\}");
        Matcher matcher = interpolator.matcher(text);

        while (matcher.find()) {
            // For each interpolated string, we're going to actually
            // create another hoister to search for ID's.

            ArrobaParser parser = AstGen.makeParserFromText(matcher.group(1));
            ClosureHoister child = new ClosureHoister(okToHoistParams);
            child.visitCompilationUnit(parser.compilationUnit());

            // Copy any ID's used in interpolation too.
            child.externalSymbols.stream()
                    .filter(symbol -> !externalSymbols.contains(symbol))
                    .forEach(symbol -> externalSymbols.add(symbol));

            text = text.replace(matcher.group(0), "");
        }

        return super.visitStringExpr(ctx);
    }
}
