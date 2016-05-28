# No real class support yet, but you can use functions.

User <- fn(name) {
    # Use 'any()' to make arbitrary data.
    local:this <- any()

    this.name <- name

    ret this
}

# Use like a constructor.
# As you can imagine, these can be piped.

user <- input("Enter your name:")
            -> User
            -> (user) => user.name
            -> (name) => "Hello, ${name}!"
            -> print