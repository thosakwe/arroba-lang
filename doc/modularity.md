```
# my-class.arr

local:MyClass = fn(name) {
    local:this = any()

    this.name = name

    this.greet = () => "Hello, " + this.name + "!"

    ret this
}

ret MyClass
```

```
# main.arr

MyClass = import("my-class")

local:world = MyClass("world")
world.greet() -> print
```