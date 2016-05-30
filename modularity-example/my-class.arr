# my-class.arr

exports = fn(name) {
    local:this = any()

    this.name = name

    this.greet = () => "Hello, " + this.name + "!"

    ret this
}