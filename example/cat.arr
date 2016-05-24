# Arroba also gives you:
#   - argc:number
#   - argv:string[]

# Here is our cat program.
# It types out every file in argv.
# This cat program can even cat itself!

iter <- all(fn(file) {
    file.read() -> print
})

argv -> (all(File)) -> iter