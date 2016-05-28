# Importing the task library augments the built-in asynchrony support.
Task = import("<task>")

# Task.resolve is a convenience function, and returns a task carrying
# the given value.
task = Task.resolve(1337)

leet = await task()

# Task.reject will automatically throw the given value.

task = Task.reject("Oops")

try {
    await task()
    print("This code will never be run")
} catch(exc) {
    printErr("${exc.msg}! I did it again!")
}