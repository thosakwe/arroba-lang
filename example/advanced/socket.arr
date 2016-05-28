String = import("<util>").String

# Arroba also includes support for sockets.
# These sockets are event-based.

# To make a server socket...

server = Socket("127.0.0.1", 8888)

# Sockets have an 'accept' event attached, which can be used
# for asynchronous listening.

server.accept.on(fn(socket) {
    # socket within this scope is a Socket instance as well.
    # However, this socket is a client socket, and has two key functions.
    #
    # 'read' will return a byte array of all input data.

    str = String(socket.read())
    print("Incoming data: ${str}")

    # 'write' will write the given data, whether it be a byte array,
    # a string, or a number.
    # Arrays will have all their contents written. If it is a byte array,
    # each byte will be written.
    #
    # 'write' returns the socket instance, and thus can be chained.
    # 'write' also accepts infinite arguments.

    socket.write("Hello, socket world!\r\n")
    socket.close()
})

# You can call 'listen' on a socket, and it will be bound to the
# given port on the given host.
# Of course, this is asynchronous.
#
# The returned task blocks indefinitely, so you will probably never want
# to await or yield it.
#
# Just call 'run'.
#
# As you can imagine, you can also use this task to handle errors via 'fail'.

server.listen().run()

# Sockets also have a 'close' task.
# await server.close()

# To create a client socket, just call 'connect'.

client = Socket("127.0.0.1", 3000)
await client.connect()
client.write("YES")