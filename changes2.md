# Planned Changes for 2.0
* Formatting tool?
* Linter/Analysis tool?
* Better IO API's
* Stack traces, redone scoping and `local`
* Better number handling, and `byte`
* Task-based Stream API
* Better freestanding libraries (Math, etc...)
* Include path
* **`null` and ternary**
* Documentation support, documentation tool
* All features from arroba2js

# Perhaps in 3.0?
* Compile to JVM
* Extend and interface with Arroba compiler via Java

# New Language Features

## Metadata

## New Keyword

When called, will call the given factory constructor with a blank datum, and any
additional arguments you pass. The constructor will no longer need to return `this`.

```arroba
fn MyClass(this) {
    this.greet = () => "Hello, ${this.name}!";
}
```

You can also use the `@extend` metadatum to extend from an existing constructor. In
this case, the `this` datum will first be passed through any parent constructors.

```arroba
@extend(MyClass)
fn ChildClass(this) {
    local:_oldGreet = this.greet
    
    // Easily override
    this.greet = () => "Overridden from old ${_oldGreet()}"
}
```

## argv and foreach

```arroba
fn main(args) {
    foreach (arg: args) {
        "Found arg: ${arg}" -> print
    }
    
    // Can also get index
    foreach (arg, i : args) {
        "Arg #${i + 1}: ${arg}" -> print
    }
}
```

## Not Everything is a Function

```arroba
local:arr = [];

// 'len' is no longer a function!!!
arr.len -> print
```

## Rest params
```arroba
fn sum(nums...) {
    local:sum = 0
    
    foreach (num: nums)
        sum += num
    
    ret sum
}
```

## Unary Operators, Assignment Expressions

## Operator Functions

## Datum Literals

```arroba
local:json = {body, foo: "bar", "string": "some other string"}
```
