package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.interpreter.ArrobaFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ArrobaEvent extends ArrobaDatum {
    private List<ArrobaFunction> wiredListeners = new ArrayList<>();

    public ArrobaEvent() {
        members.put("emit", emit());
        members.put("listeners", listeners());
        members.put("on", on());
    }

    private ArrobaFunction emit() {
        ArrobaEvent parent = this;

        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty()) {
                    for (ArrobaFunction listener : wiredListeners) {
                        ExecutorService executorService = Executors.newFixedThreadPool(1);
                        FutureTask<ArrobaDatum> futureTask = new FutureTask<>(() -> {
                            ArrobaDatum result = listener.invoke(args);
                            executorService.shutdown();
                            return result;
                        });
                        listener.invoke(args);
                    }

                    return parent;
                }

                return new ArrobaException("Event.emit expects at least one argument");
            }

            @Override
            public String toString() {
                return "<Native Function> Event.emit(...events)";
            }
        };
    }

    private ArrobaFunction listeners() {
        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return new ArrobaArray(wiredListeners);
            }

            @Override
            public String toString() {
                return "<Native Function> Event.listeners()";
            }
        };
    }

    private ArrobaFunction on() {
        ArrobaEvent parent = this;

        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty() && args.get(0) instanceof ArrobaFunction) {
                    wiredListeners.add((ArrobaFunction) args.get(0));
                    return parent;
                }

                return new ArrobaException("Event.on expects argument 1 to be a function");
            }

            @Override
            public String toString() {
                return "<Native Function> Event.on(listener)";
            }
        };
    }
}
