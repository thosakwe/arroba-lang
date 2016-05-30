# Events
Arroba also has native support for events.

Events are more like event *emitters*. They can broadcast a value
(or values), and anybody listening can respond reactively.

To create an event, simply use the `Event` constructor.

```arroba
local:ev = Event()
```

You can attach a listener to an event via `on`.

```arroba
ev.on(fn(e) {
    // We can chain this...
}).on(fn(msg) {
    print("Hello, ${msg}!")
})
```

Events can have multiple listeners, which can be retrieved via
`listeners`.

```arroba
ev.listeners().all(fn(listener) {
    // Do something...
})
```

Lastly, to fire an event and have it propagate on all listeners, call
`emit`, with an infinite number of arguments.

```arroba
// This will print "Hello, world!"

ev.emit("world").emit("Arroba")
```

**Events run asynchronously. Every listener is fired on a separate
thread.**