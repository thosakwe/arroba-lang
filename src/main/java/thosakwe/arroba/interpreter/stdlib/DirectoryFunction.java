package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryFunction extends ArrobaFunction {
    @Override
    public ArrobaDatum invoke(List<ArrobaDatum> args) {
        if (args.get(0) instanceof ArrobaString) {
            ArrobaString path = (ArrobaString) args.get(0);
            return new ArrobaDirectory(path);
        }

        return new ArrobaException("Directory expects argument 1 to be a string");
    }

    @Override
    public String toString() {
        return "<Native Function> Directory()";
    }
}

class ArrobaDirectory extends ArrobaDatum {
    private File dir;

    ArrobaDirectory(ArrobaString path) {
        try {
            dir = new File(path.toString());

            members.put("create", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    Boolean result = false;

                    try {
                        result = dir.mkdir();
                    } catch (Exception exc) {
                        return new ArrobaException("Could not create dir: " + dir.getPath());
                    }

                    return new ArrobaNumber(result ? 1.0 : 0.0);
                }

                @Override
                public String toString() {
                    return "<Native Function> dir.create()";
                }
            });
            members.put("delete", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    Boolean result = false;

                    try {
                        result = dir.delete();
                    } catch (Exception exc) {
                        return new ArrobaException("Could not delete dir: " + dir.getPath());
                    }

                    return new ArrobaNumber(result ? 1.0 : 0.0);
                }

                @Override
                public String toString() {
                    return "<Native Function> dir.create()";
                }
            });
            members.put("exists", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    Double value = dir.exists() ? 1.0 : 0.0;
                    return new ArrobaNumber(value);
                }

                @Override
                public String toString() {
                    return "<Native Function> dir.exists()";
                }
            });
            members.put("isDir", ArrobaNumber.True());
            members.put("path", new ArrobaPureString(dir.getPath()));
            members.put("ls", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    List<ArrobaDatum> arrobaFiles = new ArrayList<>();
                    File[] files = dir.listFiles();

                    if (files == null) {
                        return new ArrobaException("Could not list directory: " + dir.getPath());
                    }

                    for (File file : files) {
                        if (file.isDirectory()) {
                            arrobaFiles.add(new ArrobaDirectory(new ArrobaPureString(file.getPath())));
                        } else arrobaFiles.add(new ArrobaFile(new ArrobaPureString(file.getPath())));
                    }

                    return new ArrobaArray(arrobaFiles);
                }

                @Override
                public String toString() {
                    return "<Native Function> dir.files()";
                }
            });
            members.put("resolve", new ArrobaFunction() {
                @Override
                public ArrobaDatum invoke(List<ArrobaDatum> args) {
                    if (args.isEmpty()) {
                        return new ArrobaException("dir.write expects 1 argument");
                    } else if (!(args.get(0) instanceof ArrobaString)) {
                        return new ArrobaException("dir.resolve expects argument 1 to be a string");
                    }

                    File targetFile = new File(dir, args.get(0).toString());
                    return new ArrobaFile(new ArrobaPureString(targetFile.getPath()));
                }

                @Override
                public String toString() {
                    return "<Native Function> dir.resolve(path)";
                }
            });
        } catch (Exception exc) {
            System.err.println("Warning: could not open dir: " + path.toString());
        }
    }

    @Override
    public String toString() {
        return "<Directory:" + dir.getPath() + ">";
    }
}