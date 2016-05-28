
# Wrap code in  try-catch block to preemptively handle errors.

try {
    throw 2
} catch(exc) {
    # Caught exceptions will always have a .msg and .stackTrace
    # property. If something other than an exception was thrown,
    # then it will be available via exc.value.

    printErr("${exc.value} = 2")
}

# Tasks can return errors, too.

volatile = Task(fn() {
    # You can also create exceptions via Exception(msg?, value?, stackTrace?).
    # In this case, you can even just 'ret' the exception and it will be caught.


# Tasks can return errors, too.

task volatile = Task(fn() {

    ret Exception("We can return or throw exceptions. Cool.")
})

volatile.then(fn() {
    print("This callback will never be run")

}).fail(fn(exc) {
    printErr(exc.msg)
}).run()

# You can handle errors while awaiting, as well.

iThrow = Task(fn(x) {
    throw x * 3
})

Task(fn() {
    try {
        local:result = await iThrow(12)
        print("This code is never run")
    } catch(exc) {
        printErr("Something is wrong: ${exc.value}")
    }

}).catch(fn(exc) {
    printErr(exc.msg)

}).run()