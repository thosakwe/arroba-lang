exports = Task

Task.resolve = (val) => Task(() => val)
Task.reject = (val) => Task(() => Exception(val))

ret exports