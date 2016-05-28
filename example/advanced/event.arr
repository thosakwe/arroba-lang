# Events allow you to reactively trigger actions.
# Several functions within Arroba include events you can subscribe to.

# To subscribe to an event and register a callback on it, just call its
# 'on' member.

# Event listeners are run asynchronously.

event.on(fn(e) {
    print("Got an event: ${e}")
})

# You can get an array of callbacks wired to an event via 'listeners()'.
event.listeners().all(fn(listener) {
    // listener is a callback function
})

# To create your own event emitter, just call the Event constructor.
event = Event()

event.on(fn(e) {
    print("Hello, evented ${e}!")
})

# To emit an actual event, call its emit member with an infinite amount
# of arguments.

event.emit("world")

# on and emit return the original Event instance, so you can chain calls.

event.emit("foo").emit("bar")