# Classes
They're not real in Arroba! Sorry.

However, you can create factory constructors that get the job done.
The beauty of it is that you get proper scoping support, i.e. `this`
points to the real `this`.

The basic pattern is as follows:

```arroba
# animal.arr

exports = fn(genus, species) {
    local:this = any()

    this.genus = genus
    this.species = species
    this.printTaxonomy = fn() {
        print("This animal is a ${this.genus} ${this.species}.")
    }

    ret this
}
```

```arroba
# flying-fox.arr
Animal = import("animal")

local:largeFlyingFox = Animal("pteropus", "vampyrus")
largeFlyingFox.printTaxonomy()
```

# Inheritance
As classes do not exist in Arroba, nor can inheritance. However, we can
model it.

```arroba
# human.arr

Animal = import("animal")

exports = fn(name) {
    local:this = Animal("homo", "sapiens")

    this.name = name
    this.introduce = fn() {
        print("Hi! My name is ${this.name}.")
    }

    ret this
}
```

```arroba
# main.arr

Human = import("human")

local:human = Human("Bob")
human.printTaxonomy()
human.introduce()
```