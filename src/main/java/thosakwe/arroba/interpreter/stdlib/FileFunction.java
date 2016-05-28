package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (args.get(0) instanceof ArrobaString) {
            ArrobaString path = (ArrobaString) args.get(0);
            return new ArrobaFile(path);
        }

        return new ArrobaException("File expects argument 1 to be a string");
    }

    @Override
    public String toString() {
        return "<Native Function> File()";
    }
}

class ArrobaFile extends ArrobaDatum {
    private File file;

    private static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    ArrobaFile(ArrobaString path) {
        try {
            file = new File(path.toString());

            members.put("create", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    Boolean result = false;

                    try {
                        result = file.createNewFile();
                    } catch (Exception exc) {
                        return new ArrobaException("Could not create file: " + file.getPath());
                    }

                    return new ArrobaNumber(result ? 1.0 : 0.0);
                }

                @Override
                public String toString() {
                    return "<Native Function> file.create()";
                }
            });
            members.put("delete", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    Boolean result = false;

                    try {
                        result = file.delete();
                    } catch (Exception exc) {
                        return new ArrobaException("Could not delete file: " + file.getPath());
                    }

                    return new ArrobaNumber(result ? 1.0 : 0.0);
                }

                @Override
                public String toString() {
                    return "<Native Function> file.create()";
                }
            });
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
            members.put("isDir", ArrobaNumber.False());
            members.put("path", new ArrobaPureString(file.getPath()));
            members.put("read", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    try {
                        return new ArrobaPureString(readFile(file.getPath(), Charset.defaultCharset()));
                    } catch (Exception exc) {
                        return new ArrobaException(exc);
                    }
                }

                @Override
                public String toString() {
                    return "<Native Function> file.read()";
                }
            });
            members.put("write", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    if (args.isEmpty()) {
                        return new ArrobaException("file.write expects 1 argument");
                    }

                    Boolean result = false;

                    if (!file.exists()) {
                        try {
                            //noinspection ResultOfMethodCallIgnored
                            file.createNewFile();
                        } catch (Exception exc) {
                            return new ArrobaException("Cannot create file " + file.getPath());
                        }
                    }

                    if (file.canWrite()) {
                        try {
                            PrintStream printStream = new PrintStream(file);
                            printStream.println(args.get(0).toString());
                            printStream.close();
                            result = true;
                        } catch (Exception exc) {
                            return new ArrobaException("Cannot write to " + file.getPath());
                        }
                    }

                    return new ArrobaNumber(result ? 1.0 : 0.0);
                }

                @Override
                public String toString() {
                    return "<Native Function> file.write(text)";
                }
            });
        } catch (Exception exc) {
            System.err.println("Warning: could not open file: " + path.toString());
        }
    }

    @Override
    public String toString() {
        return "<File:" + file.getPath() + ">";
    }
}