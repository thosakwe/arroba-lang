package thosakwe.arroba.js;

import org.apache.commons.cli.CommandLine;
import thosakwe.arroba.antlr.ArrobaBaseListener;
import thosakwe.arroba.antlr.ArrobaParser;

import java.io.PrintStream;

public class JsTranspiler extends ArrobaBaseListener {
    PrintStream output = System.out;
    int tabs = 0;

    public JsTranspiler(CommandLine cliOptions) {

    }

    private void write(String text) {
        for (int i = 0; i < tabs; i++) {
            output.print(" ");
        }

        output.print(text);
    }

    void writeLn(String text) {
        write(text);
        output.println();
    }

    @Override
    public void enterCompilationUnit(ArrobaParser.CompilationUnitContext ctx) {
        // Open an IIFE
        writeLn("(function() {");
        tabs++;
        super.enterCompilationUnit(ctx);
    }

    @Override
    public void exitCompilationUnit(ArrobaParser.CompilationUnitContext ctx) {
        super.exitCompilationUnit(ctx);
        // Close the IIFE
        tabs--;
        writeLn("})();");
    }

    @Override
    public void enterAssignStmt(ArrobaParser.AssignStmtContext ctx) {
        super.enterAssignStmt(ctx);
    }

    @Override
    public void exitAssignStmt(ArrobaParser.AssignStmtContext ctx) {
        super.exitAssignStmt(ctx);
    }
}
