# Functions
Functions in Arroba are first-class citizens.

# Transformers
Many Arroba functions take a single argument and return a modified version
of that argument. These are known as `transformers`. One example is the `rgx`
function. It takes an input string and transforms it into a match, or an
array of matches.

When using transformers, try to pass the argument via
[piping](piping.md).

# Return
Functions can return a value. To return a value, just leave alone on a line
to itself.

Optionally, you can prefix it with `ret`.

```arroba
# These two are semantically the same.

local:myFunc = fn() {
    "Hello, world!"
}


myFunc = fn() {
    ret "Hello, world!"
}
```

# Inline Functions (Expression Lambdas)
Arroba also supports expression lambdas, which are perfect for implementing
transformers.

```arroba
local:add = (x, y) => x + y

local:four = add(1, 3)
```

# Scoping
Arroba functions are closures, so they can access values from the nearest
scope, even when called from outside that scope.

Even interpolated strings work.

```arroba

local:foo = fn() {
    local:bar = "baz"
    ret fn() {
        printErr("bar is still ${bar}")
    }
}

local:closure = foo()
closure()
```