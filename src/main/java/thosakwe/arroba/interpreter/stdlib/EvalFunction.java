package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.cli.AstGen;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaException;
import thosakwe.arroba.interpreter.data.ArrobaString;

import java.util.List;

public class EvalFunction extends ArrobaFunction {
    ArrobaInterpreter interpreter;

    public EvalFunction(ArrobaInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (!args.isEmpty() && args.get(0) instanceof ArrobaString) {
            ArrobaParser parser = AstGen.makeParserFromText(args.get(0).toString());

            return interpreter.visitCompilationUnit(parser.compilationUnit());
        }

        return new ArrobaException("eval expects argument 1 to be a string");
    }

    @Override
    public String toString() {
        return "<Native Function> eval(code)";
    }
}
