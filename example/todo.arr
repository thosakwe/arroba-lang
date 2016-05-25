todos <- [
    "Maybe ints",
    "Fix regex speeds",
    "More tests",
    "Array add/remove, also via operators",
    "Strings to char array",
    "Linter? Formatter?",
    "Compile to LLVM",
    "Compile to C",
    "Compile to Javascript",
    "And much more..."
]

"The following is left to be done (${todos.len()} items):" -> print

todos.all(fn(x) {
    print("    * ${x}")
})

# The to-do list for Arroba is written in Arroba... Sweet...