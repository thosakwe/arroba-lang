# An Arroba REPL... Written in Arroba.

while (true) {
    try {
        local:result = eval(input("arroba>"))
        print(result)
    } catch (exc) {
        printErr(exc)
    }
}

ret 0
