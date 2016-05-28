exports = Task

Task.resolve = fn(val) {
    ret Task(() => val)
}

Task.reject = fn(err) {
    ret Task(fn() {
        throw err
    })
}

ret exports