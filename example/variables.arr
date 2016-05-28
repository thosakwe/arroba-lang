# Declare variables with a left arrow.

# You can reassign like this, too.
# However, you might not want to reassign a global variable,
# as Arroba will always assign the first instance of the symbol
# found.
#
# To force a variable to exist within the current scope, prefix it
# with 'local:'

# This is global
pitbull <- "Mr. Worldwide"
pitbull -> print

locally <- fn() {
    local:pitbull <- "Just within the 305"
    pitbull -> print
}

locally()

# Now we're back to global
pitbull -> print

# Alternatively, you can use the right arrow to declare a variable

2 -> two
two -> print

# This variable can be piped, too.
3 -> three -> print
"This is three: ${three}" -> print