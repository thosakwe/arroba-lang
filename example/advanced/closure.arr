# Function expressions in Arroba are closures, just like in Javascript.
# They can use variables declared in another scope.

# For example:

makeClosure = fn() {
    local:name = "world"

    ret fn() {
        print("Hello, ${name}!")
    }
}

# name is a value local to makeClosure, so we can't use it outside of the function.
# For example, printing name would print nothing, because name is null in this
# scope.
print(name)

# However...
closure = makeClosure()

# The value of name set within makeClosure is preserved within our closure object.
closure()

# This will print "Hello, world!"