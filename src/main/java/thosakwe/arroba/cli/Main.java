package thosakwe.arroba.cli;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;
import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;
import thosakwe.arroba.interpreter.data.ArrobaArray;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaException;
import thosakwe.arroba.interpreter.data.ArrobaString;
import thosakwe.arroba.interpreter.stdlib.PrintFunction;
import thosakwe.arroba.js.JsTranspiler;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Options compilerOptions = makeCompilerOptions();

        if (args.length == 0 || args[0].toLowerCase().trim().equals("-h") || args[0].toLowerCase().trim().equals("--help")) {
            new HelpFormatter().printHelp("arroba [options...] <file>", compilerOptions);
            return;
        }

        CommandLine cliOptions = new DefaultParser().parse(compilerOptions, args);
        String[] cliArgs = cliOptions.getArgs();

        if (cliOptions.hasOption("repl")) {
            runRepl(cliArgs, cliOptions);
            return;
        } else if (cliOptions.getOptionValue('i') == null) {
            System.err.println("fatal error: no input file");
            System.exit(1);
        }

        ArrobaParser.CompilationUnitContext ast = AstGen.makeAst(cliOptions.getOptionValue('i'));

        if (ast != null) {
            if (cliOptions.hasOption("js")) {
                JsTranspiler transpiler = new JsTranspiler(cliOptions);
                ParseTreeWalker.DEFAULT.walk(transpiler, ast);
            } else {
                ArrobaInterpreter interpreter = new ArrobaInterpreter(cliArgs);
                interpreter.visitCompilationUnit(ast);
            }
        }
    }

    private static Options makeCompilerOptions() {
        Options result = new Options();
        result.addOption("d", "debug", false, "Enable debug output.");
        result.addOption("i", "in", true, "The input file to be parsed.");
        result.addOption("a", "repl", false, "Starts the Arroba REPL.");
        result.addOption("js", "javascript", false, "Enables JavaScript output.");
        return result;
    }

    private static void runRepl(String[] args, CommandLine cliOptions) {
        ArrobaInterpreter interpreter = new ArrobaInterpreter(args);
        Scanner scanner = new Scanner(System.in);
        PrintFunction print = new PrintFunction();

        System.out.print("arroba> ");
        while (scanner.hasNextLine()) {
            try {
                ArrobaParser parser = AstGen.makeParserFromText(scanner.nextLine());
                ArrobaDatum result = interpreter.visitCompilationUnit(parser.compilationUnit());
                dumpResult(result, print, true);
            } catch (Exception exc) {
                System.err.println("Error: " + exc.getMessage());

                if (cliOptions.hasOption("debug")) {
                    exc.printStackTrace(System.err);
                }
            }
            System.out.print("arroba> ");
        }
    }

    private static void dumpResult(ArrobaDatum result, PrintFunction print, Boolean printLine) {
        if (result == null) {
            System.out.println("<null>");
        } else if (result instanceof ArrobaArray) {
            System.out.print("[");
            List<ArrobaDatum> items = ((ArrobaArray) result).items;

            for (int i = 0; i < items.size(); i++) {
                if (i > 0)
                    System.out.print(", ");
                System.out.print(items.get(i));
            }

            System.out.println("]");
        } else if (result instanceof ArrobaException) {
            System.err.println(result);
        } else if (false && result instanceof ArrobaString){
            System.out.println("\"" + result + "\"");
        } else {
            print.invoke(result);
            if (false) {
                if (result.members.size() == 0) {
                    System.out.println("{}");
                    return;
                }
                System.out.print("{");

                int k = 0;
                for (String key : result.members.keySet()) {

                    if (k > 0)
                        System.out.print(", ");
                    System.out.print(key + ": ");

                    ArrobaDatum found = result.members.get(key);
                    dumpResult(found, print, false);
                    k++;
                }

                System.out.print("}");

                if (printLine)
                    System.out.println();
            }
        }
    }
}
