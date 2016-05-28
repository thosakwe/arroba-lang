todos = [
    # "Maybe ints",
    # "Fix regex speeds",
    "More tests",
    # "Directories",
    # "Array add/remove, also via operators",
    # "Linter? Formatter?",
    "Compile to LLVM",
    "Sockets",
    # "Concurrency (A.K.A. \"Tasks\")",
    # "While, better for loops, maybe...",
    # "Closures should preserve variables from scope",
    # "Error handling",
    "Run process task should use events",
    # "Error handling",
    "Stack trace",
    "Null???",
    "Chain tasks",
    "UDP sockets",
    "Crypto?",
    "JSON maybe",
    # "Equals",
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