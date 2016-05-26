# Tasks allow you to do stuff asynchronously.
# Create a Task by passing a function to the constructor.

task = Task((x) => x + 2)

# You can add a callback.
task.then(fn(x) {
    print("Asynchronously: " -> cat(x))
})

# And an error handler.
# The error will receive a string, the error message.

task.catch(printErr)

# Fire the task, asynchronously.
task.run(418)

# You can run the task and have it block.
# Just use yield.

task = Task((x) => x * 5)

local:num = task.yield(32)
"Blocked: ${num}" -> print

# Or, by using await

task = Task(() => "I awaited this string!")
print(await task())

# You can also pass arguments to await

task = Task((x) => x / 4)
four = await task(16)
print(four)