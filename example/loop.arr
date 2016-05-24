# For loop

i <- 0
runThrice <- for(3)

looper <- fn() {
    i <- i + 1
    "Looped ${i} time(s)" -> print
}

looper -> runThrice

# All loop

["hello", "world"] -> (all(print))