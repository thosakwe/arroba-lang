package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.ArrobaDatum;
import thosakwe.arroba.interpreter.data.ArrobaNumber;
import thosakwe.arroba.interpreter.data.ArrobaPureString;
import thosakwe.arroba.interpreter.data.ArrobaString;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (args.get(0) instanceof ArrobaString) {
            ArrobaString path = (ArrobaString) args.get(0);
            return new ArrobaFile(path);
        }

        System.err.println("File expects argument 1 to be a string");
        return null;
    }

    @Override
    public String toString() {
        return "<Native Function> File()";
    }
}

class ArrobaFile extends ArrobaDatum {
    private File file;
    private Scanner scanner;

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    ArrobaFile(ArrobaString path) {
        try {
            file = new File(path.toString());
            scanner = new Scanner(file);

            members.put("exists", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    Double value = file.exists() ? 1.0 : 0.0;
                    return new ArrobaNumber(value);
                }

                @Override
                public String toString() {
                    return "<Native Function> file.exists()";
                }
            });
            members.put("path", new ArrobaString(file.getPath(), null, null));
            members.put("read", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    try {
                        return new ArrobaPureString(readFile(file.getPath(), Charset.defaultCharset()));
                    } catch (Exception exc) {
                        System.err.println(exc.toString());
                        return null;
                    }
                }

                @Override
                public String toString() {
                    return "<Native Function> file.read()";
                }
            });
        } catch (Exception exc) {
            System.err.println("Could not open file: " + path.toString());
        }
    }

    @Override
    public String toString() {
        return "<File:" + file.getPath() + ">";
    }
}