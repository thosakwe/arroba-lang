package thosakwe.arroba.cli;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;
import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.ArrobaInterpreter;

public class Main {
    public static void main(String[] args) throws ParseException {
        Options compilerOptions = makeCompilerOptions();

        if (args.length == 0 || args[0].toLowerCase().trim().equals("-h") || args[0].toLowerCase().trim().equals("--help")) {
            new HelpFormatter().printHelp("arroba [options...] <file>", compilerOptions);
            return;
        }

        CommandLine cliOptions = new DefaultParser().parse(compilerOptions, args);
        String[] cliArgs = cliOptions.getArgs();

        if (cliArgs.length == 0) {
            System.err.println("fatal error: no input file");
            System.exit(1);
        }

        ArrobaParser.CompilationUnitContext ast = AstGen.makeAst(cliArgs[0]);

        if (ast != null) {
            ArrobaInterpreter interpreter = new ArrobaInterpreter();
            ParseTreeWalker.DEFAULT.walk(interpreter, ast);
        }
    }

    static Options makeCompilerOptions() {
        Options result = new Options();
        result.addOption("d", "debug", false, "Enable debug output.");
        return result;
    }
}
