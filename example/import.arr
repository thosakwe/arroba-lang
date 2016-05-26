# The import function is a bit of magic in its own right.
# Calling it will return any value returned by the given file.
# Calling it with multiple arguments will return an array.

# Any code in the given file is run in a separate interpreter.
# You get to keep defined variables.

# Using this with any() allows a namespace-like construct.

doIt = import("example/import_me.arr")
doIt()

# You can import files bundled with arroba via
# import <lib>

Math = import("<math>")
print(Math.pow(2, 5))