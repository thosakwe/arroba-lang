# Importing the task library augments the built-in asynchrony support.
Task = import("<task>")

# Task.resolve is a convenience function, and returns a task carrying
# the given value.
task = Task.resolve(1337)

print(await task)