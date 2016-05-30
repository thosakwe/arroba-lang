todos = [
    "More tests",
    # "Linter? Formatter?",
    "Compile to LLVM",
    "Sockets",
    "Actual for loops",
    "Run process task should use events",
    "Stack traces",
    "Null???",
    "UDP sockets",
    "Crypto?",
    "main.arr",
    "Arrays: where, first, last, reverse, join",
    "Break on uncaught errors",
    "Object literals",
    "And much more..."
]

pluralize = fn(num, singular, plural) {
    if (num == 1) {
        ret num + cat(" ") -> cat(singular)
    } else ret num -> cat(" ") -> cat(plural)
}

items = pluralize(todos.len(), "item", "items")

"The following is left to be done (${items}):" -> print

todos.all(fn(x) {
    print("    * ${x}")
})

# The to-do list for Arroba is written in Arroba... Sweet...