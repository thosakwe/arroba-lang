package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.cli.AstGen;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrobaString extends ArrobaDatum {
    private ArrobaInterpreter interpreter;
    String text;

    private void addLen() {
        addEquals();
        ArrobaString parent = this;
        members.put("chars", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                List<ArrobaDatum> chars = new ArrayList<>();

                for (char ch : parent.toString().toCharArray()) {
                    chars.add(new ArrobaPureString(new String(new char[]{ch})));
                }

                return new ArrobaArray(chars);
            }

            @Override
            public String toString() {
                return "<Native Function> string.chars()";
            }
        });
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
        members.put("split", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty() && args.get(0) instanceof ArrobaString) {
                    String[] split = parent.toString().split(args.get(0).toString());
                    List<ArrobaDatum> result = new ArrayList<>();

                    for (String str : split) {
                        result.add(new ArrobaPureString(str));
                    }

                    return new ArrobaArray(result);
                }

                System.err.println("string.split expects argument 1 to be a string");
                return null;
            }

            @Override
            public String toString() {
                return "<Native Function> string.split(splitter)";
            }
        });
        members.put("trim", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return new ArrobaPureString(parent.toString().trim());
            }

            @Override
            public String toString() {
                return "<Native Function> string.trim()";
            }
        });
    }

    public ArrobaString(String text, ArrobaParser.StringExprContext source, ArrobaInterpreter interpreter) {
        super(source);

        this.text = (text == null) ? "" : text;
        this.text = text
                .replaceAll(Matcher.quoteReplacement("\\\""), "\"")
                .replaceAll(Matcher.quoteReplacement("\\n"), "\n")
                .replaceAll(Matcher.quoteReplacement("\\b"), "\b")
                .replaceAll(Matcher.quoteReplacement("\\r"), "\r");
        this.interpreter = interpreter;
        addLen();
    }

    public String resolveValue() {
        String result = text;
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ArrobaString && toString().equals(obj.toString());
    }
}

