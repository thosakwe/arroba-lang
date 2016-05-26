# You can easily print anything

"Hello!" -> print
print("foo", "bar", "unlimited arguments")

# Print an error

"Oopsies!" -> printErr

# Easily read a line of input via input()

name <- input()
"Name: ${name}" -> print

# You can pass argument(s) to input to create a prompt:

input("Legal Name:")
    -> (name) => "Nice to meet you, ${name}!"
    -> print