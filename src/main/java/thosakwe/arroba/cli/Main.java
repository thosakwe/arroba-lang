package thosakwe.arroba.cli;

import org.apache.commons.cli.*;
import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.ArrobaInterpreter;
import thosakwe.arroba.interpreter.data.ArrobaArray;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaException;
import thosakwe.arroba.interpreter.stdlib.PrintFunction;

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
            ArrobaInterpreter interpreter = new ArrobaInterpreter(cliArgs);
            //ParseTreeWalker.DEFAULT.walk(interpreter, ast);
            interpreter.visitCompilationUnit(ast);
        }
    }

    private static Options makeCompilerOptions() {
        Options result = new Options();
        result.addOption("d", "debug", false, "Enable debug output.");
        result.addOption("i", "in", true, "The input file to be parsed.");
        result.addOption("a", "repl", false, "Starts the Arroba REPL.");
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
                dumpResult(result, print);
            } catch (Exception exc) {
                System.err.println("Error: " + exc.getMessage());

                if (cliOptions.hasOption("debug")) {
                    exc.printStackTrace(System.err);
                }
            }
            System.out.print("arroba> ");
        }
    }

    private static void dumpResult(ArrobaDatum result, PrintFunction print) {
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
        } else print.invoke(result);
    }
}
