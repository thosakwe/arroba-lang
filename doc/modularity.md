# Modularity

# Imports
We can import other code! Just call `import`. No need to include `.arr`.
***IMPORT*ANT: Imports are run relative to the working directory.**

```arroba
// Import a native module, found in inc/
Math = import("<math>")

// Import a local file, "stuff.arr"

Stuff = import("stuff")
```

# Exporting

Every Arroba script is a function in itself. Thus, we can export a
value to be used in other scripts.

JavaScript users will recognize this as a construct similar to
`module.exports`.

## Exporting Example

We can define a class in a separate file. This allows us to re-use the
same code in multiple places, though we only write it once.

```arroba
# my-class.arr

local:MyClass = fn(name) {
    local:this = any()

    this.name = name

    this.greet = () => "Hello, " + this.name + "!"

    ret this
}

exports = MyClass

```

A call to `import` will return whatever the last value within the
imported script was, which in this case is `MyClass`.

```
# main.arr

MyClass = import("my-class")

local:world = MyClass("world")
world.greet() -> print
```

# Cool Trick
You can actually omit the `exports =` pattern altogether. This shorter
version will save you a line or two of code, and you won't even have to name
the class within `my-class.arr`.

```arroba
# my-class.arr

fn(name) {
    local:this = any()

    this.name = name

    this.greet = () => "Hello, " + this.name + "!"

    ret this
}
```

You can also use `ret`, or assign the exported value to any ID.
