package thosakwe.arroba.interpreter.data;

import thosakwe.arroba.interpreter.ArrobaFunction;
import thosakwe.arroba.interpreter.stdlib.ArrobaTaskResult;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ArrobaTask extends ArrobaDatum {
    private ArrobaFunction runFunc;
    private ExecutorService executorService = Executors.newFixedThreadPool(255);

    private ArrobaFunction thener;
    private ArrobaFunction catcher;

    public ArrobaTask(ArrobaFunction runFunc) {
        ArrobaTask parent = this;
        this.runFunc = runFunc;

<<<<<<< HEAD
        members.put("fail", new ArrobaFunction() {
=======
        members.put("catch", new ArrobaFunction() {
>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty() && args.get(0) instanceof ArrobaFunction) {
                    catcher = (ArrobaFunction) args.get(0);
                    return parent;
                }

<<<<<<< HEAD
                System.err.println("task.fail expects argument 1 to be a function");
=======
                System.err.println("task.catch expects argument 1 to be a function");
>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
                return null;
            }

            @Override
            public String toString() {
<<<<<<< HEAD
                return "<Native Function> task.fail(handler)";
=======
                return "<Native Function> task.catch(catcher)";
>>>>>>> 1ee8b1c0a3f6e1d6b40644fab3f9a010059b406f
            }
        });
        members.put("then", new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                if (!args.isEmpty() && args.get(0) instanceof ArrobaFunction) {
                    thener = (ArrobaFunction) args.get(0);
                    return parent;
                }

                System.err.println("task.then expects argument 1 to be a function");
                return null;
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
                    FutureTask<ArrobaDatum> futureTask = makeTask(args);
                    executorService.execute(futureTask);
                    return ArrobaNumber.True();
                } catch (Exception exc) {
                    return ArrobaNumber.False();
                }
            }

            @Override
            public String toString() {
                return "<Native Function> task.run(...args)";
            }
        });

        members.put("yield", yield());
    }

    public ArrobaFunction yield() {
        return new ArrobaFunction() {
            @Override
            public ArrobaDatum invoke(List<ArrobaDatum> args) {
                try {
                    Future<ArrobaDatum> future = executorService.submit(() -> runFunc.invoke(args));
                    ArrobaDatum result = future.get();
                    executorService.shutdown();
                    return result;
                } catch (Exception exc) {
                    System.err.println("Task run failure: " + exc.getMessage());
                    executorService.shutdown();
                    return new ArrobaException(exc);
                }
            }

            @Override
            public String toString() {
                return "<Native Function> task.yield(...args)";
            }
        };
    }

    private FutureTask<ArrobaDatum> makeTask(List<ArrobaDatum> args) {
        return new FutureTask<>(() -> {
            try {
                ArrobaDatum result = runFunc.invoke(args);
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
        return "<Task:" + runFunc + ">";
    }
}
