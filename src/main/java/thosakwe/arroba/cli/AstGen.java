package thosakwe.arroba.cli;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import thosakwe.arroba.antlr.ArrobaLexer;
import thosakwe.arroba.antlr.ArrobaParser;
import thosakwe.arroba.antlr.json.JSONLexer;
import thosakwe.arroba.antlr.json.JSONParser;
import thosakwe.arroba.interpreter.stdlib.ArrobaJsonParser;

import java.io.IOException;

public class AstGen {
    public static ArrobaParser.CompilationUnitContext makeAst(String fileName) {
        try {
            ANTLRFileStream fileStream = new ANTLRFileStream(fileName);
            ArrobaLexer lexer = new ArrobaLexer(fileStream);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            ArrobaParser parser = new ArrobaParser(tokenStream);
            parser.setBuildParseTree(true);
            return parser.compilationUnit();
        } catch (IOException e) {
            System.err.println("fatal error: could not open file: " + fileName);
            //e.printStackTrace();
            return null;
        }
    }

    public static ArrobaParser makeParserFromText(String text) {
        ANTLRInputStream fileStream = new ANTLRInputStream(text);
        ArrobaLexer lexer = new ArrobaLexer(fileStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ArrobaParser parser = new ArrobaParser(tokenStream);
        parser.setBuildParseTree(true);
        return parser;
    }

    public static JSONParser makeJsonParserFromText(String text) {
        ANTLRInputStream fileStream = new ANTLRInputStream(text);
        JSONLexer lexer = new JSONLexer(fileStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokenStream);
        parser.setBuildParseTree(true);
        return parser;
    }
}
