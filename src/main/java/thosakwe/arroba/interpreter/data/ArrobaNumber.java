package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;

public class ArrobaNumber extends ArrobaDatum {
    public Double value = 0.0;

    private ArrobaNumber() {

    }

    public ArrobaNumber(ArrobaParser.NumExprContext ctx) {
        if (ctx.DBL() != null) {
            value = Double.parseDouble(ctx.DBL().getText());
        } else if (ctx.INT() != null) {
            value = Integer.parseInt(ctx.INT().getText()) * 1.0;
        } else if (ctx.HEX() != null) {
            String hexString = ctx.HEX().getText().replaceAll("(^0x)|((H|h)$)", "");
            value = Integer.parseInt(hexString, 16) * 1.0;
        }
    }

    public static ArrobaNumber From(Double value) {
        ArrobaNumber result = new ArrobaNumber();
        result.value = value;
        return result;
    }

    public static ArrobaNumber Zero() {
        return ArrobaNumber.From(0.0);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
