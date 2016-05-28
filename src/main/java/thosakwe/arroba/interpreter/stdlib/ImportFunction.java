package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.cli.AstGen;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;
import thosakwe.arroba.interpreter.data.ArrobaArray;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportFunction extends ArrobaFunction {
    ArrobaInterpreter interpreter;
    private File incDir = new File("./inc").getCanonicalFile();

    public ImportFunction(ArrobaInterpreter interpreter) throws IOException {
        this.interpreter = interpreter;
    }

    private String makeImportPath(String importer) {
        if (importer.startsWith("<") && importer.endsWith(">")) {
            return new File(incDir, importer.replaceAll("(^<)|(>$)", "") + ".arr").getAbsolutePath();

        } else if (!importer.endsWith(".arr")) return importer + ".arr";
        else return importer;

    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (!args.isEmpty()) {
            interpreter.createChildScope();
            if (args.size() == 1) {
                if (args.get(0).toString().equals("<util>")) {
                    return new ArrobaUtil();
                }

                String path = makeImportPath(args.get(0).toString());
                ArrobaParser.CompilationUnitContext ast = AstGen.makeAst(path);
                //interpreter.dumpScopes();
                return interpreter.visitCompilationUnit(ast);
            } else {
                List<ArrobaDatum> results = new ArrayList<>();

                for (ArrobaDatum importer : args) {
                    ArrobaParser.CompilationUnitContext ast = AstGen.makeAst(makeImportPath(importer.toString()));
                    results.add(interpreter.visitCompilationUnit(ast));
                }

                return new ArrobaArray(results);
            }
        }

        return new ArrobaException("import expects at least one argument");
    }

    @Override
    public String toString() {
        return "<Native Function> import(...paths)";
    }
}
