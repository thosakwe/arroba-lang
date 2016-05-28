package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.antlr.json.JSONBaseVisitor;
import thosakwe.arroba.antlr.json.JSONParser;
import thosakwe.arroba.cli.AstGen;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

class ArrobaJSON extends ArrobaDatum {
    ArrobaJSON() {
        members.put("parse", parse());
        members.put("stringify", stringify());
    }

    private ArrobaFunction parse() {
        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty() && args.get(0) instanceof ArrobaString) {
                    JSONParser parser = AstGen.makeJsonParserFromText(args.get(0).toString());
                    ArrobaJsonParser jsonParser = new ArrobaJsonParser();
                    return jsonParser.visitJson(parser.json());
                }

                return new ArrobaException("JSON.parse expects argument 1 to be a string.");
            }

            @Override
            public String toString() {
                return "<Native Function> JSON.parse()";
            }
        };
    }

    private ArrobaFunction stringify() {
        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty())
                    return new ArrobaPureString(stringify(args.get(0)));
                return new ArrobaPureString("<null>");
            }

            @Override
            public String toString() {
                return "<Native Function> JSON.stringify(x)";
            }
        };
    }

    private String stringify(ArrobaDatum datum) {
        if (datum instanceof ArrobaArray)
            return stringifyArray((ArrobaArray) datum);
        else if (datum instanceof ArrobaNumber) {
            Double value = ((ArrobaNumber) datum).value;
            if (value == 1.0)
                return "true";
            else if (value == 0.0)
                return "false";
            return "\"" + datum.toString() + "\'";
        } else if (datum instanceof ArrobaString)
            return "\"" + datum.toString() + "\"";
        else if (datum == null)
            return "null";
        return stringifyObject(datum);
    }

    private String stringifyArray(ArrobaArray array) {
        String result = "[";
        int added = 0;
        for (int i = 0; i < array.items.size(); i++) {
            if (array.items.get(i) instanceof ArrobaFunction)
                continue;
            if (added > 0)
                result += ",";
            result += stringify(array.items.get(i));
            added++;
        }
        return result + "]";
    }

    private String stringifyObject(ArrobaDatum obj) {
        String result = "{";
        String[] keys = obj.members.keySet().toArray(new String[obj.members.size()]);
        int added = 0;

        for (int i = 0; i < obj.members.size(); i++) {
            ArrobaDatum value = obj.members.get(keys[i]);
            if (value instanceof ArrobaFunction)
                continue;
            if (added > 0)
                result += ",";
            result += "\"";
            result += keys[i];
            result += "\":";
            result += stringify(value);
            added++;
        }
        return result + "}";
    }
}

