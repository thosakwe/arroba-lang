# Asynchrony Support
Arroba has friendly asynchrony support baked in as well.

You can run any function on a separate thread by wrapping
it in a `Task`.

Callbacks and error handlers can be wired to a task via `ok` and `fail`.
Calling `run` will start the work in a new thread, without blocking.

```arroba
local:sayHi = Task(() => print("Hello, world!"))

sayHi.ok(fn(msg) {
    // Success...
}).fail(fn(exc) {
    // Failure...
}).run()
```

# Synchronicity...
(Yes, I misused the word. I am aware. :P)

Believe it or not, a `Task` will **only** run in a new thread if you can `run`.
If you change your mind and decide to run the task synchronously, call `yield`
instead.

We also have a nice syntactic sugar available, `await`...

```arroba
task = Task((x) => x * 2)

local:leet = task.yield(4)
leet = await task(4)

// Both will return 8.
```

`await` is preferable, whenever possible.

# Arguments
As you can see, you can pass arguments to `run`, `yield` or an `await` expression,
and those arguments will be run on the function in question.

# Additional Goodies
You can augment the default `Task` class by importing the `"<task>"` module.
This exposes `resolve` and `reject` functions similar to those of JavaScript
`Promises`.

```arroba
Task = import("<task>")

local:three = await Task.resolve(3)

try {
    await Task.reject("Oops")
} catch(exc) {
    // Oops...
}
```