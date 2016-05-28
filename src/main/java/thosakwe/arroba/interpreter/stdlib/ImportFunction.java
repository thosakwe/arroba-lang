package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.cli.AstGen;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;
import thosakwe.arroba.interpreter.data.ArrobaArray;
import thosakwe.arroba.interpreter.data.ArrobaDatum;

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
<<<<<<< HEAD
        } else if (!importer.endsWith(".arr")) return importer + ".arr";
        else return importer;
=======
        } else return importer;
>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
    }

    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (!args.isEmpty()) {
            interpreter.createChildScope();
            if (args.size() == 1) {
                ArrobaParser.CompilationUnitContext ast = AstGen.makeAst(makeImportPath(args.get(0).toString()));
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

        System.err.println("import expects at least one argument");
        return null;
    }

    @Override
    public String toString() {
        return "<Native Function> import(...paths)";
    }
}
