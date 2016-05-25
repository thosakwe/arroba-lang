package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.cli.AstGen;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrobaString extends ArrobaDatum {
    private ArrobaInterpreter interpreter;
    String text;

    private void addLen() {

        members.put("len", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return new ArrobaNumber(text.length() * 1.0);
            }

            @Override
            public String toString() {
                return "<Native Function> string.len()";
            }
        });
    }

    public ArrobaString(String text, ArrobaParser.StringExprContext source, ArrobaInterpreter interpreter) {
        super(source);
        this.text = text;
        this.interpreter = interpreter;
        addLen();
    }

    public String resolveValue() {
        String result = text.replaceAll(Matcher.quoteReplacement("\\\""), "\"");
        Pattern rgx = Pattern.compile("\\$\\{([^\\}]+)\\}");
        //Pattern rgx = Pattern.compile("^.*(\\$\\{([^}]+)}).*$");
        Matcher matcher = rgx.matcher(result);

        while (matcher.find()) {
            ArrobaParser parser = AstGen.makeParserFromText(matcher.group(1));
            ArrobaParser.ExprContext expr = parser.compilationUnit().stmt(0).exprStmt().expr();
            ArrobaDatum resolvedValue = interpreter.visitExpr(expr);
            // "<undefined expression(" + matcher.group(1) + ")>"
            String resolved = resolvedValue == null ? "" : resolvedValue.toString();

            if (resolvedValue instanceof ArrobaString) {
                resolved = ((ArrobaString) resolvedValue).resolveValue();
            }

            result = result.replace(matcher.group(0), resolved);
        }

        return result;
    }

    @Override
    public String toString() {
        return resolveValue();
        //return "<String>: \"" + resolveValue() + "\"";
    }

    @Override
    public Boolean toBool() {
        return !toString().isEmpty();
    }
}

