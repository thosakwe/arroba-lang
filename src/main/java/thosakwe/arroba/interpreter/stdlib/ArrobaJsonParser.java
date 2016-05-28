package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.antlr.json.JSONBaseVisitor;
import thosakwe.arroba.antlr.json.JSONParser;
import thosakwe.arroba.interpreter.data.ArrobaArray;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaNumber;
import thosakwe.arroba.interpreter.data.ArrobaPureString;

import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class ArrobaJsonParser extends JSONBaseVisitor<ArrobaDatum> {
    @Override
    public ArrobaDatum visitJson(JSONParser.JsonContext ctx) {
        return visitValue(ctx.value());
    }

    @Override
    public ArrobaDatum visitArray(JSONParser.ArrayContext ctx) {
        List<ArrobaDatum> items = ctx.value().stream().map(this::visitValue).collect(Collectors.toList());
        return new ArrobaArray(items);
    }

    @Override
    public ArrobaDatum visitObject(JSONParser.ObjectContext ctx) {
        ArrobaDatum result = new ArrobaDatum();

        for (JSONParser.PairContext pairContext : ctx.pair()) {
            result.members.put(pairContext.STRING().getText()
                    .replaceAll("(^\")|(\"$)", "")
                    .replaceAll(Matcher.quoteReplacement("\\\""), "\""), visitValue(pairContext.value()));
        }

        return result;
    }

    @Override
    public ArrobaDatum visitValue(JSONParser.ValueContext ctx) {
        if (ctx.getText().equals("true")) {
            return ArrobaNumber.True();
        } else if (ctx.getText().equals("false")) {
            return ArrobaNumber.False();
        } else if (ctx.getText().equals("null")) {
            // Todo: null
            return null;
        } else if (ctx.NUMBER() != null) {
            return new ArrobaNumber(Double.parseDouble(ctx.NUMBER().getText()));
        } else if (ctx.STRING() != null) {
            String text = ctx.STRING().getText();
            return new ArrobaPureString(text
                    .replaceAll("(^\")|(\"$)", "")
                    .replaceAll(Matcher.quoteReplacement("\\\""), "\""));
        } else if (ctx.array() != null) {
            return visitArray(ctx.array());
        } else if (ctx.object() != null) {
            return visitObject(ctx.object());
        }

        return null;
    }
}
