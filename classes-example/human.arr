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