package thosakwe.arroba.interpreter.stdlib;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.data.*;

import java.util.ArrayList;
import java.util.List;

public class ProcessTask extends ArrobaTask {
    @Override
    public ArrobaFunction yield() {
        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty() && args.get(0) instanceof ArrobaString) {
                    String processName = args.get(0).toString();
                    List<String> processArgs = new ArrayList<>();

                    for (int i = 1; i < args.size(); i++) {
                        if (args.get(i) instanceof ArrobaString) {
                            processArgs.add(args.get(i).toString());
                        } else if (args.get(i) instanceof ArrobaArray) {
                            List<ArrobaDatum> items = ((ArrobaArray) args.get(i)).items;

                            for (int j = 0; j < items.size(); j++) {
                                if (items.get(j) instanceof ArrobaString) {
                                    processArgs.add(items.get(j).toString());
                                } else {
                                    return new ArrobaException("Process expects " +
                                            "item " + (j + 1) + " within argument "
                                            + (i + 1) + " to be a string.");
                                }
                            }
                        } else return new ArrobaException("Process expects argument " + (i + 1) + " to be a string.");
                    }

                    try {
                        Process process = Runtime.getRuntime().exec(processName, processArgs.toArray(new String[processArgs.size()]));
                        return new ArrobaProcess(process, processName, process.waitFor());
                    } catch (Exception exc) {
                        return new ArrobaException(exc);
                    }
                }

                return new ArrobaException("Process expects argument 1 to be a string.");
            }

            @Override
            public String toString() {
                return "<Native Function> Process()";
            }
        };
    }

    @Override
    public String toString() {
        return "<Task:Function(name, ...args?)>";
    }
}
