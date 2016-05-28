package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.ArrobaFunction;

import java.util.List;

public class ArrobaNumber extends ArrobaDatum {
    public Double value = 0.0;

    private ArrobaNumber() {
        addStr();
    }

    public ArrobaNumber(int value) {
        this.value = value * 1.0;
    }

    private void addStr() {
        addEquals();
        members.put("str", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return new ArrobaString(value.toString(), null, null);
            }

            @Override
            public String toString() {
                return "<Native Function> str()";
            }
        });
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

        addStr();
    }

    public ArrobaNumber(Double value) {
        this.value = value;
        addStr();
    }

    public static ArrobaNumber From(Double value) {
        return new ArrobaNumber(value);
    }

    public static ArrobaNumber False() {
        return ArrobaNumber.From(0.0);
    }

    public static ArrobaNumber True() {
        return ArrobaNumber.From(1.0);
    }

    public Boolean isByte() {
        return value == value.byteValue();
    }

    Boolean isInt() {
        return value == value.intValue();
    }

    @Override
    public String toString() {
        if (value == value.intValue()) {
            return "" + value.intValue();
        }

        return value.toString();
    }

    @Override
    public Boolean toBool() {
        return !value.equals(0.0);
    }

    @Override
    public Boolean equalsDatum(ArrobaDatum other) {
        if (other instanceof ArrobaNumber) {
            return ((ArrobaNumber) other).value.equals(value);
        }

        return super.equalsDatum(other);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ArrobaNumber && ((ArrobaNumber) obj).value.equals(value);
    }
}
