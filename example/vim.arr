if (argv.len() > 0) {
    filename = argv[0]
} else filename = input("Enter a filename to begin: ")

print("Enter text to your heart's desire.")
print("Type \"EOF\" to end input.\n")

file = File(filename)

if (file.exists()) {
    if (file.delete() == false) {
        print("Couldn't delete existing file: ${filename}")
        quit(1)
    }
}

if (file.create() == false) {
    print("Couldn't create file: ${filename}")
    quit(1)
}

str = ""

while(str != "EOF") {
    local:text = file.read()
    str = input()

    if (str != "EOF") {
        if (file.write(text + str) == false) {
            ret 1
        }
    }
}