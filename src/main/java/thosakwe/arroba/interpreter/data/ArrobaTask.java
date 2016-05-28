package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.stdlib.ArrobaTaskResult;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ArrobaTask extends ArrobaDatum {
    private ArrobaFunction runFunc;

    private ArrobaFunction thener;
    public ArrobaFunction catcher;

    public ArrobaTask() {
        addMembers();
    }

    public ArrobaTask(ArrobaFunction runFunc) {
        this.runFunc = runFunc;
        addMembers();
    }

    private void addMembers() {
        ArrobaTask parent = this;
        ArrobaFunction run = yield();

        members.put("fail", new ArrobaFunction() {

            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty() && args.get(0) instanceof ArrobaFunction) {
                    catcher = (ArrobaFunction) args.get(0);
                    return parent;
                }


                return new ArrobaException("task.fail expects argument 1 to be a function");
            }

            @Override
            public String toString() {
                return "<Native Function> task.fail(handler)";
            }
        });
        members.put("ok", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty() && args.get(0) instanceof ArrobaFunction) {
                    thener = (ArrobaFunction) args.get(0);
                    return parent;
                }

                return new ArrobaException("task.then expects argument 1 to be a function");
            }

            @Override
            public String toString() {
                return "<Native Function> task.then(catcher)";
            }
        });

        members.put("run", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                try {
                    ExecutorService executorService = Executors.newFixedThreadPool(1);
                    Future<ArrobaDatum> future = makeTask(args, executorService);
                    Thread thread = new Thread(new ArrobaTaskThread(future));
                    thread.start();
                    return parent;
                } catch (Exception exc) {
                    return new ArrobaException(exc);
                }
            }

            @Override
            public String toString() {
                return "<Native Function> task.run(...args)";
            }
        });

        members.put("yield", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                return run.invoke(args);
            }

            @Override
            public String toString() {
                return "<Native Function> task.yield(...args)";
            }
        });
    }

    public ArrobaFunction yield() {
        return runFunc;
    }

    private Future<ArrobaDatum> makeTask(List<ArrobaDatum> args, ExecutorService executorService) {
        ArrobaFunction run = yield();

        return executorService.submit(() -> {
            try {
                ArrobaDatum result = run.invoke(args);
                if (result instanceof ArrobaException && catcher != null) {
                    catcher.invoke(result);
                } else if (thener != null) {
                    thener.invoke(result);
                }

                executorService.shutdown();
                return new ArrobaTaskResult(true);
            } catch (Exception exc) {
                if (catcher != null) {
                    catcher.invoke(new ArrobaException(exc));
                }
                executorService.shutdown();
                return new ArrobaTaskResult(false);
            }
        });
    }

    @Override
    public String toString() {
        if (runFunc != null)
            return "<Task:" + runFunc + ">";
        else return "<Task>";
    }
}

class ArrobaTaskThread extends Thread {
    private final Future<ArrobaDatum> toRun;

    ArrobaTaskThread(Future<ArrobaDatum> toRun) {
        this.toRun = toRun;
    }

    @Override
    public void run() {
        try {
            toRun.get();
        } catch (Exception exc) {
            // Nothing
        }
    }
}