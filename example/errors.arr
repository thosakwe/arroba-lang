# Tasks can return errors, too.

task volatile = Task(fn() {
    ret Exception("We can return or throw exceptions. Cool.")
})

volatile.then(fn() {
    print("This callback will never be run")
}).catch(fn(exc) {
    printErr(exc.msg)
}).run()