# You can also run processes in Arroba.

process = await Process("echo", "http://google.com")

# Process accepts an infinite amount of string arguments.
# You can also pass an array of arguments.

processName = input("Enter a process name to run:")
processArgs = []

prompt = "Enter an argument. Leave the line blank when you are done."
arg = input(prompt)

while(arg != "") {
    processArgs.add(arg)
    arg = input(prompt)
}

try {
    process = await Process(processName, processArgs)

    # You can retrieve an exit code:
    print("Exit code: ${process.code}")

    # And standard output

    if (process.stdout.len() > 0) {
        print("${processName} output:\n")
        print(process.stdout)
    }

    # And standard errors

    if (process.stderr.len() > 0) {
        printErr("${processName} errors:\n")
        printErr(process.stderr)
    }
} catch(exc) {
    printErr("Could not run process. :(")
    printErr(exc.msg)
}

